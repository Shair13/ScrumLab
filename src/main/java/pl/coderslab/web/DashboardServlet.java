package pl.coderslab.web;

import pl.coderslab.dao.*;
import pl.coderslab.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DashboardServlet", value = "/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        Admin admin = (Admin) session.getAttribute("user");

        int adminId = admin.getId();
        int plansCount = planDao.getRecipesCountByAdmin(adminId);
        int recipeCount = recipeDao.countRecipeByAdmin(adminId);

        List<RecipePlan> recipePlans = recipePlanDao.findLastPlan(adminId);

        String latestPlanName = "Brak";
        if(recipePlans.size() != 0){
            latestPlanName = planDao.readPlan(recipePlans.get(0).getPlanId()).getName();
        }

        List<DayName> latestPlanDays = new ArrayList<>();
        List<List<RecipePlan>> latestPlanMeals = new ArrayList<>();
        List<List<Recipe>> latestPlanRecipes = new ArrayList<>();

        int dayNameId = 0;
        List<RecipePlan> currentDayNameMeals = new ArrayList<>();
        List<Recipe> currentDayNameRecipes = new ArrayList<>();

        for (RecipePlan recipePlan : recipePlans) {
            int currentDayNameId = recipePlan.getDayNameId();
            if (dayNameId != currentDayNameId) {
                latestPlanDays.add(dayNameDao.read(recipePlan.getDayNameId()));
                currentDayNameRecipes = new ArrayList<>();
                currentDayNameMeals = new ArrayList<>();
                latestPlanMeals.add(currentDayNameMeals);
                latestPlanRecipes.add(currentDayNameRecipes);
            }
            currentDayNameMeals.add(recipePlan);
            currentDayNameRecipes.add(recipeDao.read(recipePlan.getRecipeId()));
            dayNameId = currentDayNameId;
        }

        request.setAttribute("plansCount", plansCount);
        request.setAttribute("recipeCount", recipeCount);
        request.setAttribute("latestPlanName", latestPlanName);
        request.setAttribute("latestPlanDays", latestPlanDays);
        request.setAttribute("latestPlanMeals", latestPlanMeals);
        request.setAttribute("latestPlanRecipes", latestPlanRecipes);
        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }
}