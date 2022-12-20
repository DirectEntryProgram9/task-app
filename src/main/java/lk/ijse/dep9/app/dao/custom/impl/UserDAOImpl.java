package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.UserDAO;
import lk.ijse.dep9.app.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO User (username, full_name, password) VALUES (?,?,?)");
            stm.setString(1,user.getUserName());
            stm.setString(2,user.getFullName());
            stm.setString(3,user.getPassword());
            stm.executeUpdate();
            return user;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE User SET full_name=?, username=?, password=?");
            stm.setString(1,user.getUserName());
            stm.setString(2,user.getFullName());
            stm.setString(3,user.getPassword());
            stm.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(String username) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM User WHERE username=?");
            stm.setString(1,username);
            stm.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<User> findById(String username) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT full_name, username, password FROM User WHERE username=?");
            stm.setString(1,username);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                return Optional.of(new User(username,rst.getString("password"),rst.getString("full_name")));
            }
            return Optional.empty();
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            List<User> userList = new ArrayList<>();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM User");
            ResultSet rst = stm.executeQuery();
            while (rst.next()) {
                User user = new User(rst.getString("username"),
                        rst.getString("password"),
                        rst.getString("full_name"));
                userList.add(user);
            }
            return userList;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public long count() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT COUNT(username) FROM User");
            ResultSet rst = stm.executeQuery();
            rst.next();
            return rst.getLong(1);
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public boolean existById(String username) {
        return findById(username).isPresent();
    }
}
