package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();

        Admin readAdmin = adminDao.read(1);
        readAdmin.setPassword("maslo");
        adminDao.update(readAdmin);

//        Admin admin;
//        if (session.getAttribute("user") == null) {
////            response.sendRedirect("/login");
//            return;
//        } else {
//            admin = (Admin) session.getAttribute("user");
//        }

        response.getWriter().append("test");


        int plansCount = planDao.getRecipesCountByAdmin(1);
        int recipeCount = recipeDao.countRecipeByAdmin(1);

        response.getWriter().append(plansCount + " " + recipeCount);


//        getServletContext().getRequestDispatcher("/dashboard.jsp")
//                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}