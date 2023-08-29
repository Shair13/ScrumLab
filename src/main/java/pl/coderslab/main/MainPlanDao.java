package pl.coderslab.main;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(value = "/mainPlanDao")
public class MainPlanDao extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        PlanDao planDao = new PlanDao();
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

//        Utwórz nowy
        Plan newPlan = new Plan("Plan Duża Buła", "opis planu", currentDate, 1);
        Plan createPlanDao = planDao.createPlan(newPlan);
        int planId = createPlanDao.getId();

//        Odczyaj dane
        Plan readPlan = planDao.readPlan(planId);
        writer.append(readPlan.toString()).append("<br>");
        writer.append("<br>");

//        Zaktualizuj dane
        readPlan.setName("Plan codziennie tłusty czwartek");
        readPlan.setDescription("Opis planu 10");
        readPlan.setCreated(currentDate);
        readPlan.setAdminId(2);
        planDao.update(readPlan);
        writer.append(readPlan.toString()).append("<br>");
        writer.append("<br>");

//        Odczytaj wszystko
        List<Plan> planList = planDao.findAll();;
        for (Plan plan : planList) {
            writer.append(plan.toString()).append("<br>");
        }
        writer.append("<br>");
//
//        Usuń
        planDao.delete(planId);

//        Odczytaj wszystko po usunięciu
        planList = planDao.findAll();;
        for (Plan plan : planList) {
            writer.append(plan.toString()).append("<br>");
        }
    }
}