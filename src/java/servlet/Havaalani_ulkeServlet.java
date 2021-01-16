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
import javax.servlet.http.HttpSession;

import dao.Havaalani_ulkeDAO;
import model.Havaalani_ulke;

@WebServlet(urlPatterns = {"/admin/ulkeliste", "/admin/ulkesil", "/admin/ulkeekle", "/admin/ulkeguncelle", "/admin/gosterulkeguncelle", "/admin/gosterulkeekle"})

public class Havaalani_ulkeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Havaalani_ulkeDAO havaalani_ulkeDAO;

    public void init() {
        havaalani_ulkeDAO = new Havaalani_ulkeDAO();
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
                case "/admin/ulkeliste":
                    ulkeliste(request, response);
                    break;
                case "/admin/ulkeekle":
                    ulkeekle(request, response);
                    break;
                case "/admin/gosterulkeekle":
                    gosterulkeekle(request, response);
                    break;
                case "/admin/ulkeguncelle":
                    ulkeguncelle(request, response);
                    break;
                case "/admin/gosterulkeguncelle":
                    gosterulkeguncelle(request, response);
                    break;
                case "/admin/ulkesil":
                    ulkesil(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ulkeliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Havaalani_ulke> ulkeliste = havaalani_ulkeDAO.ulkelistele();
            request.setAttribute("ulkeliste", ulkeliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ulkelistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void ulkeekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("ulkeekle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gosterulkeekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            String havaalani_ulke_ad = new String((request.getParameter("havaalani_ulke_ad")).getBytes("ISO-8859-1"), "UTF-8");
            Havaalani_ulke yeniulke = new Havaalani_ulke(havaalani_ulke_ad);
            havaalani_ulkeDAO.ulkeekle(yeniulke);
            response.sendRedirect("ulkeliste");
        }
    }

    private void ulkeguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Havaalani_ulke ulke = havaalani_ulkeDAO.ulkesec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ulkeguncelle.jsp");
            request.setAttribute("ulke", ulke);
            dispatcher.forward(request, response);
        }
    }

    private void gosterulkeguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int havaalani_ulke_id = Integer.parseInt(request.getParameter("havaalani_ulke_id"));
            String havaalani_ulke_ad = new String((request.getParameter("havaalani_ulke_ad")).getBytes("ISO-8859-1"), "UTF-8");
            Havaalani_ulke ulke = new Havaalani_ulke(havaalani_ulke_id, havaalani_ulke_ad);
            havaalani_ulkeDAO.ulkeguncelle(ulke);
            response.sendRedirect("ulkeliste");
        }
    }

    private void ulkesil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int havaalani_ulke_id = Integer.parseInt(request.getParameter("id"));
            havaalani_ulkeDAO.ulkesil(havaalani_ulke_id);
            response.sendRedirect("ulkeliste");
        }
    }
}
