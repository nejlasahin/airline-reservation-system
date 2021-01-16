package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mesaj;

public class MesajDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";       
    
    private static final String MESAJ_SELECT_ALL = "select * from mesaj;";
    private static final String MESAJ_DELETE = "delete from mesaj where mesaj_id = ?;";
    private static final String MESAJ_OKUNMA_UPDATE = "update mesaj set mesaj_okunma=1 where mesaj_id = ?;";
    private static final String MESAJ_CEVAP_UPDATE = "update mesaj set mesaj_cevap=1 where mesaj_id = ?;";
    private static final String MESAJ_INSERT = "INSERT INTO mesaj  (mesaj_adsoyad, mesaj_email, mesaj_konu, mesaj_icerik) VALUES " +
        " (?,?,?,?);"; 
    
    public MesajDAO() {}
    
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
    
    public boolean mesajokunma(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_OKUNMA_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean mesajcevap(int id) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(MESAJ_CEVAP_UPDATE);) {     
            statement.setInt(1, id);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public void mesajekle(Mesaj mesaj) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_INSERT)) {
            preparedStatement.setString(1, mesaj.getMesaj_adsoyad());
            preparedStatement.setString(2, mesaj.getMesaj_email());
            preparedStatement.setString(3, mesaj.getMesaj_konu());
            preparedStatement.setString(4, mesaj.getMesaj_icerik());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public List<Mesaj> mesajlistele() {
        List<Mesaj> mesajlar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(MESAJ_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int mesaj_id = rs.getInt("mesaj_id");
                String mesaj_adsoyad = rs.getString("mesaj_adsoyad");
                String mesaj_email = rs.getString("mesaj_email");
                String mesaj_konu = rs.getString("mesaj_konu");
                String mesaj_icerik = rs.getString("mesaj_icerik");
                String mesaj_tarih = rs.getString("mesaj_tarih");               
                int mesaj_okunma = rs.getInt("mesaj_okunma");
                int mesaj_cevap = rs.getInt("mesaj_cevap");
                mesajlar.add(new Mesaj(mesaj_id,mesaj_adsoyad,mesaj_email,mesaj_konu,mesaj_icerik,mesaj_tarih,mesaj_okunma,mesaj_cevap));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return mesajlar;
    } 
    
    public boolean mesajsil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); 
                PreparedStatement statement = connection.prepareStatement(MESAJ_DELETE);) {
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
