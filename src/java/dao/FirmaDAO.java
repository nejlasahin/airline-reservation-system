package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Firma;

public class FirmaDAO {
    
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    

    private static final String FIRMA_INSERT = "INSERT INTO firma (firma_ad, firma_logo) VALUES (?, ?);"; 
    private static final String FIRMA_SELECT_ALL = "select * from firma;";
    private static final String FIRMA_DELETE = "delete from firma where firma_id = ?;";
    private static final String FIRMA_SELECT_ID = "select * from firma where firma_id=?;";
    private static final String FIRMA_UPDATE = "update firma set firma_ad = ?, firma_logo=? where firma_id = ?;";
    
    public FirmaDAO() {}
    
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
    
    public List<Firma> firmalistele() {
        List<Firma> firmalar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int firma_id = rs.getInt("firma_id");
                String firma_ad = rs.getString("firma_ad");
                String firma_logo = rs.getString("firma_logo");
                firmalar.add(new Firma(firma_id, firma_ad, firma_logo));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return firmalar;
    }       
    
    public void firmaekle(Firma firma) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_INSERT)) {
            preparedStatement.setString(1, firma.getFirma_ad());
            preparedStatement.setString(2, firma.getFirma_logo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public boolean firmasil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(FIRMA_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public Firma firmasec(int id) {
        Firma firma = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIRMA_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String firma_ad = rs.getString("firma_ad");
                String firma_logo = rs.getString("firma_logo");
                firma = new Firma(id, firma_ad, firma_logo);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return firma;
    }  
    
    public boolean firmaguncelle(Firma firma) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(FIRMA_UPDATE);) {
            statement.setString(1, firma.getFirma_ad());           
            statement.setString(2, firma.getFirma_logo());
            statement.setInt(3, firma.getFirma_id());
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
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
