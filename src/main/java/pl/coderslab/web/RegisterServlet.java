package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] passwords = request.getParameterValues("password");
        if(passwords[0].equals(passwords[1])){
            AdminDao adminDao = new AdminDao();
            adminDao.create(new Admin(request.getParameter("firstName"), request.getParameter("lastName"),
                    request.getParameter("email"), passwords[0], 0, 1));
            getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

        }
        else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Nie tak predko cwaniaczku z miodkiem w uszach');");
            out.println("</script>");
        }




    }
}