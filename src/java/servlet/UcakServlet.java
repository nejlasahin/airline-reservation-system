package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UcakDAO;
import javax.servlet.http.HttpSession;
import model.Firma;
import model.Ucak;

@WebServlet(urlPatterns = {"/admin/ucakliste", "/admin/ucakekle", "/admin/gosterucakekle", "/admin/ucaksil", "/admin/ucakguncelle", "/admin/gosterucakguncelle"})

public class UcakServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UcakDAO ucakDAO;

    public void init() {
        ucakDAO = new UcakDAO();
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
                case "/admin/ucakliste":
                    ucakliste(request, response);
                    break;
                case "/admin/ucakekle":
                    ucakekle(request, response);
                    break;
                case "/admin/gosterucakekle":
                    gosterucakekle(request, response);
                    break;
                case "/admin/ucaksil":
                    ucaksil(request, response);
                    break;
                case "/admin/ucakguncelle":
                    ucakguncelle(request, response);
                    break;
                case "/admin/gosterucakguncelle":
                    gosterucakguncelle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ucakliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Ucak> ucakliste = ucakDAO.ucaklistele();
            request.setAttribute("ucakliste", ucakliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucaklistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void ucakekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Firma> firma = ucakDAO.firma();
            request.setAttribute("firma", firma);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucakekle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterucakekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int firma_id = Integer.parseInt(request.getParameter("firma_id"));
            String ucak_ad = new String((request.getParameter("ucak_ad")).getBytes("ISO-8859-1"), "UTF-8");
            int ucak_koltuk = Integer.parseInt(request.getParameter("ucak_koltuk"));
            Ucak yeniucak = new Ucak(ucak_ad, ucak_koltuk, firma_id);
            ucakDAO.ucakekle(yeniucak);
            response.sendRedirect("ucakliste");
        }
    }

    private void ucaksil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int ucak_id = Integer.parseInt(request.getParameter("id"));
            ucakDAO.ucaksil(ucak_id);
            response.sendRedirect("ucakliste");
        }
    }

    private void ucakguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Ucak ucak = ucakDAO.ucaksec(id);
            List<Firma> firma = ucakDAO.firma();
            request.setAttribute("firma", firma);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ucakguncelle.jsp");
            request.setAttribute("ucak", ucak);
            dispatcher.forward(request, response);
        }
    }

    private void gosterucakguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int ucak_id = Integer.parseInt(request.getParameter("ucak_id"));
            int firma_id = Integer.parseInt(request.getParameter("firma_id"));
            int ucak_koltuk = Integer.parseInt(request.getParameter("ucak_koltuk"));
            String ucak_ad = new String((request.getParameter("ucak_ad")).getBytes("ISO-8859-1"), "UTF-8");
            Ucak ucak = new Ucak(ucak_id, ucak_ad, ucak_koltuk, firma_id);
            ucakDAO.ucakguncelle(ucak);
            response.sendRedirect("ucakliste");
        }
    }
}
