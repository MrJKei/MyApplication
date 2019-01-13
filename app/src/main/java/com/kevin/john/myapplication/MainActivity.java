package com.kevin.john.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname,lastname;
    TextView textView;

    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);

        textView = findViewById(R.id.textView);

        controller = new DB_Controller(MainActivity.this , "" , null , 1);


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_students:

                try {

                    controller.add_students(firstname.getText().toString(),lastname.getText().toString());

                    AlertDialog.Builder dialog = new AlertDialog.Builder(this   );
                    dialog.setTitle("DONE");

                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    dialog.show();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this , "FIRSTNAME ALREADY EXISTS" , Toast.LENGTH_SHORT).show();

                }


                break;

            case R.id.delete_students:

                controller.delete_students(firstname.getText().toString());

                break;

            case R.id.edit_students:

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER YOUR NEW FIRSTNAME");
                final EditText new_firstname = new EditText(this  );
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        controller.edit_students(firstname.getText().toString(), new_firstname.getText().toString());
                    }
                });

                dialog.show();

                break;

            case R.id.view_students:
                controller.view_students(textView);
                break;
        }
    }
}
