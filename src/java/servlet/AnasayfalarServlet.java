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

@WebServlet(urlPatterns = {"/ucakbileti", "/admin/panel"})

public class AnasayfalarServlet extends HttpServlet{
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
                case "/ucakbileti":
                    ucakbileti(request, response);
                    break; 
                case "/admin/panel":
                    panel(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void ucakbileti(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        List<Havaalani> havaalaniliste = havaalaniDAO.havaalaniliste();
        request.setAttribute("havaalaniliste", havaalaniliste);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");      
        dispatcher.forward(request, response);
    }
    
    private void panel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
                response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
                response.sendRedirect("../ucakbileti");
        }else{
            List<Rezervasyon> rezervasyon = rezervasyonDAO.rezervasyonsayisi();
            request.setAttribute("rezervasyon", rezervasyon);

            List<Rezervasyon> ucus = rezervasyonDAO.ucussayisi();
            request.setAttribute("ucus", ucus);

            List<Rezervasyon> mesaj = rezervasyonDAO.mesajsayisi();
            request.setAttribute("mesaj", mesaj);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
