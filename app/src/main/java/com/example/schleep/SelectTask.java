package com.example.schleep;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectTask extends AppCompatActivity {

    /*Will check if a button is clicked or not*/

    boolean noTaskSelected = true;
    boolean mathTaskSelected = false;
    boolean typingTaskSelected = false;

    Button noTaskButton;
    Button mathTaskButton;
    Button typingTaskButton;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Select Task");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noTaskButton = (Button)findViewById(R.id.none_btn);
        noTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                Toast.makeText(getApplicationContext(),"No task selected!",Toast.LENGTH_SHORT).show();
                noTaskSelected = true;
                mathTaskSelected = false;
                typingTaskSelected = false;
            }
        });

        mathTaskButton = (Button)findViewById(R.id.math_btn);
        mathTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                Toast.makeText(getApplicationContext(),"Math task selected!",Toast.LENGTH_SHORT).show();
                noTaskSelected = false;
                mathTaskSelected = true;
                typingTaskSelected = false;
            }
        });

        typingTaskButton = (Button)findViewById(R.id.typing_btn);
        typingTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
                Toast.makeText(getApplicationContext(),"Typing task selected!",Toast.LENGTH_SHORT).show();
                noTaskSelected = false;
                mathTaskSelected = false;
                typingTaskSelected = true;
            }
        });



        saveButton = (Button)findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"Task saved!",Toast.LENGTH_SHORT).show();
                if(typingTaskSelected)
                {
                    //Bundle extras = intent.getExtras();
                    Bundle bundle = new Bundle();
                    bundle.putString("task", "Typing");
                    //Intent intent = new Intent(getApplicationContext(), AddEditAlarmActivity.class);
                    //intent.putExtras(bundle);
                    //startActivity(intent);
                }
                else if (mathTaskSelected)
                {
                    //something here
                    Bundle bundle = new Bundle();
                    bundle.putString("task", "Math");
                    AddEditAlarmFragment fragobj = new AddEditAlarmFragment();
                    fragobj.setArguments(bundle);
                }
                else {
                    //notaskselected
                    Bundle bundle = new Bundle();
                    bundle.putString("task", "None");
                    AddEditAlarmFragment fragobj = new AddEditAlarmFragment();
                    fragobj.setArguments(bundle);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}
