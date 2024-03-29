package com.example.todo.model;

import com.example.todo.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {

    private int id;
    private String title;
    private Date createdDate;
    private Date finishDate;
    private User user;
    private String status;
}
