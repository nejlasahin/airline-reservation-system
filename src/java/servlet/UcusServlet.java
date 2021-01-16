package servlet;

import dao.UcusDAO;
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

import model.Firma;
import model.Havaalani;
import model.Ucak;
import model.Ucus;

@WebServlet(urlPatterns = {"/admin/ucussil", "/admin/ucusolustur", "/admin/gosterucusolustur", "/admin/guncelucusliste", "/admin/gecmisucusliste", "/admin/ucusguncelle", "/admin/gosterucusguncelle"})

public class UcusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UcusDAO ucusDAO;

    public void init() {
        ucusDAO = new UcusDAO();
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
                case "/admin/ucusolustur":
                    ucusolustur(request, response);
                    break;
                case "/admin/gosterucusolustur":
                    gosterucusolustur(request, response);
                    break;
                case "/admin/guncelucusliste":
                    guncelucusliste(request, response);
                    break;
                case "/admin/gecmisucusliste":
                    gecmisucusliste(request, response);
                    break;
                case "/admin/ucussil":
                    ucussil(request, response);
                    break;
                case "/admin/ucusguncelle":
                    ucusguncelle(request, response);
                    break;
                case "/admin/gosterucusguncelle":
                    gosterucusguncelle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ucussil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int ucus_id = Integer.parseInt(request.getParameter("id"));
            ucusDAO.ucussil(ucus_id);
            response.sendRedirect("guncelucusliste");
        }
    }

    private void guncelucusliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Ucus> guncelucusliste = ucusDAO.guncelucusliste();
            request.setAttribute("guncelucusliste", guncelucusliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("guncelucuslarilistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gecmisucusliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Ucus> gecmisucusliste = ucusDAO.gecmisucusliste();
            request.setAttribute("gecmisucusliste", gecmisucusliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("gecmisucuslarilistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void ucusolustur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Havaalani> havaalani = ucusDAO.havaalani();
            request.setAttribute("havaalani", havaalani);

            List<Firma> firma = ucusDAO.firma();
            request.setAttribute("firma", firma);

            List<Ucak> ucak = ucusDAO.ucak();
            request.setAttribute("ucak", ucak);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ucusolustur.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterucusolustur(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int ucus_kalkis_id = Integer.parseInt(request.getParameter("ucus_kalkis_id"));
            int ucus_varis_id = Integer.parseInt(request.getParameter("ucus_varis_id"));
            String ucus_tarih = request.getParameter("ucus_tarih");
            String ucus_saat = request.getParameter("ucus_saat");
            String ucus_sure = request.getParameter("ucus_sure");
            int firma_id = Integer.parseInt(request.getParameter("firma_id"));
            int ucak_id = Integer.parseInt(request.getParameter("ucak_id"));
            double ucus_ucret = Double.parseDouble(request.getParameter("ucus_ucret"));

            Ucus yeniucus = new Ucus(ucus_kalkis_id, ucus_varis_id, ucus_tarih, ucus_saat, ucus_sure, firma_id, ucak_id, ucus_ucret);
            Boolean sonuc = ucusDAO.ucuskontrol(yeniucus);
            if (sonuc == false) {
                response.sendRedirect("guncelucusliste?durum=basarisiz");
            } else {
                ucusDAO.ucusolustur(yeniucus);
                response.sendRedirect("guncelucusliste");
            }
        }
    }

    private void ucusguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Ucus ucus = ucusDAO.ucussec(id);
            List<Firma> firma = ucusDAO.firma();
            request.setAttribute("firma", firma);
            List<Ucak> ucak = ucusDAO.ucak();
            request.setAttribute("ucak", ucak);
            List<Havaalani> havaalani = ucusDAO.havaalani();
            request.setAttribute("havaalani", havaalani);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucusguncelle.jsp");
            request.setAttribute("ucus", ucus);
            dispatcher.forward(request, response);
        }
    }

    private void gosterucusguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int ucus_id = Integer.parseInt(request.getParameter("ucus_id"));
            int ucus_kalkis_id = Integer.parseInt(request.getParameter("ucus_kalkis_id"));
            int ucus_varis_id = Integer.parseInt(request.getParameter("ucus_varis_id"));
            String ucus_tarih = request.getParameter("ucus_tarih");
            String ucus_saat = request.getParameter("ucus_saat");
            String ucus_sure = request.getParameter("ucus_sure");
            int firma_id = Integer.parseInt(request.getParameter("firma_id"));
            int ucak_id = Integer.parseInt(request.getParameter("ucak_id"));
            Double ucus_ucret = Double.parseDouble(request.getParameter("ucus_ucret"));
            Ucus ucus = new Ucus(ucus_id, ucus_kalkis_id, ucus_varis_id, ucus_tarih, ucus_saat, ucus_sure, firma_id, ucak_id, ucus_ucret);
            ucusDAO.ucusguncelle(ucus);
            response.sendRedirect("guncelucusliste");
        }
    }

}
