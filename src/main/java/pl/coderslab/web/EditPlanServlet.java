package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditPlanServlet", value = "/app/plan/edit")
public class EditPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.readPlan(id);
        request.setAttribute("plan", plan);
        getServletContext().getRequestDispatcher("/app-edit-schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.readPlan(id);
        plan.setName(name);
        plan.setDescription(description);
        planDao.update(plan);
        response.sendRedirect("/app/plan/list");
    }
}