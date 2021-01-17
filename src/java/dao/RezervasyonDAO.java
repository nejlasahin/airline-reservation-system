package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Rezervasyon;

public class RezervasyonDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/hawkeye";
    private final String jdbcKullaniciname = "root";
    private final String jdbcPassword = "123456";    
    
    private static final String TEKYON_SORGULAMA_SELECT1="select distinct ucus_id,(ucak.ucak_koltuk-(SELECT COUNT(ucus_id) FROM rezervasyon WHERE ucus_id=ucus.ucus_id )) as bos_koltuk, a.havaalani_sehir_ad as kalkis_sehir, b.havaalani_sehir_ad as varis_sehir ,s.havaalani_ad as kalkis_ad,s.havaalani_kod as kalkis_kod, p.havaalani_ad as varis_ad, p.havaalani_kod as varis_kod, ucus_tarih, ucus_saat, ucus_sure, firma.firma_ad,firma.firma_logo , ucak.ucak_ad, ucus_ucret from ucus JOIN havaalani JOIN havaalani_sehir\n" +
                                    "INNER JOIN  ucak ON (ucak.ucak_id = ucus.ucak_id)\n" +
                                    "INNER JOIN  firma ON (firma.firma_id = ucus.firma_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.havaalani_id = ucus.ucus_kalkis_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.havaalani_id = ucus.ucus_varis_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (a.havaalani_sehir_id = s.havaalani_sehir_id)\n" +
                                    "INNER JOIN  havaalani_sehir b ON (b.havaalani_sehir_id = p.havaalani_sehir_id)\n" +
                                    "WHERE s.havaalani_id = ? AND p.havaalani_id =? AND ucus_tarih=? AND (ucak.ucak_koltuk-(SELECT COUNT(ucus_id) FROM rezervasyon WHERE ucus_id=ucus.ucus_id )) >= ?;";
    private static final String TEKYON_SORGULAMA_SELECT2="select distinct ucus_id,(ucak.ucak_koltuk-(SELECT COUNT(ucus_id) FROM rezervasyon WHERE ucus_id=ucus.ucus_id )) as bos_koltuk, a.havaalani_sehir_ad as kalkis_sehir, b.havaalani_sehir_ad as varis_sehir ,s.havaalani_ad as kalkis_ad,s.havaalani_kod as kalkis_kod, p.havaalani_ad as varis_ad, p.havaalani_kod as varis_kod, ucus_tarih, ucus_saat, ucus_sure, firma.firma_ad,firma.firma_logo , ucak.ucak_ad, ucus_ucret from ucus JOIN havaalani JOIN havaalani_sehir\n" +
                                    "INNER JOIN  ucak ON (ucak.ucak_id = ucus.ucak_id)\n" +
                                    "INNER JOIN  firma ON (firma.firma_id = ucus.firma_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.havaalani_id = ucus.ucus_kalkis_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.havaalani_id = ucus.ucus_varis_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (a.havaalani_sehir_id = s.havaalani_sehir_id)\n" +
                                    "INNER JOIN  havaalani_sehir b ON (b.havaalani_sehir_id = p.havaalani_sehir_id)\n" +
                                    "WHERE s.havaalani_id = ? AND p.havaalani_id =? AND ucus_tarih=? AND ucus_saat > ? AND (ucak.ucak_koltuk-(SELECT COUNT(ucus_id) FROM rezervasyon WHERE ucus_id=ucus.ucus_id )) >= ?;";
    private static final String REZERVASYON_SELECT_COUNT="SELECT COUNT(*) as sonuc FROM rezervasyon WHERE rezervasyon_tarih BETWEEN ? AND ?;";
    private static final String UCUS_SELECT_COUNT="SELECT count(*) as sonuc FROM ucus WHERE ucus_tarih >= ? ;";
    private static final String MESAJ_SELECT_COUNT="SELECT count(*) as sonuc FROM mesaj WHERE mesaj_okunma = 0;";
    private static final String REZERVASYON_DELETE = "delete from rezervasyon where rezervasyon_id = ?;";
    private static final String REZERVASYON_SELECT_PNRNO="SELECT * FROM rezervasyon where pnr_no=? and yolcu_soyad=?;";
    private static final String REZERVASYON_SELECT_UCUS_ID="select DISTINCT k.ucak_ad, u.ucus_saat, u.ucus_tarih, u.ucus_sure, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tc, yolcu_tip, a.havaalani_sehir_ad AS kalkis_sehir, s.havaalani_ad as kalkis_ad, s.havaalani_kod as kalkis_kod, b.havaalani_sehir_ad as varis_sehir, p.havaalani_ad as varis_ad, p.havaalani_kod as varis_kod, f.firma_ad, f.firma_logo from rezervasyon JOIN havaalani JOIN havaalani_sehir JOIN ucus JOIN firma JOIN ucak\n" +
                                    "INNER JOIN  ucus u ON (rezervasyon.ucus_id = ucus.ucus_id)\n" +
                                    "INNER JOIN  firma f ON (f.firma_id = u.firma_id)\n" +
                                    "INNER JOIN  ucak k ON (k.ucak_id = u.ucak_id)\n" +
                                    "INNER JOIN  havaalani s ON (u.ucus_kalkis_id = s.havaalani_id)\n" +
                                    "INNER JOIN  havaalani p ON (u.ucus_varis_id = p.havaalani_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (s.havaalani_sehir_id = a.havaalani_sehir_id )\n" +
                                    "INNER JOIN  havaalani_sehir b ON (p.havaalani_sehir_id = b.havaalani_sehir_id)\n" +
                                    "WHERE u.ucus_id=? and rezervasyon.rezervasyon_id=?;";
    private static final String SELECT_UCUS_BILGILERI = "select distinct ucus_id,(ucak.ucak_koltuk-(SELECT COUNT(ucus_id) FROM rezervasyon WHERE ucus_id=ucus.ucus_id )) as bos_koltuk, a.havaalani_sehir_ad as kalkis_sehir, b.havaalani_sehir_ad as varis_sehir ,s.havaalani_ad as kalkis_ad,s.havaalani_kod as kalkis_kod, p.havaalani_ad as varis_ad, p.havaalani_kod as varis_kod, ucus_tarih, ucus_saat, ucus_sure, firma.firma_ad,firma.firma_logo , ucak.ucak_ad, ucak.ucak_koltuk, ucus_ucret from ucus JOIN havaalani JOIN havaalani_sehir\n" +
                                    "INNER JOIN  ucak ON (ucak.ucak_id = ucus.ucak_id)\n" +
                                    "INNER JOIN  firma ON (firma.firma_id = ucus.firma_id)\n" +
                                    "INNER JOIN  havaalani s ON (s.havaalani_id = ucus.ucus_kalkis_id)\n" +
                                    "INNER JOIN  havaalani p ON (p.havaalani_id = ucus.ucus_varis_id)\n" +
                                    "INNER JOIN  havaalani_sehir a ON (a.havaalani_sehir_id = s.havaalani_sehir_id)\n" +
                                    "INNER JOIN  havaalani_sehir b ON (b.havaalani_sehir_id = p.havaalani_sehir_id)\n" +
                                    "WHERE ucus_id=?;";
    private static final String KOLTUK_BILGI_SELECT="SELECT koltuk_no FROM rezervasyon \n" +
                                                "WHERE ucus_id=?\n" +
                                                "ORDER BY koltuk_no ASC;";  
    private static final String KOLTUK_DOLU_SELECT="SELECT COUNT(koltuk_no) as koltuk_dolu FROM rezervasyon \n" +
                                                "WHERE ucus_id=?;";
    private static final String REZERVASYON_INSERT ="INSERT INTO rezervasyon (ucus_id, kullanici_id, pnr_no, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, yolcu_tarih, yolcu_ucret, koltuk_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";   
    private static final String KOLTUK_NO_SELECT="SELECT * FROM rezervasyon WHERE ucus_id=? and koltuk_no=?;";
    private static final String REZERVASYON_ISLEMLERIM_SELECT="SELECT r.rezervasyon_id,r.durum ,r.rezervasyon_tarih, r.pnr_no, r.yolcu_ad, r.yolcu_soyad, r.yolcu_email, r.yolcu_tel, r.yolcu_tc, r.yolcu_tip, r.yolcu_tarih, r.yolcu_ucret, r.koltuk_no, u.ucus_tarih, u.ucus_saat, u.ucus_sure, k.havaalani_ad as kalkis_ad, k.havaalani_kod as kalkis_kod, v.havaalani_ad as varis_ad, v.havaalani_kod as varis_kod, s1.havaalani_sehir_ad as kalkis_sehir, s2.havaalani_sehir_ad as varis_sehir, f.firma_ad, f.firma_logo, p.ucak_ad from rezervasyon AS r\n" +
                                                "JOIN ucus AS u ON u.ucus_id = r.ucus_id\n" +
                                                "JOIN havaalani AS k ON k.havaalani_id=u.ucus_kalkis_id \n" +
                                                "JOIN havaalani AS v ON v.havaalani_id=u.ucus_varis_id\n" +
                                                "JOIN havaalani_sehir AS s1 ON s1.havaalani_sehir_id=k.havaalani_sehir_id\n" +
                                                "JOIN havaalani_sehir AS s2 ON s2.havaalani_sehir_id=v.havaalani_sehir_id\n" +
                                                "JOIN firma AS f ON f.firma_id=u.firma_id\n" +
                                                "JOIN ucak AS p ON p.ucak_id=u.ucak_id\n" +
                                                "WHERE r.kullanici_id=?\n"+
                                                "ORDER BY r.rezervasyon_tarih DESC;";
    private static final String IPTAL_DURUM1="update rezervasyon r\n" +
                                                "join ucus u on r.ucus_id = u.ucus_id\n" +
                                                "set r.durum = '1'\n" +
                                                "WHERE (r.kullanici_id=? and u.ucus_tarih > ?) OR (u.ucus_tarih = ? and u.ucus_saat > ?);";
    private static final String IPTAL_DURUM0="update rezervasyon r\n" +
                                                "join ucus u on r.ucus_id = u.ucus_id\n" +
                                                "set r.durum = '0'\n" +
                                                "WHERE (r.kullanici_id=? and u.ucus_tarih < ?) OR (u.ucus_tarih = ? and u.ucus_saat < ?);";
    private static final String REZERVASYON_UPDATE = "update rezervasyon set yolcu_ad = ?, yolcu_soyad=?, yolcu_tc=?, yolcu_tarih=?, yolcu_email=?, yolcu_tel=? where rezervasyon_id = ?;";
    
    private static final String RZERVASYON_INCELE="SELECT r.rezervasyon_id,r.durum ,r.rezervasyon_tarih, r.pnr_no, r.yolcu_ad, r.yolcu_soyad, r.yolcu_email, r.yolcu_tel, r.yolcu_tc, r.yolcu_tip, r.yolcu_tarih, r.yolcu_ucret, r.koltuk_no, u.ucus_tarih, u.ucus_saat, u.ucus_sure, k.havaalani_ad as kalkis_ad, k.havaalani_kod as kalkis_kod, v.havaalani_ad as varis_ad, v.havaalani_kod as varis_kod, s1.havaalani_sehir_ad as kalkis_sehir, s2.havaalani_sehir_ad as varis_sehir, f.firma_ad, f.firma_logo, p.ucak_ad from rezervasyon AS r\n" +
                                                "JOIN ucus AS u ON u.ucus_id = r.ucus_id\n" +
                                                "JOIN havaalani AS k ON k.havaalani_id=u.ucus_kalkis_id\n" +
                                                "JOIN havaalani AS v ON v.havaalani_id=u.ucus_varis_id\n" +
                                                "JOIN havaalani_sehir AS s1 ON s1.havaalani_sehir_id=k.havaalani_sehir_id\n" +
                                                "JOIN havaalani_sehir AS s2 ON s2.havaalani_sehir_id=v.havaalani_sehir_id\n" +
                                                "JOIN firma AS f ON f.firma_id=u.firma_id\n" +
                                                "JOIN ucak AS p ON p.ucak_id=u.ucak_id\n" +
                                                "ORDER BY r.rezervasyon_tarih DESC;";
    
    public RezervasyonDAO() {}
    
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
    
    public boolean iptaldurum1(int id) throws SQLException {
        boolean guncellenenSatir;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now(); 
        String tarih = now.format(formatter);
        String saat = now.format(timeformatter);
        
        String[] ARRAYsaat = saat.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Sdakika;
        if (mm < 10) {
            Sdakika = "0" + String.valueOf(mm);
        } else {
            Sdakika = String.valueOf(mm);
        }
        String Ssaat;
        if (hh < 10) {
            Ssaat = "0" + String.valueOf(hh + 2);
        } else {
            Ssaat = String.valueOf(hh + 2);
        }
        String ucus_saat = Ssaat + ":" + Sdakika;            
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(IPTAL_DURUM1);) {
            statement.setInt(1, id);
            statement.setString(2, tarih);
            statement.setString(3, tarih);
            statement.setString(4, ucus_saat);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean iptaldurum0(int id) throws SQLException {
        boolean guncellenenSatir;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now(); 
        String tarih = now.format(formatter);
        String saat = now.format(timeformatter);
        
        String[] ARRAYsaat = saat.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Sdakika;
        if (mm < 10) {
            Sdakika = "0" + String.valueOf(mm);
        } else {
            Sdakika = String.valueOf(mm);
        }
        String Ssaat;
        if (hh < 10) {
            Ssaat = "0" + String.valueOf(hh + 2);
        } else {
            Ssaat = String.valueOf(hh + 2);
        }
        String ucus_saat = Ssaat + ":" + Sdakika;            
        try (Connection connection = getConnection(); 
            PreparedStatement statement = connection.prepareStatement(IPTAL_DURUM0);) {
            statement.setInt(1, id);
            statement.setString(2, tarih);
            statement.setString(3, tarih);
            statement.setString(4, ucus_saat);
            guncellenenSatir = statement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    }
    
    public boolean koltukkontrol(int id, String koltuk_no) {

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_NO_SELECT);) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, koltuk_no);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        
        return false;
    }
    
    public void rezervasyonekle(Rezervasyon rez) throws SQLException {  
        try (           
            Connection connection = getConnection();                                
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_INSERT)) {
            preparedStatement.setInt(1, rez.getUcus_id());
            preparedStatement.setInt(2, rez.getKullanici_id());
            preparedStatement.setString(3, rez.getPnr_no());
            preparedStatement.setString(4, rez.getYolcu_ad());
            preparedStatement.setString(5, rez.getYolcu_soyad());
            preparedStatement.setString(6, rez.getYolcu_email());
            preparedStatement.setString(7, rez.getYolcu_tel());
            preparedStatement.setString(8, rez.getYolcu_tc());
            preparedStatement.setInt(9, rez.getYolcu_tip());
            preparedStatement.setString(10, rez.getYolcu_tarih());
            preparedStatement.setDouble(11, rez.getYolcu_ucret());
            preparedStatement.setString(12, rez.getKoltuk_no());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    } 
    
    public List<Rezervasyon> rezervasyonislem(int id) {
        List<Rezervasyon> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(REZERVASYON_ISLEMLERIM_SELECT);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int durum = rs.getInt("durum");
                String pnr_no = rs.getString("pnr_no");
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_soyad = rs.getString("yolcu_soyad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip");
                String yolcu_tarih = rs.getString("yolcu_tarih");
                Double yolcu_ucret=rs.getDouble("yolcu_ucret"); 
                String koltuk_no = rs.getString("koltuk_no");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_tarih=rs.getString("ucus_tarih");
                String ucus_sure=rs.getString("ucus_sure");
                String[] ARRAYucus_sure = ucus_sure.split(":"); 
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String[] ARRAYucus_saat = ucus_saat.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;              
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String firma_ad=rs.getString("firma_ad");
                String firma_logo=rs.getString("firma_logo");
                String ucak_ad=rs.getString("ucak_ad");
                String rezervasyon_tarih=rs.getString("rezervasyon_tarih");          
                rez.add(new Rezervasyon(durum, rezervasyon_id, rezervasyon_tarih, pnr_no,yolcu_ad, yolcu_soyad,yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, koltuk_no, ucus_tarih, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, ucus_sure, firma_ad, firma_logo, ucus_s, ucus_d, varis_saat,ucak_ad, yolcu_tarih, yolcu_ucret));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    }
    
    public boolean rezervasyonguncelle(Rezervasyon rez) throws SQLException {
        boolean guncellenenSatir;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_UPDATE);) {
            preparedStatement.setString(1, rez.getYolcu_ad());
            preparedStatement.setString(2, rez.getYolcu_soyad());
            preparedStatement.setString(3, rez.getYolcu_tc());
            preparedStatement.setString(4, rez.getYolcu_tarih());
            preparedStatement.setString(5, rez.getYolcu_email());
            preparedStatement.setString(6, rez.getYolcu_tel());
            preparedStatement.setInt(7, rez.getRezervasyon_id());
            guncellenenSatir = preparedStatement.executeUpdate() > 0;
        }
        return guncellenenSatir;
    } 
    
    public List<Rezervasyon> tekyonsorgulama(Rezervasyon rezervasyon) {
        List<Rezervasyon> rez = new ArrayList<>();
        DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String sss = now.format(timeformatter);

        String[] ARRAYsaat = sss.split(":");
        String h = ARRAYsaat[0];
        String m = ARRAYsaat[1];
        int hh = Integer.parseInt(h);
        int mm = Integer.parseInt(m);
        String Ssdakika;
        if (mm < 10) {
            Ssdakika = "0" + String.valueOf(mm);
        } else {
            Ssdakika = String.valueOf(mm);
        }
        String Sssaat;
        if (hh < 10) {
            Sssaat = "0" + String.valueOf(hh + 1);
        } else {
            Sssaat = String.valueOf(hh + 1);
        }
        String u_saat = Sssaat + ":" + Ssdakika;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        String date1 = date.format(formatter);
        if (rezervasyon.getUcus_tarih().equals(date1)) {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT2);) {
                statement.setInt(1, rezervasyon.getHavaalani_kalkis_id());
                statement.setInt(2, rezervasyon.getHavaalani_varis_id());
                statement.setString(3, rezervasyon.getUcus_tarih());
                statement.setString(4, u_saat);
                statement.setInt(5, (rezervasyon.getCocuk_sayi() + rezervasyon.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int ucus_id = rs.getInt("ucus_id");
                    String kalkis_sehir = rs.getString("kalkis_sehir");
                    String kalkis_ad = rs.getString("kalkis_ad");
                    String kalkis_kod = rs.getString("kalkis_kod");
                    String varis_sehir = rs.getString("varis_sehir");
                    String varis_ad = rs.getString("varis_ad");
                    String varis_kod = rs.getString("varis_kod");
                    String ucus_saat = rs.getString("ucus_saat");
                    ucus_saat = ucus_saat.substring(0, 5);
                    String ucus_tarih = rs.getString("ucus_tarih");
                    String ucus_sure = rs.getString("ucus_sure");

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
                    String firma_ad = rs.getString("firma_ad");
                    String firma_logo = rs.getString("firma_logo");
                    Double ucus_ucret = rs.getDouble("ucus_ucret");
                    rez.add(new Rezervasyon(ucus_tarih, ucus_id, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, ucus_sure, firma_ad, firma_logo, ucus_ucret, ucus_s, ucus_d, varis_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        } else {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection.prepareStatement(TEKYON_SORGULAMA_SELECT1);) {
                statement.setInt(1, rezervasyon.getHavaalani_kalkis_id());
                statement.setInt(2, rezervasyon.getHavaalani_varis_id());
                statement.setString(3, rezervasyon.getUcus_tarih());
                statement.setInt(4, (rezervasyon.getCocuk_sayi() + rezervasyon.getYetiskin_sayi()));
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    int ucus_id = rs.getInt("ucus_id");
                    String kalkis_sehir = rs.getString("kalkis_sehir");
                    String kalkis_ad = rs.getString("kalkis_ad");
                    String kalkis_kod = rs.getString("kalkis_kod");
                    String varis_sehir = rs.getString("varis_sehir");
                    String varis_ad = rs.getString("varis_ad");
                    String varis_kod = rs.getString("varis_kod");
                    String ucus_saat = rs.getString("ucus_saat");
                    ucus_saat = ucus_saat.substring(0, 5);
                    String ucus_tarih = rs.getString("ucus_tarih");
                    String ucus_sure = rs.getString("ucus_sure");

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
                    String firma_ad = rs.getString("firma_ad");
                    String firma_logo = rs.getString("firma_logo");
                    Double ucus_ucret = rs.getDouble("ucus_ucret");
                    rez.add(new Rezervasyon(ucus_tarih, ucus_id, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, ucus_sure, firma_ad, firma_logo, ucus_ucret, ucus_s, ucus_d, varis_saat));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return rez;
        }
    }
    
    public Rezervasyon ucusbilgileri(int id) {
        Rezervasyon rez=null;
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_UCUS_BILGILERI);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int ucus_id = rs.getInt("ucus_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_tarih=rs.getString("ucus_tarih");
                String ucus_sure=rs.getString("ucus_sure");
                
                String[] ARRAYucus_sure = ucus_sure.split(":"); 
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String[] ARRAYucus_saat = ucus_saat.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;
                String firma_ad=rs.getString("firma_ad");
                String firma_logo=rs.getString("firma_logo");
                Double ucus_ucret=rs.getDouble("ucus_ucret");
                String ucak_ad=rs.getString("ucak_ad");
                int ucak_koltuk = rs.getInt("ucak_koltuk");
                rez = new Rezervasyon(ucus_tarih,ucus_id, kalkis_sehir,kalkis_ad,kalkis_kod,varis_sehir,varis_ad,varis_kod,ucus_saat,ucus_sure,firma_ad,firma_logo,ucus_ucret, ucus_s, ucus_d, varis_saat,ucak_ad,ucak_koltuk);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rez;
    } 
    
    public Rezervasyon rezervasyonsec(String pnr_no, String yolcu_soyad) {
        Rezervasyon rezervasyon = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_PNRNO);) {
            preparedStatement.setString(1, pnr_no);
            preparedStatement.setString(2, yolcu_soyad);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String rezervasyon_tarih = rs.getString("rezervasyon_tarih");
                int ucus_id = rs.getInt("ucus_id");
                int kullanici_id = rs.getInt("kullanici_id"); 
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip"); 
                String koltuk_no = rs.getString("koltuk_no");
                rezervasyon = new Rezervasyon(rezervasyon_id, rezervasyon_tarih,pnr_no,yolcu_ad, yolcu_soyad,yolcu_email,yolcu_tel,yolcu_tc,yolcu_tip,koltuk_no,kullanici_id, ucus_id  );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public Rezervasyon rezervasyonbilgi(int ucus_id, int rezervasyon_id) {
        Rezervasyon rezervasyon = null;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REZERVASYON_SELECT_UCUS_ID);) {
            preparedStatement.setInt(1, ucus_id);
            preparedStatement.setInt(2, rezervasyon_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ucus_saat = rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_sure = rs.getString("ucus_sure"); 
                String[] ARRAYucus_sure = ucus_sure.split(":"); 
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String ucus_tarih = rs.getString("ucus_tarih");
                String ucak_ad = rs.getString("ucak_ad");
                String firma_ad = rs.getString("firma_ad");
                String firma_logo = rs.getString("firma_logo"); 
                String kalkis_sehir = rs.getString("kalkis_sehir");
                String kalkis_ad = rs.getString("kalkis_ad");
                String kalkis_kod = rs.getString("kalkis_kod");
                String varis_sehir = rs.getString("varis_sehir");
                String varis_ad = rs.getString("varis_ad");
                String varis_kod = rs.getString("varis_kod");
                
                rezervasyon = new Rezervasyon(ucus_tarih, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, firma_ad, firma_logo, ucus_s, ucus_d, ucak_ad);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public boolean rezervasyoniptal(int id) throws SQLException {
        boolean silinenSatir;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(REZERVASYON_DELETE);) {
            statement.setInt(1, id);
            silinenSatir = statement.executeUpdate() > 0;
        }
        return silinenSatir;
    }
    
    public List<Rezervasyon> rezervasyonsayisi() {
        List<Rezervasyon> rezervasyon = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDate months = LocalDate.now().minusMonths(1);
        String date1 = months.format(formatter);
        LocalDateTime now = LocalDateTime.now().plusDays(1); 
        String date2 = now.format(formatter);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(REZERVASYON_SELECT_COUNT);) {
            statement.setString(1, date1);
            statement.setString(2, date2);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int sonuc = rs.getInt("sonuc");               
                rezervasyon.add(new Rezervasyon(sonuc));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Rezervasyon> ucussayisi() {
        List<Rezervasyon> rezervasyon = new ArrayList<> ();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        String date1 = now.format(formatter);
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(UCUS_SELECT_COUNT);) {
            statement.setString(1, date1);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int sonuc = rs.getInt("sonuc");               
                rezervasyon.add(new Rezervasyon(sonuc));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Rezervasyon>mesajsayisi() {
        List<Rezervasyon> rezervasyon = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(MESAJ_SELECT_COUNT);) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int sonuc = rs.getInt("sonuc");               
                rezervasyon.add(new Rezervasyon(sonuc));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyon;
    }
    
    public List<Rezervasyon> rezervasyonlistele() {
        List<Rezervasyon> rez = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(RZERVASYON_INCELE);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int durum = rs.getInt("durum");
                String pnr_no = rs.getString("pnr_no");
                String yolcu_ad = rs.getString("yolcu_ad");
                String yolcu_soyad = rs.getString("yolcu_soyad");
                String yolcu_email = rs.getString("yolcu_email");
                String yolcu_tel = rs.getString("yolcu_tel");
                String yolcu_tc = rs.getString("yolcu_tc");
                int yolcu_tip = rs.getInt("yolcu_tip");
                String yolcu_tarih = rs.getString("yolcu_tarih");
                Double yolcu_ucret=rs.getDouble("yolcu_ucret"); 
                String koltuk_no = rs.getString("koltuk_no");
                String ucus_saat=rs.getString("ucus_saat");
                ucus_saat=ucus_saat.substring(0, 5);
                String ucus_tarih=rs.getString("ucus_tarih");
                String ucus_sure=rs.getString("ucus_sure");
                String[] ARRAYucus_sure = ucus_sure.split(":"); 
                String ucus_s = ARRAYucus_sure[0];
                String ucus_d = ARRAYucus_sure[1];
                String[] ARRAYucus_saat = ucus_saat.split(":");
                String s = ARRAYucus_saat[0];
                String d = ARRAYucus_saat[1];
                int saat=(Integer.parseInt(s)+Integer.parseInt(ucus_s))%24 ;
                int dakika=(Integer.parseInt(d)+Integer.parseInt(ucus_d))%60 ;
                String Sdakika;
                if(dakika < 10){
                    Sdakika="0"+String.valueOf(dakika);
                }else{                                    
                    Sdakika=String.valueOf(dakika);
                }
                String Ssaat;
                if(saat < 10){
                    Ssaat="0"+String.valueOf(saat);
                }else{                                    
                    Ssaat=String.valueOf(saat);
                }
                String varis_saat = Ssaat+":"+Sdakika;              
                int rezervasyon_id = rs.getInt("rezervasyon_id");
                String kalkis_sehir=rs.getString("kalkis_sehir");
                String kalkis_ad=rs.getString("kalkis_ad");
                String kalkis_kod=rs.getString("kalkis_kod");
                String varis_sehir=rs.getString("varis_sehir");
                String varis_ad=rs.getString("varis_ad");
                String varis_kod=rs.getString("varis_kod");
                String firma_ad=rs.getString("firma_ad");
                String firma_logo=rs.getString("firma_logo");
                String ucak_ad=rs.getString("ucak_ad");
                String rezervasyon_tarih=rs.getString("rezervasyon_tarih");          
                rez.add(new Rezervasyon(durum, rezervasyon_id, rezervasyon_tarih, pnr_no,yolcu_ad, yolcu_soyad,yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, koltuk_no, ucus_tarih, kalkis_sehir, kalkis_ad, kalkis_kod, varis_sehir, varis_ad, varis_kod, ucus_saat, ucus_sure, firma_ad, firma_logo, ucus_s, ucus_d, varis_saat,ucak_ad, yolcu_tarih, yolcu_ucret));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } return rez;
    }  
    
    public List<Rezervasyon> koltukbilgi(int id) {
        List<Rezervasyon> rezervasyonlar = new ArrayList<> ();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_BILGI_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int koltuk_no = rs.getInt("koltuk_no");
                rezervasyonlar.add(new Rezervasyon(koltuk_no));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyonlar;
    }
    
    public Rezervasyon dolukoltuk(int id) {
        Rezervasyon rezervasyonlar = new Rezervasyon();
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(KOLTUK_DOLU_SELECT);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int koltuk_dolu = rs.getInt("koltuk_dolu");
                rezervasyonlar = new Rezervasyon(koltuk_dolu);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rezervasyonlar;
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
