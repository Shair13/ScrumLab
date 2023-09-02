package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/password/edit")
public class EditPasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-edit-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        if ("".equals(pass1) || !pass1.equals(pass2)) {
            response.sendRedirect("/app/password/edit");
        } else {
            HttpSession session = request.getSession();
            Admin currentUser = (Admin) session.getAttribute("user");
            int id = currentUser.getId();
            Admin newPassword = new Admin(pass1, id);
            AdminDao adminDao = new AdminDao();
            adminDao.updatePassword(newPassword);
            response.sendRedirect("/app/dashboard");
        }
    }
}
