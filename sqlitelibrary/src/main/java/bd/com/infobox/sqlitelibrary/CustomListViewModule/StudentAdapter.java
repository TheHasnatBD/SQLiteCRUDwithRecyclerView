package bd.com.infobox.sqlitelibrary.CustomListViewModule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.DatabaseModule.StudentDataSource;
import bd.com.infobox.sqlitelibrary.R;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private List<Student> students;

    private StudentDataSource studentDataSource;


    public StudentAdapter(@NonNull Context context, @NonNull List<Student> students) {
        super(context, R.layout.single_student, students);

        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.single_student, parent,false);

        TextView nameTV = convertView.findViewById(R.id.stdName);
        TextView deptTV = convertView.findViewById(R.id.stdDept);
        final ImageView itemMenu = convertView.findViewById(R.id.itemMenu);

        nameTV.setText(students.get(position).getName());
        deptTV.setText(students.get(position).getDept());

        itemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, itemMenu);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.menu_item, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        studentDataSource = new StudentDataSource(context);

                        if (menuItem.getItemId() == R.id.edit_menu){

                        } else if (menuItem.getItemId() == R.id.delete_menu){
                            int id = students.get(position).getId();
                            boolean deleted_status = studentDataSource.deleteRow(id);

                            if (deleted_status){
                                students.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
                            }
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });

        return convertView;
    }
}
