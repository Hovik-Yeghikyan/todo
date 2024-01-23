package com.example.todo.servlet;

import com.example.todo.Status;
import com.example.todo.manager.ToDoManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.example.todo.model.ToDo;
import com.example.todo.model.User;
import com.example.todo.util.DateUtil;

@WebServlet(urlPatterns = "/addToDo")

public class AddToDoServlet extends HttpServlet {

    private ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
      // Date createdDate = new Date();
       // Status status = Status.NEW;
        toDoManager.add(ToDo.builder()
                .title(title)
                .createdDate(new Date())
                .user(user)
                .status(Status.NEW)
                .build());
        resp.sendRedirect("/home");

    }
}
