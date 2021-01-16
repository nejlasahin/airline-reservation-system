package servlet;

import dao.FirmaDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Firma;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = {"/admin/firmaliste", "/admin/firmaekle", "/admin/gosterfirmaekle", "/admin/firmasil", "/admin/firmaguncelle", "/admin/gosterfirmaguncelle"})

public class FirmaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FirmaDAO firmaDAO;

    public void init() {
        firmaDAO = new FirmaDAO();
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
                case "/admin/firmaliste":
                    firmaliste(request, response);
                    break;
                case "/admin/firmaekle":
                    firmaekle(request, response);
                    break;
                case "/admin/gosterfirmaekle":
                   gosterfirmaekle(request, response);
                    break;
                case "/admin/firmaguncelle":
                    firmaguncelle(request, response);
                    break;
                case "/admin/gosterfirmaguncelle":
                   gosterfirmaguncelle(request, response);
                    break; 
                case "/admin/firmasil":
                   firmasil(request, response);
                    break;      
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void firmaliste(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            List<Firma> firmaliste = firmaDAO.firmalistele();
            request.setAttribute("firmaliste", firmaliste);
            RequestDispatcher dispatcher = request.getRequestDispatcher("firmalistele.jsp");
            dispatcher.forward(request, response);            
        }
    }
    
    private void firmaekle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("firmaekle.jsp");      
        dispatcher.forward(request, response);
        }
    }  
    
    private void gosterfirmaekle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            String firma_logo = null;
            String firma_ad = null;

            response.setContentType("text/html; charset=UTF-8");

            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent) {
                return;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List< FileItem> fields = upload.parseRequest(request);
                Iterator< FileItem> it = fields.iterator();
                if (!it.hasNext()) {
                    return;
                }

                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                        if (firma_ad == null) {
                            if (fileItem.getFieldName().equals("firma_ad")) {
                                firma_ad = fileItem.getString("UTF-8");
                            }
                        }
                    } else {
                        if (fileItem.getSize() > 0) {
                            firma_logo = fileItem.getName();
                            fileItem.write(new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + firma_logo));
                            
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Firma yenifirma = new Firma(firma_ad, firma_logo);
            firmaDAO.firmaekle(yenifirma);
            response.sendRedirect("firmaliste");
        }       
    }   
    
    private void firmaguncelle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int id = Integer.parseInt(request.getParameter("id"));
            Firma firma = firmaDAO.firmasec(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("firmaguncelle.jsp");
            request.setAttribute("firma", firma);
            dispatcher.forward(request, response);
        }
        
    }
    
    private void gosterfirmaguncelle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            String logo = null;
            String firma_logo = null;
            String firma_ad = null;
            int firma_id = 0;

            response.setContentType("text/html; charset=UTF-8");
            boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
            if (!isMultipartContent) {
                return;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            try {
                List< FileItem> fields = upload.parseRequest(request);
                Iterator< FileItem> it = fields.iterator();
                if (!it.hasNext()) {
                    return;
                }

                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                        if (firma_ad == null) {
                            if (fileItem.getFieldName().equals("firma_ad")) {
                                firma_ad = fileItem.getString("UTF-8");
                            }
                        }
                        if (logo == null) {
                            if (fileItem.getFieldName().equals("logo")) {
                                logo = fileItem.getString("UTF-8");
                            }
                        }
                        if (firma_id == 0) {
                            if (fileItem.getFieldName().equals("firma_id")) {
                                firma_id = Integer.parseInt(fileItem.getString());
                            }
                        }
                    } else {
                        if (fileItem.getSize() > 0) {
                            firma_logo = fileItem.getName();
                            fileItem.write(new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + firma_logo));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            File f = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + logo);
            f.delete();
            Firma firma = new Firma(firma_id, firma_ad, firma_logo);
            firmaDAO.firmaguncelle(firma);
            response.sendRedirect("firmaliste");
        }
    }
    
    private void firmasil(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        HttpSession session = request.getSession();
        if ((Integer) session.getAttribute("kullanici_yetki") == null) {
            response.sendRedirect("giris");
        }else if((Integer) session.getAttribute("kullanici_yetki") != 2){
            response.sendRedirect("../ucakbileti");
        }else{
            int firma_id = Integer.parseInt(request.getParameter("id"));
            String firma_logo = request.getParameter("logo");
            File f = new File("C:\\Users\\Asus\\Documents\\NetBeansProjects\\hawkeye\\web\\assets\\data\\" + firma_logo);
            f.delete();
            firmaDAO.firmasil(firma_id);
            response.sendRedirect("firmaliste");
        }        
    }
}
