package bd.com.infobox.sqlite_recyclerview;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    private StudentDataSource studentDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDataSource = new StudentDataSource(this);

        _name = findViewById(R.id.nameInput);
        _dept = findViewById(R.id.deptInput);
        _year = findViewById(R.id.yearInput);

        _radioGroup = findViewById(R.id.gender_radioG);


        /**
         * RADIO BUTTON
         */
        _radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup _radioGroup, int selectedId) {
                selectedId = _radioGroup.getCheckedRadioButtonId();
                RadioButton genderChoosed = findViewById(selectedId);
                selected_gender = genderChoosed.getText().toString();
            }
        });

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
}
