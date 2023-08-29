package pl.coderslab.dao;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String CREATE_PLAN_QUERY =
            "INSERT INTO plan(name, description, created, admin_id) VALUES (?, ?, ?, ?)";
    private static final String READ_PLAN_QUERY =
            "SELECT * FROM plan WHERE id = ?";
    private static final String UPDATE_PLAN_QUERY =
            "UPDATE plan SET name = ?, description = ?, created = ? WHERE id = ?";
    private static final String DELETE_PLAN_QUERY =
            "DELETE FROM plan WHERE id = ?";
    private static final String FIND_ALL_PLAN_QUERY =
            "SELECT * FROM plan";

    public Plan createPlan(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setTimestamp(3, plan.getCreated());
            preStmt.setInt(4, plan.getAdminId());
            preStmt.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu plan.
            ResultSet resultSet = preStmt.getGeneratedKeys();
            if (resultSet.next()) {
                plan.setId(resultSet.getInt(1));
            }
            return plan;
        } catch (SQLException e) {
            e.printStackTrace();
            return plan;
        }
    }

    public Plan readPlan(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(READ_PLAN_QUERY);) {

            preStmt.setString(1, Integer.toString(planId));
            ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt(1));
                plan.setName(resultSet.getString(2));
                plan.setDescription(resultSet.getString(3));
                plan.setCreated(resultSet.getTimestamp(4));
                plan.setAdminId(resultSet.getInt(5));
                return plan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(UPDATE_PLAN_QUERY)) {

            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setTimestamp(3, plan.getCreated());
            preStmt.setInt(4, plan.getAdminId());
            preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(DELETE_PLAN_QUERY)) {
            preStmt.setString(1, Integer.toString(planId));
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_PLAN_QUERY);) {
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(rs.getInt("id"));
                planToAdd.setName(rs.getString("name"));
                planToAdd.setDescription(rs.getString("description"));
                planToAdd.setCreated(rs.getTimestamp("created"));
                planToAdd.setAdminId(rs.getInt("admin_id"));
                planList.add(planToAdd);
            }
            return planList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}