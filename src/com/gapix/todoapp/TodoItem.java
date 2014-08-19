package com.gapix.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "TodoItems")
public class TodoItem extends Model {
    @Column(name = "Description")
    public String description;

    @Column(name = "DueDate")
    public Date dueDate;
    public int priority;

    public TodoItem() {
        super();
    }

    public TodoItem(String details, Date dueDate, int priority) {
        super();
        this.description = details;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        description = value;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        return "Due Date: " + sdf.format(dueDate);
    }

    public void setDueDate(Date date) {
        dueDate = date;
    }

    public String getPriority() {
        return "7";
    }

    public static List<TodoItem> getAll() {
        return new Select().from(TodoItem.class).execute();
    }
}
