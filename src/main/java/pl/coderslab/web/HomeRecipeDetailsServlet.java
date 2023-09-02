package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "HomeRecipeDetailsServlet", value = "/recipe/details")
public class HomeRecipeDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String REGEX = "[0-9]+";
        Pattern pattern = Pattern.compile(REGEX);

        if (idParam == null) {
            response.sendRedirect("/recipes");
        } else if (pattern.matcher(idParam).matches()) {

            int id = Integer.parseInt(idParam);
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(id);
            String[] ingredientsArr = recipe.getIngredients().split("\\n");
            request.setAttribute("ingredients", ingredientsArr);
            request.setAttribute("recipe", recipe);
            getServletContext().getRequestDispatcher("/recipe-datails.jsp").forward(request, response);

        } else {
            response.sendRedirect("/recipes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}