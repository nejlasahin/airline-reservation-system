package servlet;

import dao.CevapDAO;
import dao.MesajDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cevap;
import model.Mesaj;

@WebServlet(urlPatterns = {"/admin/mesajcevapla", "/admin/gostermesajcevapla", "/admin/cevapliste", "/admin/cevapsil", "/admin/cevapincele"})

public class CevapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CevapDAO cevapDAO;
    private MesajDAO mesajDAO;
    public void init() {
        cevapDAO = new CevapDAO();
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
                case "/admin/mesajcevapla":
                    mesajcevapla(request, response);
                    break;
                case "/admin/gostermesajcevapla":
                    gostermesajcevapla(request, response);
                    break;
                case "/admin/cevapliste":
                    cevapliste(request, response);
                    break;
                case "/admin/cevapsil":
                    cevapsil(request, response);
                    break;  
                case "/admin/cevapincele":
                    cevapincele(request, response);
                    break;  
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void cevapliste(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            List<Cevap> cevapliste = cevapDAO.cevaplistele();
            request.setAttribute("cevapliste", cevapliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cevaplistele.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void cevapincele(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int cevap_id = Integer.parseInt(request.getParameter("id"));
            Cevap cevap = cevapDAO.cevapincele(cevap_id);
            request.setAttribute("cevap", cevap);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cevapincele.jsp");
            dispatcher.forward(request, response);
        }       
    }
    
    private void cevapsil(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int cevap_id = Integer.parseInt(request.getParameter("id"));
            cevapDAO.cevapsil(cevap_id);
            response.sendRedirect("cevapliste");
        } 
    }
    
    private void mesajcevapla(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            mesajDAO.mesajokunma(id);
            Mesaj mesaj = cevapDAO.mesajsec(id);      
            request.setAttribute("mesaj", mesaj); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("mesajcevap.jsp");      
            dispatcher.forward(request, response);
        }
    }
    
    private void gostermesajcevapla(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession sessionn = request.getSession();
        if ((Integer) sessionn.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) sessionn.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int mesaj_id = Integer.parseInt(request.getParameter("mesaj_id"));
            String mesaj_email = request.getParameter("mesaj_email");
            String cevap_baslik = new String((request.getParameter("cevap_baslik")).getBytes("ISO-8859-1"), "UTF-8");
            String cevap_icerik = new String((request.getParameter("cevap_icerik")).getBytes("ISO-8859-1"), "UTF-8");
            Cevap yenicevap = new Cevap(mesaj_id,cevap_icerik,cevap_baslik);

            final String to = mesaj_email; 
            final String subject = cevap_baslik;
            final String messg = cevap_icerik;
            final String from = "mail@gmail.com";
            final String pass = "sifre";

            Properties props = new Properties();    
            props.put("mail.smtp.host", "smtp.gmail.com");    
            props.put("mail.smtp.socketFactory.port", "465");    
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
            props.put("mail.smtp.auth", "true");    
            props.put("mail.smtp.port", "465");     
            Session session = Session.getDefaultInstance(props,    
            new javax.mail.Authenticator() {    
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {    
                    return new PasswordAuthentication(from,pass);  
                }    
            });       
            try {    
               MimeMessage message = new MimeMessage(session);
               message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
               message.setSubject(subject, "UTF-8");    
               message.setText(messg, "UTF-8");    
               Transport.send(message);    
            } catch (MessagingException e) {throw new RuntimeException(e);

            }        
            mesajDAO.mesajcevap(mesaj_id);
            cevapDAO.cevapekle(yenicevap);
            response.sendRedirect("cevapliste");
        }    
    }
}
    

