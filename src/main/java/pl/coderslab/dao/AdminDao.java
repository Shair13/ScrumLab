package pl.coderslab.dao;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, " +
            "superadmin, enable)VALUES (?, ?, ?, ?, ?, ?);";

    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * from admins where id = ?;";

    private static final String READ_ADMIN_BY_EMAIL_QUERY = "SELECT * from admins where email = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, " +
            "password = ?, superadmin = ?, enable = ? WHERE id = ?;";

    public Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY);
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public Admin readByEmail(String email) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_EMAIL_QUERY);
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public boolean authorize(String email, String password) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    if(BCrypt.checkpw(password, resultSet.getString("password"))) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Admin> findAll() {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminsList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;
    }

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(){
        return BCrypt.checkpw(candidate, hashed);
    }

    public boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }

    public void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(7, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
