package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Havaalani_sehir;

public class Havaalani_sehirDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String SEHİR_SELECT_ID = "select * from havaalani_sehir where havaalani_sehir_id=?;";
    private static final String SEHİR_SELECT_ALL = "select * from havaalani_sehir;";
    private static final String SEHİR_INSERT = "INSERT INTO havaalani_sehir (havaalani_sehir_ad) VALUES " +
        " (?);"; 
    private static final String SEHİR_DELETE = "delete from havaalani_sehir where havaalani_sehir_id = ?;";
    private static final String SEHİR_UPDATE = "update havaalani_sehir set havaalani_sehir_ad = ? where havaalani_sehir_id = ?;";
    
    public Havaalani_sehirDAO() {}
    
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
        
    public List<Havaalani_sehir> sehirlistele() {
        List<Havaalani_sehir> sehirler = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_sehir_id = rs.getInt("havaalani_sehir_id");
                String havaalani_sehir_ad = rs.getString("havaalani_sehir_ad");
                sehirler.add(new Havaalani_sehir(havaalani_sehir_id, havaalani_sehir_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return sehirler;
    }
    
    public void sehirekle(Havaalani_sehir sehir) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_INSERT)) {
            preparedStatement.setString(1, sehir.getHavaalani_sehir_ad());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean sehirsil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(SEHİR_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public boolean sehirguncelle(Havaalani_sehir sehir) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(SEHİR_UPDATE);) {
            statement.setString(1, sehir.getHavaalani_sehir_ad());           
            statement.setInt(2, sehir.getHavaalani_sehir_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public Havaalani_sehir sehirsec(int id) {
        Havaalani_sehir sehir = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEHİR_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String havaalani_sehir_ad = rs.getString("havaalani_sehir_ad");
                sehir = new Havaalani_sehir(id, havaalani_sehir_ad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return sehir;
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
