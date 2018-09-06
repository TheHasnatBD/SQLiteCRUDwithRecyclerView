package bd.com.infobox.sqlite_recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import bd.com.infobox.sqlitelibrary.CustomListViewModule.StudentAdapter;
import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.DatabaseModule.StudentDataSource;

public class StudentListActivity extends AppCompatActivity {

    //private ListView student_list;
    private RecyclerView student_list;
    private List<Student> students;
    //private StudentAdapter adapter;
    private StudentAdapterRV studentAdapterRV;
    private StudentDataSource studentDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);


        student_list = findViewById(R.id.studentList);
        studentDataSource = new StudentDataSource(this);
        students = studentDataSource.getStudentInfo();

        // RecyclerView
        studentAdapterRV = new StudentAdapterRV(this, students);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        student_list.setLayoutManager(linearLayoutManager);
        student_list.setAdapter(studentAdapterRV);
        // ending RecyclerView


        /* // For list view
        adapter = new StudentAdapter(this, students);
        student_list.setAdapter(adapter);

        student_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name = students.get(i).getName();
                Toast.makeText(StudentListActivity.this, "Name : " + name, Toast.LENGTH_SHORT).show();
            }
        });
        */




        FloatingActionButton addStudent = findViewById(R.id.fab);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(StudentListActivity.this, MainActivity.class));
                //finish();
            }
        });


    } // ending onCreate






}
