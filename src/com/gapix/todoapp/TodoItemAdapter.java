package com.gapix.todoapp;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemAdapter(Context c, List<TodoItem> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItemView todoItemView = (TodoItemView) convertView;
        if (null == todoItemView) {
            todoItemView = TodoItemView.inflate(parent);
        }
        todoItemView.setItem(getItem(position));
        return todoItemView;
    }
}
