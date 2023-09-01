package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.RecipePlan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();
        Admin admin = (Admin)session.getAttribute("user");
        request.setAttribute("plans", planDao.findAllByAdmin(admin.getId()));
        request.setAttribute("recipes", recipeDao.findAllByAdmin(admin.getId()));
        request.setAttribute("days", dayNameDao.findAll());
        getServletContext().getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        int recipeId = Integer.parseInt(request.getParameter("recipePlanRecipeId"));
        String mealName = request.getParameter("recipePlanMealName");
        int displayOrder = Integer.parseInt(request.getParameter("recipePlanDisplayOrder"));
        int dayNameId = Integer.parseInt(request.getParameter("recipePlanDayNameId"));
        int planId = Integer.parseInt(request.getParameter("recipePlanPlanId"));
        recipePlanDao.create(new RecipePlan(recipeId, mealName, displayOrder, dayNameId, planId));
        response.sendRedirect(request.getContextPath() + "/app/dashboard");
    }
}