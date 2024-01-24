package com.example.todo.servlet;

import com.example.todo.Status;
import com.example.todo.manager.ToDoManager;
import com.example.todo.model.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/editToDos")

public class EditToDoServlet extends HttpServlet {
    private ToDoManager toDoManager = new ToDoManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDos = toDoManager.getToDoById(id);
        req.setAttribute("toDos", toDos);
        req.getRequestDispatcher("/WEB-INF/editToDos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        toDoManager.update(ToDo.builder()
                .id(id)
                .title(title)
                .finishDate(new Date())
                .status(Status.DONE)
                .build());
        resp.sendRedirect("/home");
    }
}
