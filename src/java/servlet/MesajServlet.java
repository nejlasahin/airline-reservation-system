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

import dao.MesajDAO;
import javax.servlet.http.HttpSession;
import model.Mesaj;

@WebServlet(urlPatterns = {"/admin/mesajliste", "/admin/mesajsil", "/iletisim", "/gostermesajekle"})

public class MesajServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MesajDAO mesajDAO;

    public void init() {
        mesajDAO = new MesajDAO();
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
                case "/admin/mesajliste":
                    mesajliste(request, response);
                    break;
                case "/admin/mesajsil":
                    mesajsil(request, response);
                    break;
                case "/iletisim":
                    mesajekle(request, response);
                    break;
                case "/gostermesajekle":
                    gostermesajekle(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void mesajliste(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            List<Mesaj> mesajliste = mesajDAO.mesajlistele();
            request.setAttribute("mesajliste", mesajliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("mesajlistele.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void mesajsil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        } else if ((Integer) session.getAttribute("kullanici_yetki") != 2) {
            response.sendRedirect("../ucakbileti");
        } else {
            int mesaj_id = Integer.parseInt(request.getParameter("id"));
            mesajDAO.mesajsil(mesaj_id);
            response.sendRedirect("mesajliste");
        }
    }

    private void mesajekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("iletisim.jsp");
        dispatcher.forward(request, response);
    }

    private void gostermesajekle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String mesaj_adsoyad = new String((request.getParameter("mesaj_adsoyad")).getBytes("ISO-8859-1"), "UTF-8");
        String mesaj_email = request.getParameter("mesaj_email");
        String mesaj_konu = new String((request.getParameter("mesaj_konu")).getBytes("ISO-8859-1"), "UTF-8");
        String mesaj_icerik = new String((request.getParameter("mesaj_icerik")).getBytes("ISO-8859-1"), "UTF-8");
        Mesaj yenimesaj = new Mesaj(mesaj_adsoyad, mesaj_email, mesaj_konu, mesaj_icerik);
        mesajDAO.mesajekle(yenimesaj);
        response.sendRedirect("iletisim?durum=basarili");
    }
}
