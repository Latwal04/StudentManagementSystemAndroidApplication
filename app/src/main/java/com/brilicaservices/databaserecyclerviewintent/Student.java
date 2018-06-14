package com.brilicaservices.databaserecyclerviewintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class Student extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static final String TAG = Student.class.getName();
    /*
     * Creating global object of different views that we
     * are going to use in our application.
     * */
    EditText studentNameTF, studentPhoneTF, studentAddressTF;
    Button addStudentBtn, displayStudentBtn;

    /*
     * Creating a global collegeName String object.*/
    String collegeName = "";

    /*
     * ArrayList of Student class will be used
     * to store the data of individual student.*/
    ArrayList<StudentModel> studentArrayList = new ArrayList<>();

    /*
     * Creating a spinner object*/
    Spinner spinnerCollegeNames;

    /*
     * Creating a string of array of colleges*/
    String collegeNames[] = {"Select college name","DIT", "Graphic Era", "HNB"};

    /*
    * Database Helper*/
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        /*
         * casting objects with the respective view ids.*/
        studentNameTF = findViewById(R.id.student_name);
        studentPhoneTF = findViewById(R.id.enter_phone);
        studentAddressTF = findViewById(R.id.enter_address);
        addStudentBtn= findViewById(R.id.add_student_button);
        spinnerCollegeNames = findViewById(R.id.college_name_spinner);
        displayStudentBtn = findViewById(R.id.display_student_button);
        databaseHelper = new DatabaseHelper(this);

        /*
         * Using setOnItemSelectedListener on spinner object
         * and giving it the context - this, meaning current activity*/
        spinnerCollegeNames.setOnItemSelectedListener(this);

        /*
         * Creating an arrayAdapter object and passing 3 different arguments
         * i.e. context, layout, array*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, collegeNames);

        /*
         * using the spinner's setAdapter method to update it's adapter*/
        spinnerCollegeNames.setAdapter(arrayAdapter);

        /*
         * setPrompt is select on spinner to just give the refernce that the
         * first object of array is only a label.*/
        spinnerCollegeNames.setPrompt(collegeNames[0]);

        /*
         * Adding a click listener on addStudentBtn*/
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                 * On the click of the button, getting values from
                 * the user input.*/
                String name = studentNameTF.getText().toString();
                long phone = Long.parseLong(studentPhoneTF.getText().toString());
                String address = studentAddressTF.getText().toString();

                /*
                 * Storing the new values into the arrayList using the
                 * Student class object.*/
                databaseHelper.addNewStudent(new StudentModel(name,
                        collegeName, address, phone));

                /*
                 * Showing a success message once the data has been saved into arrayList*/
                Toast.makeText(getApplicationContext(), "Student data saved successfully", Toast.LENGTH_LONG).show();
            }
        });
        displayStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Student.this,ItemLayout.class);
                intent.putExtra("Name:",studentNameTF.getText());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /*
         * getting college name from the list of colleges.
         * It will be updated only on the basis of array.*/
        collegeName = collegeNames[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



