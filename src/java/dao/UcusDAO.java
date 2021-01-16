package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Firma;
import model.Havaalani;
import model.Ucak;
import model.Ucus;

public class UcusDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   

    private static final String UCUS_INSERT ="INSERT INTO ucus (ucus_kalkis_id, ucus_varis_id, ucus_tarih, ucus_saat, ucus_sure, firma_id, ucak_id, ucus_ucret) VALUES (?,?,?,?,?,?,?,?);";
    private static final String FIRMA_SELECT_ALL = "select * from firma;";
    private static final String HAVAALANI_SELECT_ALL = "select * from havaalani;";
    private static final String UCAK_SELECT_ALL = "select * from ucak;";
    private static final String GUNCELUCUS_SELECT_ALL="select ucus_id, s.havaalani_ad as kalkis_ad, p.havaalani_ad as varis_ad, ucus_tarih, ucus_saat, ucus_sure, firma.firma_ad, ucak.ucak_ad, ucus_ucret from ucus\n" +
                                "INNER JOIN  ucak ON (ucak.ucak_id = ucus.ucak_id)\n" +
                                "INNER JOIN  firma ON (firma.firma_id = ucus.firma_id)\n" +
                                "INNER JOIN  havaalani s ON (s.havaalani_id = ucus.ucus_kalkis_id)\n" +
                                "INNER JOIN  havaalani p ON (p.havaalani_id = ucus.ucus_varis_id)\n" +
                                "WHERE ucus_tarih >= ? ;";
    
    private static final String GECMISUCUS_SELECT_ALL="select ucus_id, s.havaalani_ad as kalkis_ad, p.havaalani_ad as varis_ad, ucus_tarih, ucus_saat, ucus_sure, firma.firma_ad, ucak.ucak_ad, ucus_ucret from ucus\n" +
                                "INNER JOIN  ucak ON (ucak.ucak_id = ucus.ucak_id)\n" +
                                "INNER JOIN  firma ON (firma.firma_id = ucus.firma_id)\n" +
                                "INNER JOIN  havaalani s ON (s.havaalani_id = ucus.ucus_kalkis_id)\n" +
                                "INNER JOIN  havaalani p ON (p.havaalani_id = ucus.ucus_varis_id)\n" +
                                "WHERE ucus_tarih < ? ;";
    private static final String UCUS_DELETE = "delete from ucus where ucus_id = ?;";
    private static final String UCUS_SELECT_ID = "SELECT * FROM ucus  where ucus_id=?;";
    private static final String UCUS_UPDATE = "update ucus set ucus_kalkis_id = ?, ucus_varis_id=?, ucus_tarih=?, ucus_saat=?, ucus_sure=?, firma_id=?, ucak_id=?, ucus_ucret=? where ucus_id = ?;";
    private static final String UCUS_KONTROL = "select * from ucus as u \n" +
                                "join ucak as k on k.ucak_id=u.ucak_id\n" +
                                "where u.ucak_id=? and u.ucus_tarih=? and ((u.ucus_saat BETWEEN ? AND ?) or (ADDTIME(u.ucus_saat, u.ucus_sure) BETWEEN ? AND ?));";
    
    public UcusDAO() {}
    
    protected Connection getConnection() {
        Connection connection = null;
         
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcKullaniciname,jdbcPassword);
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public boolean ucussil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCUS_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean ucusguncelle(Ucus ucus) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCUS_UPDATE);) {
            statement.setInt(1, ucus.getUcus_kalkis_id());
            statement.setInt(2, ucus.getUcus_varis_id());
            statement.setString(3, ucus.getUcus_tarih()); 
            statement.setString(4, ucus.getUcus_saat());
            statement.setString(5, ucus.getUcus_sure());
            statement.setInt(6, ucus.getFirma_id());
            statement.setInt(7, ucus.getUcak_id());
            statement.setDouble(8, ucus.getUcus_ucret());
            statement.setInt(9, ucus.getUcus_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public Ucus ucussec(int id) {
        Ucus ucus = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                int ucus_kalkis_id = rs.getInt("ucus_kalkis_id");
                int ucus_varis_id = rs.getInt("ucus_varis_id");
                String ucus_tarih = rs.getString("ucus_tarih");
                String ucus_saat = rs.getString("ucus_saat");
                String ucus_sure = rs.getString("ucus_sure");
                int firma_id = rs.getInt("firma_id");
                int ucak_id = rs.getInt("ucak_id");
                Double ucus_ucret = rs.getDouble("ucus_ucret");
                ucus = new Ucus(id,ucus_kalkis_id,ucus_varis_id,ucus_tarih,ucus_saat,ucus_sure,firma_id,ucak_id,ucus_ucret);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucus;
    }
    public boolean ucuskontrol(Ucus ucus)throws SQLException { 
        String ucus_saat = ucus.getUcus_saat();
        ucus_saat = ucus_saat.substring(0, 5);
        String ucus_sure = ucus.getUcus_sure();
        String[] ARRAYucus_sure = ucus_sure.split(":");
        String ucus_s = ARRAYucus_sure[0];
        String ucus_d = ARRAYucus_sure[1];
        String[] ARRAYucus_saat = ucus_saat.split(":");
        String s = ARRAYucus_saat[0];
        String d = ARRAYucus_saat[1];
        int saat = (Integer.parseInt(s) + Integer.parseInt(ucus_s)) % 24;
        int dakika = (Integer.parseInt(d) + Integer.parseInt(ucus_d)) % 60;
        String Sdakika;
        if (dakika < 10) {
            Sdakika = "0" + String.valueOf(dakika);
        } else {
            Sdakika = String.valueOf(dakika);
        }
        String Ssaat;
        if (saat < 10) {
            Ssaat = "0" + String.valueOf(saat);
        } else {
            Ssaat = String.valueOf(saat);
        }
        String varis_saat = Ssaat + ":" + Sdakika;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_KONTROL);) {
            preparedStatement.setInt(1, ucus.getUcak_id());
            preparedStatement.setString(2, ucus.getUcus_tarih());
            preparedStatement.setString(3, ucus_saat);
            preparedStatement.setString(4, varis_saat);
            preparedStatement.setString(5, ucus_saat);
            preparedStatement.setString(6, varis_saat);
            ResultSet rs = preparedStatement.executeQuery();            
            if (rs.next()) {
                return false;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return true;     
    }
    
    public void ucusolustur(Ucus ucus) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UCUS_INSERT)) {
            preparedStatement.setInt(1, ucus.getUcus_kalkis_id());
            preparedStatement.setInt(2, ucus.getUcus_varis_id());
            preparedStatement.setString(3, ucus.getUcus_tarih());
            preparedStatement.setString(4, ucus.getUcus_saat());
            preparedStatement.setString(5, ucus.getUcus_sure());
            preparedStatement.setInt(6, ucus.getFirma_id());
            preparedStatement.setInt(7, ucus.getUcak_id());
            preparedStatement.setDouble(8, ucus.getUcus_ucret());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public List<Ucus> guncelucusliste() {
        List<Ucus> ucuslar = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String str = now.format(formatter);

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GUNCELUCUS_SELECT_ALL);) {
            preparedStatement.setString(1, str);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ucus_id = rs.getInt("ucus_id");
                String ucus_kalkis = rs.getString("kalkis_ad");
                String ucus_varis = rs.getString("varis_ad");
                String ucus_tarih = rs.getString("ucus_tarih");
                String ucus_saat = rs.getString("ucus_saat");
                String ucus_sure = rs.getString("ucus_sure");
                String firma_ad = rs.getString("firma_ad");
                String ucak_ad = rs.getString("ucak_ad");
                Double ucus_ucret = rs.getDouble("ucus_ucret");
                ucuslar.add(new Ucus(ucus_id, ucus_tarih,ucus_saat, ucus_sure, ucus_ucret,firma_ad,ucak_ad,ucus_kalkis,ucus_varis));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucuslar;
    }  
    
    public List<Ucus> gecmisucusliste() {
        List<Ucus> ucuslar = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String str = now.format(formatter);

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GECMISUCUS_SELECT_ALL);) {
            preparedStatement.setString(1, str);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ucus_id = rs.getInt("ucus_id");
                String ucus_kalkis = rs.getString("kalkis_ad");
                String ucus_varis = rs.getString("varis_ad");
                String ucus_tarih = rs.getString("ucus_tarih");
                String ucus_saat = rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_sure = rs.getString("ucus_sure");
                String firma_ad = rs.getString("firma_ad");
                String ucak_ad = rs.getString("ucak_ad");
                Double ucus_ucret = rs.getDouble("ucus_ucret");
                ucuslar.add(new Ucus(ucus_id, ucus_tarih,ucus_saat, ucus_sure, ucus_ucret,firma_ad,ucak_ad,ucus_kalkis,ucus_varis));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucuslar;
    } 
    
    public List<Firma> firma() {
        List<Firma> firma = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int firma_id = rs.getInt("firma_id");
                String firma_ad = rs.getString("firma_ad");               
                firma.add(new Firma(firma_id, firma_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return firma;
    }
    
    public List<Ucak> ucak() {
        List<Ucak> ucak = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ucak_id = rs.getInt("ucak_id");
                String ucak_ad = rs.getString("ucak_ad");               
                ucak.add(new Ucak(ucak_id, ucak_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucak;
    }
    
    public List<Havaalani> havaalani() {
        List<Havaalani> havaalani = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_id = rs.getInt("havaalani_id");
                String havaalani_ad = rs.getString("havaalani_ad");     
                String havaalani_kod = rs.getString("havaalani_kod");  
                havaalani.add(new Havaalani(havaalani_id, havaalani_ad, havaalani_kod));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalani;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
