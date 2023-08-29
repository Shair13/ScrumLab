package pl.coderslab.web;
import java.sql.Timestamp;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/testPlan")
public class testPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();

        planDao.createPlan(new Plan("Halo","Cześć", new Timestamp(System.currentTimeMillis()), 1));
        System.out.println(planDao.readPlan(7));
        Plan planToChange = planDao.readPlan(7);
        planToChange.setName("holahola");
        planDao.update(planToChange);
        System.out.println(planDao.readPlan(7));
        planDao.delete(7);
        List<Plan> plans = planDao.findAll();
        for (Plan plan: plans) {
            System.out.println(plan);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}