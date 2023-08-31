package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "RecipeDetailsServlet", value = "/app/recipe/details")
public class RecipeDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String REGEX = "[0-9]+";
        Pattern pattern = Pattern.compile(REGEX);
        HttpSession session = request.getSession();

        if(session.getAttribute("user") != null) {

            if (idParam == null) {
                response.sendRedirect("/dashboard");
            } else if (pattern.matcher(idParam).matches()) {
                int id = Integer.parseInt(idParam);
                RecipeDao recipeDao = new RecipeDao();
                Recipe recipe = recipeDao.read(id);
                String[] ingredientsArr = recipe.getIngredients().split("\\n");
                request.setAttribute("ingredients", ingredientsArr);
                request.setAttribute("recipe", recipe);
                getServletContext().getRequestDispatcher("/recipedetailsApp.jsp").forward(request, response);
            } else {
                response.sendRedirect("/dashboard");
            }
        } else {
            if (idParam == null) {
                response.sendRedirect("/");
            } else if (pattern.matcher(idParam).matches()) {
                int id = Integer.parseInt(idParam);
                RecipeDao recipeDao = new RecipeDao();
                Recipe recipe = recipeDao.read(id);
                String[] ingredientsArr = recipe.getIngredients().split("\\n");
                request.setAttribute("ingredients", ingredientsArr);
                request.setAttribute("recipe", recipe);
                getServletContext().getRequestDispatcher("/recipedetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}