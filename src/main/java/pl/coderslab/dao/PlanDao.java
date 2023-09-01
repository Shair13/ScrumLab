package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private static final String FIND_ALL_PLAN_BY_ADMIN_QUERY =
            "SELECT * FROM plan WHERE admin_id=?";
    private static final String GET_NUMBER_OF_ADMIN_RECIPES =
            "SELECT COUNT(*) FROM plan WHERE admin_id=?";

    public Plan createPlan(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setTimestamp(3, plan.getCreated());
            preStmt.setInt(4, plan.getAdminId());
            int result = preStmt.executeUpdate();
            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet resultSet = preStmt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    plan.setId(resultSet.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Plan readPlan(int planId) {
        Plan plan = new Plan();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(READ_PLAN_QUERY)
        ) {
            preStmt.setInt(1, planId);

            try (ResultSet resultSet = preStmt.executeQuery()) {
                if (resultSet.next()) {
                    plan.setId(resultSet.getInt(1));
                    plan.setName(resultSet.getString(2));
                    plan.setDescription(resultSet.getString(3));
                    plan.setCreated(resultSet.getTimestamp(4));
                    plan.setAdminId(resultSet.getInt(5));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(UPDATE_PLAN_QUERY)
        ) {

            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setTimestamp(3, plan.getCreated());
            preStmt.setInt(4, plan.getId());
            preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(DELETE_PLAN_QUERY)
        ) {
            preStmt.setInt(1, planId);
            preStmt.executeUpdate();
//            boolean deleted = preStmt.execute();
//            if (!deleted) {
//                throw new NotFoundException("Product not found");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_PLAN_QUERY)
        ) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public int getRecipesCountByAdmin(int adminId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement preStmt = conn.prepareStatement(GET_NUMBER_OF_ADMIN_RECIPES)
        ) {
            preStmt.setInt(1, adminId);
            try (ResultSet resultSet = preStmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public List<Plan> findAllByAdmin(int adminId) {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(FIND_ALL_PLAN_BY_ADMIN_QUERY);
        ) {
            insertStm.setInt(1, adminId);
            ResultSet rs = insertStm.executeQuery();
            while (rs.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(rs.getInt("id"));
                planToAdd.setName(rs.getString("name"));
                planToAdd.setDescription(rs.getString("description"));
                planToAdd.setCreated(rs.getTimestamp("created"));
                planToAdd.setAdminId(rs.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }
}