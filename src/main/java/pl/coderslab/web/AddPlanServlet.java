package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "AddPlanServlet", value = "/app/plan/add")
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/addplanApp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();
        
        Admin user = (Admin) session.getAttribute("user");
        PlanDao planDao = new PlanDao();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Plan newPlan = new Plan(name, description, timestamp, user.getId());
        planDao.createPlan(newPlan);
        response.sendRedirect("/app/schedules");
    }
}