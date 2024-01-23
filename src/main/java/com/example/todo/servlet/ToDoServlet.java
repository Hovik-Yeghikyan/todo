package com.example.todo.servlet;

import com.example.todo.manager.ToDoManager;
import com.example.todo.model.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/toDos")

public class ToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ToDo> toDos = toDoManager.getAll();
        req.setAttribute("toDos",toDos);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req,resp);
    }
}
