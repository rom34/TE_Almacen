package com.emergentes.controlador;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ProductoDAO dao=new ProductoDAOimpl();
            int id;
            Producto pro=new Producto();
            String action=(request.getParameter("action")!=null)?request.getParameter("action"):"view";
        switch(action){
            case "add":
                request.setAttribute("producto",pro);
                request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                break;
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                pro=dao.getById(id);
               
                request.setAttribute("producto", pro);
                request.getRequestDispatcher("frmproducto.jsp").forward(request, response);
                break;
            case "delete":
                id=Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
                request.getRequestDispatcher("Inicio").forward(request, response);        
                break;   
            default: 
                List<Producto> lista =dao.getAll();
                request.setAttribute("productos",lista);
                request.getRequestDispatcher("listado.jsp").forward(request, response);            
                break;                
            }
        } catch (Exception e) {
            System.out.println("error  "+ e.getMessage());
            
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoDAO dao=new ProductoDAOimpl();
        int id=Integer.parseInt(request.getParameter("id"));
        String nombre_producto=request.getParameter("nombre_producto");
        double precio=Double.parseDouble(request.getParameter("precio"));
        int stock=Integer.parseInt(request.getParameter("stock"));
        Producto pro=new Producto();
        pro.setId(id);
        pro.setNombre_producto(nombre_producto);
        pro.setPrecio(precio);
        pro.setStock(stock);
        
        if(id==0){
            try{
                
                dao.insert(pro);
                response.sendRedirect("Inicio");
            }catch(Exception e) {
            System.out.println("error  "+ e.getMessage());
            
            }
        }
        else{
            try{
                dao.update(pro);
                response.sendRedirect("Inicio");
            }catch(Exception e) {
            System.out.println("error  "+ e.getMessage());
            
            }
        }
    }
        
}
