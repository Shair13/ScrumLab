package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
i//package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/mainDayNameDao")
public class MainDayNameDao extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        DayNameDao dayNameDao = new DayNameDao();

//        Utwórz nowego admina
        DayName newDayName = new DayName("Wtorek",1);
        DayName createDayName = dayNameDao.create(newDayName);
        int dayNameId = createDayName.getId();

//        Odczyaj dane admina
        DayName readDayName = dayNameDao.read(dayNameId);
        writer.append(readDayName.toString()).append("<br>");
        writer.append("<br>");

//        Zaktualizuj dane admina
        readDayName.setName("Środa");
        readDayName.setDisplayOrder(0);
        dayNameDao.update(readDayName);
        writer.append(readDayName.toString()).append("<br>");
        writer.append("<br>");

//        Odczytaj wszystkich adminów
        List<DayName> dayNamesList = dayNameDao.findAll();;
        for (DayName admin : dayNamesList) {
            writer.append(admin.toString()).append("<br>");
        }
        writer.append("<br>");

////        Usuń admina
        dayNameDao.delete(dayNameId);

//        Odczytaj wszystkich adminów po usunięciu
        dayNamesList = dayNameDao.findAll();;
        for (DayName admin1 : dayNamesList) {
            writer.append(admin1.toString()).append("<br>");
        }
    }
}mport java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/mainDayNameDao")
public class MainDayNameDao extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        DayNameDao dayNameDao = new DayNameDao();

//        Utwórz nowego admina
        DayName newDayName = new DayName("Wtorek",1);
        DayName createDayName = dayNameDao.create(newDayName);
        int dayNameId = createDayName.getId();

//        Odczyaj dane admina
        DayName readDayName = dayNameDao.read(dayNameId);
        writer.append(readDayName.toString()).append("<br>");
        writer.append("<br>");

//        Zaktualizuj dane admina
        readDayName.setName("Środa");
        readDayName.setDisplayOrder(0);
        dayNameDao.update(readDayName);
        writer.append(readDayName.toString()).append("<br>");
        writer.append("<br>");

//        Odczytaj wszystkich adminów
        List<DayName> dayNamesList = dayNameDao.findAll();;
        for (DayName admin : dayNamesList) {
            writer.append(admin.toString()).append("<br>");
        }
        writer.append("<br>");

////        Usuń admina
        dayNameDao.delete(dayNameId);

//        Odczytaj wszystkich adminów po usunięciu
        dayNamesList = dayNameDao.findAll();;
        for (DayName admin1 : dayNamesList) {
            writer.append(admin1.toString()).append("<br>");
        }
    }
}