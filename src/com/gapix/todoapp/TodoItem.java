package com.gapix.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoItem {
    public String details;
    public Date dueDate;
    public int priority;

    public TodoItem(String details, Date dueDate, int priority) {
        this.details = details;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getDescription() {
        return details;
    }

    public void setDescription(String description) {
        details = description;
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

}
