package com.gapix.todoapp;

import java.util.Date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TodoItemView extends RelativeLayout {
    private TextView mDetailsView;
    private TextView mPriorityView;
    private TextView mDueDateView;

    public TodoItemView(Context c) {
        this(c, null);
    }

    public TodoItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TodoItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.todo_item_view_children,
                this, true);
        setupChildren();
    }

    public static TodoItemView inflate(ViewGroup parent) {
        TodoItemView todoItemView = (TodoItemView) LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_todo_item_view,
                parent, false);
        return todoItemView;
    }

    private void setupChildren() {
        mDetailsView = (TextView) findViewById(R.id.todoitem_DescTextView);
        mDueDateView = (TextView) findViewById(R.id.todoitem_DueDateTextView);
        mPriorityView = (TextView) findViewById(R.id.todoitem_PriorityTextView);
    }

    public void setItem(TodoItem item) {
        mDetailsView.setText(item.getDescription());
        mDueDateView.setText(item.getDueDateString());
        switch (item.getPriority()) {
        case LOW_PRIORITY:
            mPriorityView.setText("Low");
            mPriorityView.setTextColor(getResources().getColor(R.color.bluish));
            mPriorityView.setVisibility(View.VISIBLE);
            break;
        case NORMAL_PRIORITY:
            mPriorityView.setVisibility(View.GONE);
            break;
        case HIGH_PRIORITY:
            mPriorityView.setText("High");
            mPriorityView.setVisibility(View.VISIBLE);
            mPriorityView.setTextColor(getResources().getColor(R.color.redish));
            break;
        }
        Date dueDate = item.getDueDate();
        Date now = new Date();
        if (dueDate.before(now)) {
            mDueDateView.setTextColor(getResources().getColor(R.color.red));
        } else {
            mDueDateView.setTextColor(getResources().getColor(R.color.blue));
        }
    }
}
