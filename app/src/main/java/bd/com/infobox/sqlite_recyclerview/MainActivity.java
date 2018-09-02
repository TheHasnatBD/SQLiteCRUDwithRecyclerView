package bd.com.infobox.sqlite_recyclerview;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.DatabaseModule.StudentDataSource;

public class MainActivity extends AppCompatActivity {

    private EditText _name, _dept;
    private StudentDataSource studentDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDataSource = new StudentDataSource(this);

        _name = findViewById(R.id.nameInput);
        _dept = findViewById(R.id.deptInput);
    }

    public void addStudentBtn(View view) {
        String Name = _name.getText().toString();
        String Dept = _dept.getText().toString();

        Student student = new Student(Name, Dept);

        if (studentDataSource.insertStudent(student)){
            Toast.makeText(this, "Information added", Toast.LENGTH_SHORT).show();

            _name.setText("");
            _dept.setText("");

        }else {
            Snackbar.make(view, "Failed to add", Snackbar.LENGTH_LONG).show();
        }

    }





    public void goToStdListBtn(View view) {
        startActivity(new Intent(MainActivity.this, StudentListActivity.class));
        finish();
    }
}
