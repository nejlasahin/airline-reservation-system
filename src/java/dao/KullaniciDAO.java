package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Kullanici;

public class KullaniciDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String KULLANICI_INSERT = "INSERT INTO kullanicilar" +
            "  (kullanici_ad, kullanici_soyad, kullanici_email, kullanici_sifre, kullanici_yetki) VALUES " +
        " (?, ?, ?, ?,"+1+");";
    private static final String KULLANICI_SELECT_ID = "select * from kullanicilar where kullanici_id=?;";
    private static final String KULLANICI_DELETE = "delete from kullanicilar where kullanici_id = ?;";
    private static final String KULLANICI_SELECT_EMAIL = "select * from kullanicilar where kullanici_email = ?;";
    private static final String KULLANICI_SELECT_ALL = "select * from kullanicilar;";
    private static final String KULLANICI_SELECT_EMAIL_SIFRE = "select * from kullanicilar where kullanici_email = ? and kullanici_sifre = ?;";
    private static final String KULLANICI_INSERT_ADMIN ="INSERT INTO kullanicilar (kullanici_ad, kullanici_soyad, kullanici_email, kullanici_sifre, kullanici_yetki) VALUES (?,?,?,?,"+2+");";
    private static final String KULLANICI_UPDATE = "update kullanicilar set kullanici_ad = ?, kullanici_soyad = ?, kullanici_email = ?, kullanici_sifre = ? where kullanici_id = ?;";
    private static final String PROFIL_UPDATE = "update kullanicilar set kullanici_ad = ?, kullanici_soyad = ?, kullanici_email = ? where kullanici_id = ?;";
    private static final String ADMIN_SELECT_EMAIL_SIFRE = "select * from kullanicilar where kullanici_email = ? and kullanici_sifre = ? and kullanici_yetki=2;";
    private static final String SIFRE_KONTROL_SELECT = "select * from kullanicilar where kullanici_id=? and kullanici_sifre=?;";
    private static final String SIFRE_UPDATE = "update kullanicilar set kullanici_sifre = ? where kullanici_id = ?;";
    private static final String HESAP_DELETE = "delete from kullanicilar where kullanici_id = ?;";
    public KullaniciDAO() {}

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

    public List<Kullanici> uyelistele() {
        List<Kullanici> uyeler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int kullanici_id = rs.getInt("kullanici_id");
                String kullanici_ad = rs.getString("kullanici_ad");
                String kullanici_soyad = rs.getString("kullanici_soyad");
                String kullanici_email = rs.getString("kullanici_email");
                int kullanici_yetki = rs.getInt("kullanici_yetki");
                uyeler.add(new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email, kullanici_yetki));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return uyeler;
    }
    
    public boolean sifrekontrol(int id, String kullanici_sifre) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SIFRE_KONTROL_SELECT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, kullanici_sifre);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public boolean kullanicisil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(KULLANICI_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean hesapsil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(HESAP_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public Kullanici kullanicisec(int id) {
        Kullanici kullanici = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kullanici_ad = rs.getString("kullanici_ad");
                String kullanici_soyad = rs.getString("kullanici_soyad");
                String kullanici_email = rs.getString("kullanici_email");
                String kullanici_sifre = rs.getString("kullanici_sifre");
                kullanici = new Kullanici(id, kullanici_ad,kullanici_soyad,kullanici_email, kullanici_sifre);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return kullanici;
    }
    
    public Kullanici sifreal(String kullanici_email) {
        Kullanici kullanici = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL);) {
            preparedStatement.setString(1, kullanici_email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String kullanici_sifre = rs.getString("kullanici_sifre");
                kullanici = new Kullanici(kullanici_email, kullanici_sifre);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return kullanici;
    }
    
    public boolean adminguncelle(Kullanici kullanici) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(KULLANICI_UPDATE);) {
            statement.setString(1, kullanici.getKullanici_ad()); 
            statement.setString(2, kullanici.getKullanici_soyad());
            statement.setString(3, kullanici.getKullanici_email());
            statement.setString(4, kullanici.getKullanici_sifre());
            statement.setInt(5, kullanici.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean sifreguncelle(Kullanici kullanici) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(SIFRE_UPDATE);) {
            statement.setString(1, kullanici.getKullanici_sifre());
            statement.setInt(2, kullanici.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public boolean profilguncelle(Kullanici kullanici) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(PROFIL_UPDATE);) {
            statement.setString(1, kullanici.getKullanici_ad()); 
            statement.setString(2, kullanici.getKullanici_soyad());
            statement.setString(3, kullanici.getKullanici_email());
            statement.setInt(4, kullanici.getKullanici_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public void uyeol(Kullanici kullanici) throws SQLException {       
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT)) {
            preparedStatement.setString(1, kullanici.getKullanici_ad());
            preparedStatement.setString(2, kullanici.getKullanici_soyad());
            preparedStatement.setString(3, kullanici.getKullanici_email());
            preparedStatement.setString(4, kullanici.getKullanici_sifre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public void adminekle(Kullanici kullanici) throws SQLException {       
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_INSERT_ADMIN)) {
            preparedStatement.setString(1, kullanici.getKullanici_ad());
            preparedStatement.setString(2, kullanici.getKullanici_soyad());
            preparedStatement.setString(3, kullanici.getKullanici_email());
            preparedStatement.setString(4, kullanici.getKullanici_sifre());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean uyekontrol(String kullanici_email) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL);) {
            preparedStatement.setString(1, kullanici_email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return false;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return true;
    }
    
    public boolean uyegiriskontrol(String kullanici_email, String kullanici_sifre) {
        
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, kullanici_email);
            preparedStatement.setString(2, kullanici_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public boolean admingiriskontrol(String admin_email, String admin_sifre) {
        
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, admin_email);
            preparedStatement.setString(2, admin_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public Kullanici uyegiris(String kullanici_email, String kullanici_sifre) {
         
        Kullanici kullanici = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(KULLANICI_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, kullanici_email);
            preparedStatement.setString(2, kullanici_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int kullanici_id = rs.getInt("kullanici_id");
                String kullanici_ad = rs.getString("kullanici_ad");
                String kullanici_soyad = rs.getString("kullanici_soyad");
                int kullanici_yetki = rs.getInt("kullanici_yetki");
                kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, kullanici_email, kullanici_yetki);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return kullanici;
    }
    
    public Kullanici admingiris(String admin_email, String admin_sifre) {
         
        Kullanici kullanici = null;
        
        try (Connection connection = getConnection();
                
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_SELECT_EMAIL_SIFRE);) {
            preparedStatement.setString(1, admin_email);
            preparedStatement.setString(2, admin_sifre);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int kullanici_id = rs.getInt("kullanici_id");
                String kullanici_ad = rs.getString("kullanici_ad");
                String kullanici_soyad = rs.getString("kullanici_soyad");
                int kullanici_yetki = rs.getInt("kullanici_yetki");
                kullanici = new Kullanici(kullanici_id, kullanici_ad, kullanici_soyad, admin_email, kullanici_yetki);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return kullanici;
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
