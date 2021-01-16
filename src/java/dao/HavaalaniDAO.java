package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Havaalani;
import model.Havaalani_sehir;
import model.Havaalani_ulke;

public class HavaalaniDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";  
    
    
    private static final String HAVAALANI_SELECT_ALL = "SELECT havaalani_id, havaalani_ad, havaalani_kod, havaalani_ulke.havaalani_ulke_id, havaalani_ulke.havaalani_ulke_ad, havaalani_sehir.havaalani_sehir_id, havaalani_sehir.havaalani_sehir_ad  FROM havaalani INNER JOIN havaalani_ulke ON havaalani.havaalani_ulke_id= havaalani_ulke.havaalani_ulke_id INNER JOIN havaalani_sehir ON havaalani.havaalani_sehir_id= havaalani_sehir.havaalani_sehir_id;";
    private static final String HAVAALANI_INSERT ="INSERT INTO havaalani (havaalani_ad, havaalani_kod, havaalani_sehir_id, havaalani_ulke_id) VALUES (?,?,?,?);";
    private static final String HAVAALANI_SEHIR_SELECT_ALL ="select * from havaalani_sehir;";
    private static final String HAVAALANI_ULKE_SELECT_ALL ="select * from havaalani_ulke;";
    private static final String HAVAALANI_DELETE = "delete from havaalani where havaalani_id = ?;";
    private static final String HAVAALANI_SELECT_ID = "SELECT * FROM havaalani  where havaalani_id=?;";
    private static final String HAVAALANI_UPDATE = "update havaalani set havaalani_ad = ?, havaalani_kod=?, havaalani_ulke_id=?, havaalani_sehir_id=? where havaalani_id = ?;";
    
    public HavaalaniDAO() {}
    
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
          
    public List<Havaalani> havaalaniliste() {

        List<Havaalani> havaalani = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_sehir_id = rs.getInt("havaalani_sehir_id");
                String havaalani_sehir_ad = rs.getString("havaalani_sehir_ad");
                int havaalani_ulke_id = rs.getInt("havaalani_ulke_id");
                String havaalani_ulke_ad = rs.getString("havaalani_ulke_ad");
                String havaalani_ad = rs.getString("havaalani_ad");
                String havaalani_kod = rs.getString("havaalani_kod");
                int havaalani_id = rs.getInt("havaalani_id");
                havaalani.add(new Havaalani(havaalani_id, havaalani_ulke_id, havaalani_sehir_id, havaalani_ad, havaalani_kod, havaalani_ulke_ad, havaalani_sehir_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalani;
    }
    
    public List<Havaalani_sehir> havaalanisehir() {

        List<Havaalani_sehir> havaalanisehir = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SEHIR_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_sehir_id = rs.getInt("havaalani_sehir_id");
                String havaalani_sehir_ad = rs.getString("havaalani_sehir_ad");               
                havaalanisehir.add(new Havaalani_sehir(havaalani_sehir_id, havaalani_sehir_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalanisehir;
    }
    
    public List<Havaalani_ulke> havaalaniulke() {

        List<Havaalani_ulke> havaalaniulke = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_ULKE_SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int havaalani_ulke_id = rs.getInt("havaalani_ulke_id");
                String havaalani_ulke_ad = rs.getString("havaalani_ulke_ad");               
                havaalaniulke.add(new Havaalani_ulke(havaalani_ulke_id, havaalani_ulke_ad));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalaniulke;
    }
    
    public void havaalaniekle(Havaalani havaalani) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_INSERT)) {
            preparedStatement.setString(1, havaalani.getHavaalani_ad());
            preparedStatement.setString(2, havaalani.getHavaalani_kod());
            preparedStatement.setInt(3, havaalani.getHavaalani_sehir_id());
            preparedStatement.setInt(4, havaalani.getHavaalani_ulke_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    
    public boolean havaalanisil(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(HAVAALANI_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public Havaalani havaalanisec(int id) {
        Havaalani havaalani = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(HAVAALANI_SELECT_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String havaalani_ad = rs.getString("havaalani_ad");
                String havaalani_kod = rs.getString("havaalani_kod");
                int havaalani_ulke_id = rs.getInt("havaalani_ulke_id");
                int havaalani_sehir_id = rs.getInt("havaalani_sehir_id");
                
                havaalani = new Havaalani(id, havaalani_ulke_id, havaalani_sehir_id,havaalani_ad, havaalani_kod);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return havaalani;
    } 
    
    public boolean havaalaniguncelle(Havaalani havaalani) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(HAVAALANI_UPDATE);) {
            statement.setString(1, havaalani.getHavaalani_ad()); 
            statement.setString(2, havaalani.getHavaalani_kod());
            statement.setInt(3, havaalani.getHavaalani_ulke_id());
            statement.setInt(4, havaalani.getHavaalani_sehir_id());
            statement.setInt(5, havaalani.getHavaalani_id());
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
