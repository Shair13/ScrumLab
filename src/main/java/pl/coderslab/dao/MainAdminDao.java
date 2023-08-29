package pl.coderslab.dao;

import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/mainAdminDao")
public class MainAdminDao extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        AdminDao adminDao = new AdminDao();

//        Utwórz nowego admina
        Admin newAdmin = new Admin("Jan", "Kowalski", "jan.kowalski@gmail.com",
                "haslo1", 0, 1);
        Admin createAdmin = adminDao.create(newAdmin);
        int adminId = createAdmin.getId();

//        Odczyaj dane admina
        Admin readAdmin = adminDao.read(adminId);
        writer.append(readAdmin.toString()).append("<br>");
        writer.append("<br>");

//        Zaktualizuj dane admina
        readAdmin.setFirstName("Zbigniew");
        readAdmin.setLastName("Nowak");
        readAdmin.setPassword("admin");
        adminDao.update(readAdmin);
        writer.append(readAdmin.toString()).append("<br>");
        writer.append("<br>");

//        Odczytaj wszystkich adminów
        List<Admin> adminsList = adminDao.findAll();;
        for (Admin admin : adminsList) {
            writer.append(admin.toString()).append("<br>");
        }
        writer.append("<br>");

//        Usuń admina
        adminDao.delete(adminId);

//        Odczytaj wszystkich adminów po usunięciu
        adminsList = adminDao.findAll();;
        for (Admin admin1 : adminsList) {
            writer.append(admin1.toString()).append("<br>");
        }
    }
}