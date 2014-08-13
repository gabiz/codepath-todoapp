package com.gapix.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
    private EditText etEditItem;
    private int entryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        etEditItem = (EditText) findViewById(R.id.etEditItem);
        String text = getIntent().getExtras().getString("text");
        entryPosition = getIntent().getExtras().getInt("position");

        etEditItem.append(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_item, menu);
        return true;
    }

    public void saveEditItem(View v) {
        Intent data = new Intent();
        data.putExtra("text", etEditItem.getText().toString());
        data.putExtra("position", entryPosition);
        setResult(RESULT_OK, data);
        finish();
    }
}
