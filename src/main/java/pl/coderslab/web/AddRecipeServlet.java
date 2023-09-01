package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/app/recipe/add")
public class AddRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-add-recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("user");

        RecipeDao recipeDao = new RecipeDao();
        recipeDao.create(new Recipe(request.getParameter("name"), request.getParameter("ingredients"),
                request.getParameter("description"), request.getParameter("preparationTime"), request.getParameter("preparation"), admin.getId()));
        response.sendRedirect("/app/dashboard");


    }
}