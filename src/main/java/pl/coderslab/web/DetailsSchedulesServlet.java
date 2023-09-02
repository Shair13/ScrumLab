package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Plan;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/app/plan/details")
public class DetailsSchedulesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        int planId = Integer.parseInt(request.getParameter("planId"));
        Plan plan = planDao.readPlan(planId);
        request.setAttribute("plan", plan);

        List<RecipePlan> recipePlanList = recipePlanDao.findAllByPlan(planId);
        DashboardServlet.processRecipePlan(request, response, recipePlanList);

        getServletContext().getRequestDispatcher("/detailsSchedules.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}