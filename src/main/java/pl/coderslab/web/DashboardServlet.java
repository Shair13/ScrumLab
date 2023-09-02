package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DashboardServlet", value = "/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        Admin admin = (Admin) session.getAttribute("user");

        int adminId = admin.getId();
        int plansCount = planDao.getRecipesCountByAdmin(adminId);
        int recipeCount = recipeDao.countRecipeByAdmin(adminId);

        request.setAttribute("plansCount", plansCount);
        request.setAttribute("recipeCount", recipeCount);

        List<RecipePlan> recipePlans = recipePlanDao.findLastPlan(adminId);
        processRecipePlan(request, response, recipePlans);

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }

    protected static void processRecipePlan(HttpServletRequest request, HttpServletResponse response,
                                            List<RecipePlan> recipePlanList) {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();
        
        Plan plan;
        boolean planExists;
        if (recipePlanList.size() != 0) {
            plan = planDao.readPlan(recipePlanList.get(0).getPlanId());
            planExists = true;
        } else {
            return;
        }

        List<DayName> planDays = new ArrayList<>();
        List<List<RecipePlan>> planMeals = new ArrayList<>();
        List<List<Recipe>> planRecipes = new ArrayList<>();

        int dayNameId = 0;
        List<RecipePlan> currentDayMeals = new ArrayList<>();
        List<Recipe> currentDayRecipes = new ArrayList<>();

        for (RecipePlan recipePlan : recipePlanList) {
            int currentDayNameId = recipePlan.getDayNameId();
            if (dayNameId != currentDayNameId) {
                planDays.add(dayNameDao.read(recipePlan.getDayNameId()));
                currentDayRecipes = new ArrayList<>();
                currentDayMeals = new ArrayList<>();
                planMeals.add(currentDayMeals);
                planRecipes.add(currentDayRecipes);
            }
            currentDayMeals.add(recipePlan);
            currentDayRecipes.add(recipeDao.read(recipePlan.getRecipeId()));
            dayNameId = currentDayNameId;
        }

        request.setAttribute("planExists", planExists);
        request.setAttribute("plan", plan);
        request.setAttribute("planDays", planDays);
        request.setAttribute("planMeals", planMeals);
        request.setAttribute("planRecipes", planRecipes);
    }
}