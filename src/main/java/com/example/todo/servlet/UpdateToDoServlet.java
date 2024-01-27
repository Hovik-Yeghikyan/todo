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

@WebServlet(urlPatterns = "/updateToDos")
public class UpdateToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDos = toDoManager.getToDoById(id);
        req.setAttribute("toDos", toDos);
        req.getRequestDispatcher("/WEB-INF/updateToDos.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        toDoManager.updateTitle(ToDo.builder()
                .title(title)
                .id(id)
                .build());
        resp.sendRedirect("/home");
    }
}
