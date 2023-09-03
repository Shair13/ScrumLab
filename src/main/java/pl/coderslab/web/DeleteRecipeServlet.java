package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value = "/app/recipe/delete")
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.delete(recipeId);
        response.sendRedirect("/app/recipes");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}