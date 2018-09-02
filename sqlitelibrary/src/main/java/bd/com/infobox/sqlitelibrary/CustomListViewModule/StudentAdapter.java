package bd.com.infobox.sqlitelibrary.CustomListViewModule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.R;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private List<Student> students;


    public StudentAdapter(@NonNull Context context, @NonNull List<Student> students) {
        super(context, R.layout.single_student, students);

        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.single_student, parent,false);

        TextView nameTV = convertView.findViewById(R.id.stdName);
        TextView deptTV = convertView.findViewById(R.id.stdDept);

        nameTV.setText(students.get(position).getName());
        deptTV.setText(students.get(position).getDept());

        return convertView;
    }
}
