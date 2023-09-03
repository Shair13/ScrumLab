package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/list")
public class SchedulesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao planDao = new PlanDao();
        Admin admin = (Admin) session.getAttribute("user");
        int adminId = admin.getId();
        List<Plan> plansList = planDao.findAllByAdmin(adminId);
        request.setAttribute("plansList", plansList);
        getServletContext().getRequestDispatcher("/schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
