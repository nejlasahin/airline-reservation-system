package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cevap;
import model.Mesaj;

public class CevapDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   
    
    private static final String CEVAP_SELECT_ALL = "select * from cevap\n" +
                                                "INNER JOIN  mesaj ON (mesaj.mesaj_id = cevap.mesaj_id);";
    private static final String CEVAP_DELETE = "delete from cevap where cevap_id = ?;";
    private static final String MESAJ_SELECT_ID = "SELECT * FROM mesaj  where mesaj_id=?;";
    private static final String CEVAP_INSERT = "INSERT INTO cevap (mesaj_id, cevap_icerik, cevap_baslik) VALUES (?,?,?);"; 
    private static final String CEVAP_SELECT_ID = "select * from cevap\n" +
                                                "INNER JOIN  mesaj ON (mesaj.mesaj_id = cevap.mesaj_id)"+
                                                "where cevap_id=?;";
    
    public CevapDAO() {}
    
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
    
    public List<Cevap> cevaplistele() {
        List<Cevap> cevaplar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cevap_id = rs.getInt("cevap_id");
                int mesaj_id = rs.getInt("mesaj_id");
                String cevap_icerik = rs.getString("cevap_icerik");
                String cevap_baslik = rs.getString("cevap_baslik");
                String cevap_tarih = rs.getString("cevap_tarih");
                String mesaj_adsoyad = rs.getString("mesaj_adsoyad");
                String mesaj_email = rs.getString("mesaj_email");
                String mesaj_konu = rs.getString("mesaj_konu");
                String mesaj_icerik = rs.getString("mesaj_icerik");
                String mesaj_tarih = rs.getString("mesaj_tarih");               
                
                cevaplar.add(new Cevap(cevap_id,mesaj_id,cevap_icerik,cevap_baslik,cevap_tarih,mesaj_adsoyad,mesaj_email, mesaj_konu, mesaj_icerik,mesaj_tarih));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cevaplar;
    } 
    
    public Mesaj mesajsec(int id) {
        Mesaj mesaj = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mesaj_adsoyad = rs.getString("mesaj_adsoyad");                
                String mesaj_email = rs.getString("mesaj_email");
                String mesaj_konu = rs.getString("mesaj_konu");
                String mesaj_icerik = rs.getString("mesaj_icerik");
                String mesaj_tarih = rs.getString("mesaj_tarih");
                int mesaj_okunma = rs.getInt("mesaj_okunma");
                int mesaj_cevap = rs.getInt("mesaj_cevap");
                mesaj = new Mesaj(id, mesaj_adsoyad, mesaj_email, mesaj_konu, mesaj_icerik, mesaj_tarih,mesaj_okunma,mesaj_cevap);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mesaj;
    } 
    
    public Cevap cevapincele(int id) {
        Cevap cevap = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cevap_id = rs.getInt("cevap_id");
                int mesaj_id = rs.getInt("mesaj_id");
                String cevap_icerik = rs.getString("cevap_icerik");
                String cevap_baslik = rs.getString("cevap_baslik");
                String cevap_tarih = rs.getString("cevap_tarih");
                String mesaj_adsoyad = rs.getString("mesaj_adsoyad");
                String mesaj_email = rs.getString("mesaj_email");
                String mesaj_konu = rs.getString("mesaj_konu");
                String mesaj_icerik = rs.getString("mesaj_icerik");
                String mesaj_tarih = rs.getString("mesaj_tarih");     
                cevap = new Cevap(cevap_id,mesaj_id,cevap_icerik,cevap_baslik,cevap_tarih,mesaj_adsoyad,mesaj_email, mesaj_konu, mesaj_icerik,mesaj_tarih);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cevap;
    }    
    
    public void cevapekle(Cevap cevap) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(CEVAP_INSERT)) {
            preparedStatement.setInt(1, cevap.getMesaj_id());
            preparedStatement.setString(2, cevap.getCevap_icerik());
            preparedStatement.setString(3, cevap.getCevap_baslik());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean cevapsil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(CEVAP_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
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
