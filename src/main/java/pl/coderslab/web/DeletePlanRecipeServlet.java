package pl.coderslab.web;

import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/app/plan/details/delete")
public class DeletePlanRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("recipePlanId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.delete(id);
        response.sendRedirect("/app/plan/details?planId="+planId);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}