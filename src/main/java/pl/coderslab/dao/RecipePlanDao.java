package pl.coderslab.dao;

import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePlanDao {
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe_plan(recipe_id,meal_name,display_order,day_name_id,plan_id) VALUES (?,?,?,?,?);";
    private static final String DELETE_RECIPE_PLAN_QUERY = "DELETE FROM recipe_plan where id = ?;";
    private static final String FIND_ALL_RECIPE_PLAN_BY_ADMIN_QUERY = "SELECT recipe_plan.* FROM recipe_plan JOIN plan on recipe_plan.plan_id=plan.id where plan.admin_id=?;";
    private static final String FIND_ALL_RECIPE_PLAN_BY_PLAN_QUERY = """
            SELECT recipe_plan.*
            FROM recipe_plan
                     JOIN day_name on day_name.id = day_name_id
                     JOIN plan on recipe_plan.plan_id = plan.id
            WHERE plan.id = ?
            ORDER BY day_name.display_order, recipe_plan.display_order;
            """;
    private static final String READ_LATEST_USERS_PLAN = """
            SELECT recipe_plan.*
            FROM recipe_plan
                     JOIN day_name on day_name.id = day_name_id
                     JOIN recipe on recipe.id = recipe_id
            WHERE recipe_plan.plan_id = (SELECT MAX(plan.id)
                                         FROM recipe_plan
                                                  JOIN plan on recipe_plan.plan_id = plan.id
                                         WHERE admin_id = ?
                                         ORDER BY plan.id)
            ORDER BY day_name.display_order, recipe_plan.display_order;
            """;

    public RecipePlan create(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setInt(1, recipePlan.getRecipeId());
            insertStm.setString(2, recipePlan.getMealName());
            insertStm.setInt(3, recipePlan.getDisplayOrder());
            insertStm.setInt(4, recipePlan.getDayNameId());
            insertStm.setInt(5, recipePlan.getPlanId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipePlan.setId(generatedKeys.getInt(1));
                    return recipePlan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RecipePlan> findAllByAdmin(int adminId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_ALL_RECIPE_PLAN_BY_ADMIN_QUERY);
        ) {
            insertStm.setInt(1, adminId);
            ResultSet resultSet = insertStm.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipeToAdd = new RecipePlan();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                recipeToAdd.setMealName(resultSet.getString("meal_name"));
                recipeToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipeToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipeToAdd.setPlanId(resultSet.getInt("plan_id"));
                recipePlanList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;

    }

    public List<RecipePlan> findAllByPlan(int planId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_ALL_RECIPE_PLAN_BY_PLAN_QUERY);
        ) {
            insertStm.setInt(1, planId);
            ResultSet resultSet = insertStm.executeQuery();
            while (resultSet.next()) {
                RecipePlan recipeToAdd = new RecipePlan();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                recipeToAdd.setMealName(resultSet.getString("meal_name"));
                recipeToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                recipeToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                recipeToAdd.setPlanId(resultSet.getInt("plan_id"));
                recipePlanList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

    public void delete(Integer id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_PLAN_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RecipePlan> findLastPlan(int adminId) {
        List<RecipePlan> recipePlanList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(READ_LATEST_USERS_PLAN)
        ) {
            preStmt.setInt(1, adminId);
            try (ResultSet resultSet = preStmt.executeQuery()) {
                while (resultSet.next()) {
                    RecipePlan recipeToAdd = new RecipePlan();
                    recipeToAdd.setId(resultSet.getInt("id"));
                    recipeToAdd.setRecipeId(resultSet.getInt("recipe_id"));
                    recipeToAdd.setMealName(resultSet.getString("meal_name"));
                    recipeToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                    recipeToAdd.setDayNameId(resultSet.getInt("day_name_id"));
                    recipeToAdd.setPlanId(resultSet.getInt("plan_id"));
                    recipePlanList.add(recipeToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipePlanList;
    }

}
