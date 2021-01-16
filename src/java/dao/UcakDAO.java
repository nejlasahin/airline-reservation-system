package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ucak;
import model.Firma;

public class UcakDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";   

    private static final String UCAK_SELECT_ALL = "SELECT ucak_id, ucak_ad, ucak_koltuk, firma.firma_ad FROM ucak INNER JOIN firma ON ucak.firma_id=firma.firma_id;";
    private static final String FIRMA_SELECT_ALL ="select * from firma;";
    private static final String UCAK_INSERT ="INSERT INTO ucak (ucak_ad, ucak_koltuk, firma_id) VALUES (?,?,?);";
    private static final String UCAK_DELETE = "delete from ucak where ucak_id = ?;";
    private static final String UCAK_UPDATE = "update ucak set ucak_ad = ?, ucak_koltuk=?, firma_id=? where ucak_id = ?;";
    private static final String UCAK_SELECT_ID = "SELECT * FROM ucak  where ucak_id=?;";
    
    public UcakDAO() {}
    
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
    
    public List<Ucak> ucaklistele() {
        List<Ucak> ucaklar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ucak_id = rs.getInt("ucak_id");
                String ucak_ad = rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                String firma_ad = rs.getString("firma_ad");
                ucaklar.add(new Ucak(ucak_id, ucak_ad, ucak_koltuk, firma_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucaklar;
    }  
    
    public boolean ucaksil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UCAK_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
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
    
    public void ucakekle(Ucak ucak) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_INSERT)) {
            preparedStatement.setString(1, ucak.getUcak_ad());
            preparedStatement.setInt(2, ucak.getUcak_koltuk());
            preparedStatement.setInt(3, ucak.getFirma_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean ucakguncelle(Ucak ucak) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(UCAK_UPDATE);) {
            statement.setString(1, ucak.getUcak_ad());
            statement.setInt(2, ucak.getUcak_koltuk());
            statement.setInt(3, ucak.getFirma_id());       
            statement.setInt(4, ucak.getUcak_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }  
    
    public Ucak ucaksec(int id) {
        Ucak ucak = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UCAK_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ucak_ad = rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                int firma_id = rs.getInt("firma_id");
                ucak = new Ucak(id, ucak_ad, ucak_koltuk, firma_id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return ucak;
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
