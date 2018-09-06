package bd.com.infobox.sqlite_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bd.com.infobox.sqlitelibrary.DatabaseModule.Student;
import bd.com.infobox.sqlitelibrary.DatabaseModule.StudentDataSource;

public class StudentAdapterRV extends RecyclerView.Adapter<StudentAdapterRV.StudentViewHolder>{

    private Context context;
    private List<Student> students;
    private StudentDataSource studentDataSource;

    public StudentAdapterRV(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_student_rv, parent, false);
        StudentViewHolder studentViewHolder = new StudentViewHolder(view);
        return studentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentViewHolder holder, final int position) {
        holder.nameTV.setText(students.get(position).getName());
        holder.deptTV.setText(students.get(position).getDept());
        holder.genderTV.setText(students.get(position).getGender());

        holder.menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), holder.menuItem);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(bd.com.infobox.sqlitelibrary.R.menu.menu_item, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        studentDataSource = new StudentDataSource(context);
                        int id = students.get(position).getId();
                        switch (menuItem.getItemId()){
                            case R.id.edit_menu:
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("id", id);
                                context.startActivity(intent);
                                break;
                            case R.id.delete_menu:
                                boolean deleted_status = studentDataSource.deleteRow(id);
                                if (deleted_status){
                                    students.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
                                }
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV, deptTV, genderTV;
        ImageView menuItem;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.rv_stdName);
            deptTV = itemView.findViewById(R.id.rv_stdDept);
            genderTV = itemView.findViewById(R.id.rv_stdGender);

            menuItem = itemView.findViewById(R.id.rv_itemMenu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    view = LayoutInflater.from(context).inflate(R.layout.view_profile_layout, null);

                    TextView name = view.findViewById(R.id.name);
                    TextView dept = view.findViewById(R.id.dept);
                    TextView gender = view.findViewById(R.id.gender);
                    TextView year = view.findViewById(R.id.year);

                    name.setText(students.get(position).getName());
                    dept.setText(students.get(position).getDept());
                    gender.setText(students.get(position).getGender());
                    year.setText(students.get(position).getYear());

                    builder.setView(view);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            });

        }
    }
}
