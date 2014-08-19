package com.gapix.todoapp;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.gapix.todoapp.TodoItemDialog.TodoItemDialogListener;

public class TodoActivity extends FragmentActivity {
    private ArrayList<TodoItem> items;
    private ListView lvItems;
    private TodoItemAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.lvItems);

        items = new ArrayList<TodoItem>();
        readItems();

        itemsAdapter = new TodoItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    public void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long rowId) {
                TodoItem item = items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                item.delete();
                // saveItems();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long rowId) {
                showEditItemDialog(itemsAdapter.getItem(position));
            }
        });

    }

    // Load / Save items

    public void readItems() {
        items.addAll(TodoItem.getAll());
    }

    private void showAddItemDialog() {
        FragmentManager fm = getSupportFragmentManager();
        TodoItemDialog todoItemDialog = TodoItemDialog.newInstance(
                "Add Todo Item", null);
        todoItemDialog.setFinishDialogListener(new TodoItemDialogListener() {
            public void onFinishDialog(String description, Date date) {
                TodoItem item = new TodoItem(description, date, 3);
                items.add(item);
                itemsAdapter.notifyDataSetChanged();
                item.save();
            }
        });

        todoItemDialog.show(fm, "fragment_todo_item");
    }

    private void showEditItemDialog(final TodoItem item) {
        FragmentManager fm = getSupportFragmentManager();
        TodoItemDialog todoItemDialog = TodoItemDialog.newInstance(
                "Edit Todo Item", item);
        todoItemDialog.setFinishDialogListener(new TodoItemDialogListener() {
            public void onFinishDialog(String description, Date date) {
                item.setDescription(description);
                item.setDueDate(date);
                itemsAdapter.notifyDataSetChanged();
                item.save();
            }
        });

        todoItemDialog.show(fm, "fragment_todo_item");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
        case R.id.action_add:
            showAddItemDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
