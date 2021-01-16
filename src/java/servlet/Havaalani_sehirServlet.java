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

import dao.Havaalani_sehirDAO;
import javax.servlet.http.HttpSession;
import model.Havaalani_sehir;

@WebServlet(urlPatterns = {"/admin/sehirliste", "/admin/sehirsil", "/admin/sehirekle", "/admin/gostersehirekle", "/admin/sehirguncelle", "/admin/gostersehirguncelle"})

public class Havaalani_sehirServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Havaalani_sehirDAO havaalani_sehirDAO;

    public void init() {
        havaalani_sehirDAO = new Havaalani_sehirDAO();
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
                case "/admin/sehirliste":
                    sehirliste(request, response);
                    break;
                case "/admin/sehirekle":
                    sehirekle(request, response);
                    break;
                case "/admin/gostersehirekle":
                    gostersehirekle(request, response);
                    break;
                case "/admin/sehirguncelle":
                    sehirguncelle(request, response);
                    break;
                case "/admin/gostersehirguncelle":
                    gostersehirguncelle(request, response);
                    break;
                case "/admin/sehirsil":
                    sehirsil(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void sehirliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Havaalani_sehir> sehirliste = havaalani_sehirDAO.sehirlistele();
            request.setAttribute("sehirliste", sehirliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("sehirlistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void sehirekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("sehirekle.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void gostersehirekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            String havaalani_sehir_ad = new String((request.getParameter("havaalani_sehir_ad")).getBytes("ISO-8859-1"), "UTF-8");
            Havaalani_sehir yenisehir = new Havaalani_sehir(havaalani_sehir_ad);
            havaalani_sehirDAO.sehirekle(yenisehir);
            response.sendRedirect("sehirliste");
        }
    }

    private void sehirsil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int havaalani_sehir_id = Integer.parseInt(request.getParameter("id"));
            havaalani_sehirDAO.sehirsil(havaalani_sehir_id);
            response.sendRedirect("sehirliste");
        }
    }

    private void sehirguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Havaalani_sehir sehir = havaalani_sehirDAO.sehirsec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("sehirguncelle.jsp");
            request.setAttribute("sehir", sehir);
            dispatcher.forward(request, response);
        }
    }

    private void gostersehirguncelle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int havaalani_sehir_id = Integer.parseInt(request.getParameter("havaalani_sehir_id"));
            String havaalani_sehir_ad = new String((request.getParameter("havaalani_sehir_ad")).getBytes("ISO-8859-1"), "UTF-8");
            Havaalani_sehir sehir = new Havaalani_sehir(havaalani_sehir_id, havaalani_sehir_ad);
            havaalani_sehirDAO.sehirguncelle(sehir);
            response.sendRedirect("sehirliste");
        }
    }
}
