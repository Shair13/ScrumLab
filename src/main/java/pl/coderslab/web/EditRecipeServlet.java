package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "EditRecipeServlet", value = "/app/recipe/edit")
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String REGEX = "[0-9]+";
        Pattern pattern = Pattern.compile(REGEX);
        String idParam = request.getParameter("id");
        if(!pattern.matcher(idParam).matches()){
            response.sendRedirect("/app/recipes");
        }else{
            int id = Integer.parseInt(idParam);
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(id);
            request.setAttribute("recipe", recipe);
            getServletContext().getRequestDispatcher("/app-edit-recipe.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idRecipe = Integer.parseInt(request.getParameter("id"));
        int idAdmin = Integer.parseInt(request.getParameter("idAdmin"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");
        String preparationTime = request.getParameter("preparationTime");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = new Recipe(name, ingredients, description, preparationTime, preparation, idAdmin, idRecipe);
        recipeDao.update(recipe);
        response.sendRedirect("/app/recipes");
    }
}