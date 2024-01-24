package com.example.todo.servlet;

import com.example.todo.manager.ToDoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteToDos")
public class DeleteToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        toDoManager.deleteToDo(id);
        resp.sendRedirect("/home");
    }
}
