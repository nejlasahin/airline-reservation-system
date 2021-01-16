package servlet;

import dao.HavaalaniDAO;
import dao.RezervasyonDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Havaalani;
import model.Rezervasyon;

@WebServlet(urlPatterns = {"/ucussorgulama", "/admin/rezervasyonliste", "/admin/rezervasyoniptal", "/rezervasyonsorgulama", "/rezervasyonolustur", "/rezervasyonislemlerim", "/gosterrezervasyonislemlerim", "/rezervasyonguncelle", "/reziptal"})
public class RezervasyonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private HavaalaniDAO havaalaniDAO;
    private RezervasyonDAO rezervasyonDAO;

    public void init() {
        havaalaniDAO = new HavaalaniDAO();
        rezervasyonDAO = new RezervasyonDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/ucussorgulama":
                    ucussorgulama(request, response);
                    break;
                case "/admin/rezervasyonliste":
                    rezervasyonlistele(request, response);
                    break;
                case "/admin/rezervasyoniptal":
                    rezervasyoniptal(request, response);
                    break;
                case "/rezervasyonsorgulama":
                    rezervasyonsorgulama(request, response);
                    break;
                case "/rezervasyonolustur":
                    rezervasyonolustur(request, response);
                    break;
                case "/rezervasyonislemlerim":
                    rezervasyonislemlerim(request, response);
                    break;
                case "/gosterrezervasyonislemlerim":
                    gosterrezervasyonislemlerim(request, response);
                    break;
                case "/rezervasyonguncelle":
                    rezervasyonguncelle(request, response);
                    break;
                case "/reziptal":
                    reziptal(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void reziptal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 1) {
            response.sendRedirect("ucakbileti");
        } else {
            int rezervasyon_id = Integer.parseInt(request.getParameter("rezervasyon_id"));
            String kullanici_sifre = (String) session.getAttribute("kullanici_sifre");
            String sifre = request.getParameter("sil_sifre");
            if (kullanici_sifre.equals(sifre)) {
                rezervasyonDAO.rezervasyoniptal(rezervasyon_id);
                response.sendRedirect("rezervasyonislemlerim?iptal=basarili");
            } else {
                response.sendRedirect("rezervasyonislemlerim?iptal=basarisiz");
            }
        }
    }

    private void rezervasyonguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 1) {
            response.sendRedirect("ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String yolcu_ad = new String((request.getParameter("yolcu_ad" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String yolcu_soyad = new String((request.getParameter("yolcu_soyad" + id)).getBytes("ISO-8859-1"), "UTF-8");
            String yolcu_tc = request.getParameter("yolcu_tc" + id);
            String yolcu_tarih = request.getParameter("yolcu_tarih" + id);
            String yolcu_email = request.getParameter("yolcu_email" + id);
            String yolcu_tel = request.getParameter("yolcu_tel" + id);
            Rezervasyon rez = new Rezervasyon(id, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tarih);
            rezervasyonDAO.rezervasyonguncelle(rez);
            response.sendRedirect("rezervasyonislemlerim?guncelleme=basarili");
        }
    }

    private void rezervasyonislemlerim(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 1) {
            response.sendRedirect("ucakbileti");
        } else {
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            rezervasyonDAO.iptaldurum1(kullanici_id);
            rezervasyonDAO.iptaldurum0(kullanici_id);
            List<Rezervasyon> rezervasyonislem = rezervasyonDAO.rezervasyonislem(kullanici_id);
            request.setAttribute("rezervasyonislem", rezervasyonislem);

            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonislemlerim.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterrezervasyonislemlerim(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 1) {
            response.sendRedirect("ucakbileti");
        } else {
            int ucus_id = Integer.parseInt(request.getParameter("ucus_id"));
            int kullanici_id = (int) session.getAttribute("kullanici_id");
            String yolcu_email = request.getParameter("yolcu_email");
            String yolcu_tel = request.getParameter("yolcu_tel");
            String pnr_no;
            int c_sayi = Integer.parseInt(request.getParameter("c_sayi"));
            int y_sayi = Integer.parseInt(request.getParameter("y_sayi"));
            Double u_ucret = Double.parseDouble(request.getParameter("u_ucret"));
            int yolcu_tip;
            String yolcu_ad;
            String yolcu_soyad;
            String yolcu_tc;
            String yolcu_tarih;
            String yolcu_koltuk;
            Boolean sonuc = false;
            for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                yolcu_koltuk = request.getParameter("yolcu_koltuk" + i);
                sonuc = rezervasyonDAO.koltukkontrol(ucus_id, yolcu_koltuk);
            }
            if (sonuc == true) {
                response.sendRedirect("rezervasyonislemlerim?durum=basarisiz");
            } else {
                for (int i = 1; i <= (c_sayi + y_sayi); i++) {
                    pnr_no = getAlphaNumericString(8);
                    yolcu_tip = Integer.parseInt(request.getParameter("yolcu_tip" + i));
                    yolcu_ad = new String((request.getParameter("yolcu_ad" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    yolcu_soyad = new String((request.getParameter("yolcu_soyad" + i)).getBytes("ISO-8859-1"), "UTF-8");
                    yolcu_tc = request.getParameter("yolcu_tc" + i);
                    yolcu_tarih = request.getParameter("yolcu_tarih" + i);
                    yolcu_koltuk = request.getParameter("yolcu_koltuk" + i);
                    Rezervasyon rezervasyon = new Rezervasyon(pnr_no, yolcu_ad, yolcu_soyad, yolcu_email, yolcu_tel, yolcu_tc, yolcu_tip, yolcu_koltuk, kullanici_id, ucus_id, yolcu_tarih, u_ucret);
                    rezervasyonDAO.rezervasyonekle(rezervasyon);
                }

                response.sendRedirect("rezervasyonislemlerim?durum=basarili");
            }
        }
    }

    private static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    private void rezervasyoniptal(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int rezervasyon_id = Integer.parseInt(request.getParameter("id"));
            rezervasyonDAO.rezervasyoniptal(rezervasyon_id);
            response.sendRedirect("rezervasyonliste");
        }
    }

    private void ucussorgulama(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int havaalani_kalkis_id = Integer.parseInt(request.getParameter("gidis"));
        int havaalani_varis_id = Integer.parseInt(request.getParameter("varis"));
        String ucus_tarih = request.getParameter("gidis_tarih");
        int yetiskin_sayi = Integer.parseInt(request.getParameter("yetiskin"));
        int cocuk_sayi = Integer.parseInt(request.getParameter("cocuk"));

        Rezervasyon rezervasyon = new Rezervasyon(havaalani_kalkis_id, havaalani_varis_id, ucus_tarih, yetiskin_sayi, cocuk_sayi);
        request.setAttribute("rezervasyon", rezervasyon);
        List<Rezervasyon> tekyonsorgula = rezervasyonDAO.tekyonsorgulama(rezervasyon);
        request.setAttribute("ucussorgulama", tekyonsorgula);
        List<Havaalani> havaalaniliste = havaalaniDAO.havaalaniliste();
        request.setAttribute("havaalaniliste", havaalaniliste);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ucussorgulama.jsp");
        dispatcher.forward(request, response);
    }

    private void rezervasyonlistele(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Rezervasyon> rezervasyonliste = rezervasyonDAO.rezervasyonlistele();
            request.setAttribute("rezervasyonliste", rezervasyonliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonlistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void rezervasyonolustur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris?rezervasyon=basarisiz");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 1) {
            response.sendRedirect("ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Rezervasyon ucusbilgileri = rezervasyonDAO.ucusbilgileri(id);
            request.setAttribute("ucusbilgileri", ucusbilgileri);

            List<Rezervasyon> koltuk = rezervasyonDAO.koltukbilgi(id);
            request.setAttribute("koltuk", koltuk);

            Rezervasyon koltuk_dolu = rezervasyonDAO.dolukoltuk(id);
            request.setAttribute("koltuk_dolu", koltuk_dolu);

            int yetiskin_sayi = Integer.parseInt(request.getParameter("yetiskin"));
            int cocuk_sayi = Integer.parseInt(request.getParameter("cocuk"));
            Rezervasyon yolcusayi = new Rezervasyon(yetiskin_sayi, cocuk_sayi);
            request.setAttribute("yolcusayi", yolcusayi);

            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonolustur.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void rezervasyonsorgulama(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String pnr_no = request.getParameter("pnr_no");
        String yolcu_soyad = new String((request.getParameter("yolcu_soyad")).getBytes("ISO-8859-1"), "UTF-8");
        Rezervasyon rezervasyongiris = new Rezervasyon(pnr_no, yolcu_soyad);
        request.setAttribute("rezervasyongiris", rezervasyongiris);

        Rezervasyon rezervasyonsec = rezervasyonDAO.rezervasyonsec(pnr_no, yolcu_soyad);
        request.setAttribute("rezervasyon", rezervasyonsec);

        if (rezervasyonsec == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonsorgulama.jsp?durum=basarisiz");
            dispatcher.forward(request, response);
        } else {
            Rezervasyon rezervasyonbilgi = rezervasyonDAO.rezervasyonbilgi(rezervasyonsec.getUcus_id(), rezervasyonsec.getRezervasyon_id());
            request.setAttribute("rezervasyonbilgi", rezervasyonbilgi);
            RequestDispatcher dispatcher = request.getRequestDispatcher("rezervasyonsorgulama.jsp");
            dispatcher.forward(request, response);
        }
    }
}
