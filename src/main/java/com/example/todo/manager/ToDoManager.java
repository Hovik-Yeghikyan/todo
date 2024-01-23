package com.example.todo.manager;

import com.example.todo.Status;
import com.example.todo.db.DBConnectionProvider;
import com.example.todo.model.ToDo;
import com.example.todo.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ToDoManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();

    public List<ToDo> getAll() {
        String sql = "SELECT * FROM to_do";
        List<ToDo> toDos = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                toDos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))

                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    public ToDo getToDoById(int id) {
        String sql = "SELECT * FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                return ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(ToDo toDos) {
        String sql = "INSERT INTO to_do(title,created_date,user_id,status) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, toDos.getTitle());
            preparedStatement.setString(2, DateUtil.convertDateToString(toDos.getCreatedDate()));
            preparedStatement.setInt(3, toDos.getUser().getId());
            preparedStatement.setString(4, String.valueOf(toDos.getStatus()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                toDos.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLesson(int id) {
        String sql = "DELETE FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(ToDo toDos) {
        String sql = "UPDATE to_do SET title = ?,  finish_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, toDos.getTitle());
            preparedStatement.setString(2, DateUtil.convertDateToString(toDos.getFinishDate()));
            preparedStatement.setString(3, String.valueOf(toDos.getStatus()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

