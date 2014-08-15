package com.gapix.todoapp;

import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class TodoItemDialog extends DialogFragment {
    private EditText mEditText;
    private DatePicker mDatePicker;
    public TodoItem todoItem;

    public interface TodoItemDialogListener {
        void onFinishDialog(String description, Date date);
    }

    private TodoItemDialogListener mListener;

    public void setFinishDialogListener(TodoItemDialogListener listener) {
        mListener = listener;
    }

    public TodoItemDialog() {
    }

    @SuppressWarnings("deprecation")
    public static TodoItemDialog newInstance(String title, TodoItem item) {
        TodoItemDialog frag = new TodoItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        if (item != null) {
            args.putString("description", item.getDescription());
            Date date = item.getDueDate();
            args.putInt("year", date.getYear());
            args.putInt("month", date.getMonth());
            args.putInt("day", date.getDate());
        } else {
            args.putString("description", "");
        }

        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_item, container);
        String title = getArguments().getString("title", "");
        getDialog().setTitle(title);

        mEditText = (EditText) view.findViewById(R.id.txt_task_description);
        mEditText.setText(getArguments().getString("description", ""));

        mDatePicker = (DatePicker) view.findViewById(R.id.dp_due_date);
        int year = getArguments().getInt("year", 0);

        if (year != 0) {
            int month = getArguments().getInt("month");
            int day = getArguments().getInt("day");

            mDatePicker.updateDate(year + 1900, month, day);
        }
        // Show soft keyboard automatically

        final Button button = (Button) view.findViewById(R.id.btn_save_item);

        // Setup record button notification
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();

                @SuppressWarnings("deprecation")
                Date date = new Date(mDatePicker.getYear() - 1900, mDatePicker
                        .getMonth(), mDatePicker.getDayOfMonth());
                mListener.onFinishDialog(mEditText.getText().toString(), date);
            }
        });

        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return view;
    }
}
