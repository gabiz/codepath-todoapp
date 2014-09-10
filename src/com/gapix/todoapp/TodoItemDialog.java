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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class TodoItemDialog extends DialogFragment {
    private EditText mEditText;
    private Spinner mPrioSpinner;
    private DatePicker mDatePicker;
    public TodoItem todoItem;

    public interface TodoItemDialogListener {
        void onFinishDialog(String description, Date date, TodoItem.Priority priority);
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

            args.putInt("priority", item.getPriority().ordinal());
        } else {
            args.putString("description", "");
            args.putInt("priority", 1);
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

        // Set task description
        mEditText = (EditText) view.findViewById(R.id.txt_task_description);
        mEditText.setText(getArguments().getString("description", ""));

        // Set priority
        mPrioSpinner = (Spinner) view.findViewById(R.id.spn_priority);
        int priority = getArguments().getInt("priority");
        mPrioSpinner.setSelection(priority);

        // Set due date
        mDatePicker = (DatePicker) view.findViewById(R.id.dp_due_date);
        int year = getArguments().getInt("year", 0);

        if (year != 0) {
            int month = getArguments().getInt("month");
            int day = getArguments().getInt("day");

            mDatePicker.updateDate(year + 1900, month, day);
        }
        // Show soft keyboard automatically

        final Button button = (Button) view.findViewById(R.id.btn_save_item);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();

                @SuppressWarnings("deprecation")
                Date date = new Date(mDatePicker.getYear() - 1900, mDatePicker
                        .getMonth(), mDatePicker.getDayOfMonth());
                TodoItem.Priority priority = TodoItem.Priority.values()[mPrioSpinner.getSelectedItemPosition()];
                mListener.onFinishDialog(mEditText.getText().toString(), date,
                        priority);
            }
        });

        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return view;
    }

    public void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }
}
