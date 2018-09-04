package bd.com.infobox.sqlite_recyclerview;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.DatabaseModule.StudentDataSource;

public class MainActivity extends AppCompatActivity {

    private EditText _name, _dept, _year;
    private RadioGroup _radioGroup;
    private String selected_gender;
    private Button saveBtn, updateBtn, goStudentListBtn;

    private StudentDataSource studentDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDataSource = new StudentDataSource(this);

        _name = findViewById(R.id.nameInput);
        _dept = findViewById(R.id.deptInput);
        _radioGroup = findViewById(R.id.gender_radioG);
        _year = findViewById(R.id.yearInput);

        saveBtn = findViewById(R.id.addStdBtn);
        updateBtn = findViewById(R.id.updateStudentBtn);
        goStudentListBtn = findViewById(R.id.goToStdListBtn);


        /**
         * RADIO BUTTON
         */
        //RadioButton radioButton = (RadioButton) _radioGroup.getChildAt(0);
        _radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup _radioGroup, int selectedId) {
                selectedId = _radioGroup.getCheckedRadioButtonId();
                RadioButton genderChoosed = findViewById(selectedId);

                selected_gender = genderChoosed.getText().toString();
            }
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        if (id > 0){
            saveBtn.setVisibility(View.INVISIBLE);
            goStudentListBtn.setVisibility(View.INVISIBLE);
            updateBtn.setVisibility(View.VISIBLE);
            updateBtn.setTag(id);

            Student student = studentDataSource.getStudentInfoByID(id);
            _name.setText(student.getName());
            _dept.setText(student.getDept());
            selected_gender = student.getGender();
            _year.setText(student.getYear());
        }


    } // ending onCreate



    public void addStudentBtn(View view) {
        String Name = _name.getText().toString();
        String Dept = _dept.getText().toString();
        String Gender = selected_gender;
        String Year = _year.getText().toString();

        Student student = new Student(Name, Dept, Gender, Year);

        if (studentDataSource.insertStudent(student)){
            Toast.makeText(this, "Information added", Toast.LENGTH_SHORT).show();

            _name.setText("");
            _dept.setText("");
            _year.setText("");

        }else {
            Snackbar.make(view, "Failed to add", Snackbar.LENGTH_LONG).show();
        }

    }





    public void goToStdListBtn(View view) {
        startActivity(new Intent(MainActivity.this, StudentListActivity.class));
        finish();
    }

    public void updateStudentBtn(View view) {
        int Id = (int) updateBtn.getTag();
        String Name = _name.getText().toString();
        String Dept = _dept.getText().toString();
        String Gender = selected_gender;
        String Year = _year.getText().toString();

        Student student = new Student(Id, Name, Dept, Gender, Year);

        boolean updatedStatus = studentDataSource.updateStudent(student);
        if (updatedStatus){
            Toast.makeText(this, "Updated information", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, StudentListActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Updated failed", Toast.LENGTH_SHORT).show();
        }

    }
}
