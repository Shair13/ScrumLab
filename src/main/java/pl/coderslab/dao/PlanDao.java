package pl.coderslab.dao;
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
            "INSERT INTO plan(name, description, created, adminId) VALUES (?, ?, ?, ?)";
    private static final String READ_PLAN_QUERY =
            "SELECT * FROM plan WHERE id = ?";
    private static final String UPDATE_PLAN_QUERY =
            "UPDATE plan SET name = ?, description = ?, created = ? WHERE id = ?";
    private static final String DELETE_PLAN_QUERY =
            "DELETE FROM plan WHERE id = ?";
    private static final String FIND_ALL_PLAN_QUERY =
            "SELECT * FROM plan";
    public Plan createPlan(Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_PLAN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setString(3, plan.getCreated().toString());
            preStmt.setString(4, Integer.toString(plan.getAdminId()));
            preStmt.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu plan.
            ResultSet resultSet = preStmt.getGeneratedKeys();
            if (resultSet.next()) {
                plan.setId(resultSet.getInt(1));
            }
            return plan;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Plan readPlan(int planId) {
        boolean hasPlan = false;
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(READ_PLAN_QUERY);
            preStmt.setString(1, Integer.toString(planId));
            ResultSet resultSet = preStmt.executeQuery();

            if (resultSet.next()) {
                Plan plan = new Plan();
                plan.setId(resultSet.getInt(1));
                plan.setName(resultSet.getString(2));
                plan.setDescription(resultSet.getString(3));
                plan.setCreated(LocalDate.parse(resultSet.getString(4)));
                plan.setAdminId(Integer.parseInt(resultSet.getString(5)));
                return plan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(UPDATE_PLAN_QUERY);
            preStmt.setString(1, plan.getName());
            preStmt.setString(2, plan.getDescription());
            preStmt.setString(3, plan.getCreated().toString());
            preStmt.setString(4, Integer.toString(plan.getId()));
            preStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement preStmt = conn.prepareStatement(DELETE_PLAN_QUERY);
            preStmt.setString(1, Integer.toString(planId));
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Plan> findAll(){
        List<Plan> planList = new ArrayList<>();
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement preStmt = conn.prepareStatement(FIND_ALL_PLAN_QUERY);
            ResultSet rs = preStmt.executeQuery();

            while (rs.next()){
                Plan planToAdd = new Plan();
                planToAdd.setId(rs.getInt("id"));
                planToAdd.setName(rs.getString("name"));
                planToAdd.setDescription(rs.getString("description"));
                planToAdd.setCreated(LocalDate.parse(rs.getString("created")));
                planList.add(planToAdd);
            }
            return planList;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
