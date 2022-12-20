package lk.ijse.dep9.app.dao.custom.impl;

import lk.ijse.dep9.app.dao.custom.TaskDAO;
import lk.ijse.dep9.app.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaskDAOImpl implements TaskDAO {

    private final Connection connection;

    public TaskDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Task save(Task task) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Task (id, content, status, project_id) VALUES (?,?,?,?)");
            stm.setInt(1, task.getId());
            stm.setString(2,task.getContent());
//            stm.set(3,task.getStatus());
            stm.setInt(3,task.getProjectId());
            stm.executeUpdate();
            return task;
        }
        catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void deleteById(Integer pk) {

    }

    @Override
    public Optional<Task> findById(Integer pk) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existById(Integer pk) {
        return false;
    }
}
