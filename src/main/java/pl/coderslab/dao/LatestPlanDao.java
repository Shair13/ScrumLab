package pl.coderslab.dao;

import pl.coderslab.model.LatestPlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LatestPlanDao {
    private static final String READ_LATEST_USERS_PLAN =
            """
            SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description
            FROM `recipe_plan`
            JOIN day_name on day_name.id=day_name_id
            JOIN recipe on recipe.id=recipe_id 
            WHERE recipe_plan.plan_id = (SELECT MAX(id) 
            FROM plan WHERE admin_id = ?) ORDER BY day_name.display_order, recipe_plan.display_order;""";

    public List<LatestPlan> findLastPlan(int adminId){
        List<LatestPlan> latestPlanList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(READ_LATEST_USERS_PLAN)
        ) {
            preStmt.setInt(1, adminId);
            try(ResultSet resultSet = preStmt.executeQuery()){
                while(resultSet.next()) {
                    LatestPlan plan = new LatestPlan();
                    plan.setDayName(resultSet.getString(1));
                    plan.setMealName(resultSet.getString(2));
                    plan.setRecipeName(resultSet.getString(3));
                    plan.setRecipeDescription(resultSet.getString(4));
                    latestPlanList.add(plan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latestPlanList;
    }
}
