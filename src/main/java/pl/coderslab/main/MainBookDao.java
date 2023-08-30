package pl.coderslab.main;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Book;
import pl.coderslab.model.DayName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/mainBookDao")
public class MainBookDao extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        BookDao bookDao = new BookDao();

        Book newBook = new Book("Book2", "Author2", "1111");
        Book createBook = bookDao.create(newBook);

        List<Book> bookList = bookDao.findAll();
        for (Book book : bookList) {
            writer.append(book.toString()).append("<br>");
        }

        bookDao.delete(createBook.getId());
        writer.append("<br>");

        bookList = bookDao.findAll();
        for (Book book : bookList) {
            writer.append(book.toString()).append("<br>");
        }

////        Utwórz nowego admina
//        Admin newAdmin = new Admin("Jan", "Kowalski", "jan.kowalski@gmail.com",
//                "haslo1", 0, 1);
//        Admin createAdmin = adminDao.create(newAdmin);
//        int adminId = createAdmin.getId();
//
//
////        Odczyaj dane admina
//        Admin readAdmin = adminDao.read(adminId);
//        writer.append(readAdmin.toString()).append("<br>");
//        writer.append("<br>");
//
////        Zaktualizuj dane admina
//        readAdmin.setFirstName("Zbigniew");
//        readAdmin.setLastName("Nowak");
//        readAdmin.setPassword("admin");
//        adminDao.update(readAdmin);
//        writer.append(readAdmin.toString()).append("<br>");
//        writer.append("<br>");
//
////        Odczytaj wszystkich adminów
//        List<Admin> adminsList = adminDao.findAll();;
//        for (Admin admin : adminsList) {
//            writer.append(admin.toString()).append("<br>");
//        }
//        writer.append("<br>");
//
////        Usuń admina
//        writer.append(adminId + "").append("<br>");
//        adminDao.delete(adminId);
//
////        Odczytaj wszystkich adminów po usunięciu
//        adminsList = adminDao.findAll();;
//        for (Admin admin1 : adminsList) {
//            writer.append(admin1.toString()).append("<br>");
    }
}
