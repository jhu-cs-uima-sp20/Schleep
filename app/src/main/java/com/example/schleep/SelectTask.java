package com.example.schleep;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class SelectTask extends AppCompatActivity {

    /*Will check if a button is clicked or not*/

    private static String task;
    
    boolean noTaskSelected = true;
    boolean mathTaskSelected = false;
    boolean typingTaskSelected = false;
    String difficulty = "easy";
    String questions = "1";
    Button noTaskButton;
    Button mathTaskButton;
    Button typingTaskButton;
    Button saveButton;
    private boolean[] math_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Select Task");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        math_array = new boolean[9];
        noTaskButton = (Button)findViewById(R.id.none_btn);
        noTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //change boolean value
               // Toast.makeText(getApplicationContext(),"No task selected!",Toast.LENGTH_SHORT).show();
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
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_math, null);
                Dialog d = new BottomSheetDialog(SelectTask.this);
                d.setContentView(val);
                d.show();
                RadioButton easy = val.findViewById(R.id.math_easy);
                RadioButton med = val.findViewById(R.id.math_med);
                RadioButton hard = val.findViewById(R.id.math_hard);
                RadioButton pro = val.findViewById(R.id.math_pro);
                RadioButton one = val.findViewById(R.id.math_one);
                RadioButton five = val.findViewById(R.id.math_five);
                RadioButton seven = val.findViewById(R.id.math_seven);
                RadioButton ten = val.findViewById(R.id.math_ten);


                easy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        easy.setChecked(math_array[1]);
                        if(!math_array[1]){
                            easy.setChecked(true);
                            math_array[1] = true;
                            difficulty = "easy";
                            //  Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            easy.setChecked(false);
                            math_array[1] = false;
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                med.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[2]){
                            difficulty  ="med";
                            math_array[2] = true;

                            //  Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{

                            math_array[2] = false;
                            difficulty = "easy";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                hard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[3]){
                            difficulty = "hard";
                            math_array[3] = true;

                            // Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            math_array[3] = false;
                            difficulty = "easy";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });
                pro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[4]){
                            math_array[4] = true;
                            difficulty = "pro";
                            //    Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            math_array[4] = false;
                            difficulty = "easy";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[5]){
                            math_array[5] = true;
                            questions = "1";
                        }else{
                            math_array[5] = false;

                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });
                five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[6]){
                            math_array[6] = true;
                            questions = "5";
                        }else{
                            math_array[6] = false;
                            questions = "1";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });

                seven.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if(!math_array[7]){
                            math_array[7] = true;
                            questions = "7";

                            //Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            math_array[7] = false;
                            questions = "1";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });
                ten.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!math_array[8]){
                            math_array[8] = true;
                            questions = "10";

                            //Toast.makeText(getContext(), selected_days[1] + " :is checked 1" , Toast.LENGTH_SHORT).show();
                        }else{
                            math_array[8] = false;
                            questions = "1";
                            //Toast.makeText(getContext(), selected_days[1]  + " :is checked 2" , Toast.LENGTH_SHORT).show();
                        }
                        //  Toast.makeText(getContext(), days_array[7] + " :is checked" , Toast.LENGTH_SHORT).show();
                    }
                });




                //change boolean value
                //Toast.makeText(getApplicationContext(),"Math task selected!",Toast.LENGTH_SHORT).show();

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
                View val = getLayoutInflater().inflate(R.layout.bottom_sheet_typing, null);
                Dialog d = new BottomSheetDialog(SelectTask.this);
                d.setContentView(val);
                d.show();
                //change boolean value
                //Toast.makeText(getApplicationContext(),"Typing task selected!",Toast.LENGTH_SHORT).show();
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
              //  Toast.makeText(getApplicationContext(),"Task saved!",Toast.LENGTH_SHORT).show();
                if(typingTaskSelected)
                {
                    MainActivity.curr_task = "typing";

                    setVar("typing");
                }
                else if (mathTaskSelected)
                {
                    MainActivity.curr_task = "math";
                    MainActivity.curr_ques = questions;
                    setVar("math");
                }
                else {
                    MainActivity.curr_task = "none";

                    setVar("none");
                }
            }
        });
    }


    public static String getVar() {
        return task;
    }

    public void setVar(String task) {
        this.task = task;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
         if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent();
            i.putExtra("task", task);
            i.putExtra("difficulty", difficulty);
            i.putExtra("question", questions);

            setResult(RESULT_OK, i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

*/
@Override
public boolean onSupportNavigateUp(){
    Intent i = new Intent();
    i.putExtra("task", task);
    i.putExtra("difficulty", difficulty);
    i.putExtra("question", questions);

    //setResult(RESULT_OK, i);
    finish();

    return true;


}


}
