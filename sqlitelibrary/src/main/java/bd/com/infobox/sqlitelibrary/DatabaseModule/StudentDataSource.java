package bd.com.infobox.sqlitelibrary.DatabaseModule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDataSource {

    private MyDatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public StudentDataSource(Context context) {
        databaseHelper = new MyDatabaseHelper(context);

    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }

    public boolean insertStudent(Student student){
        this.open();
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.COL_NAME, student.getName());
        values.put(MyDatabaseHelper.COL_DEPT, student.getDept());

        long insertedRow = database.insert(MyDatabaseHelper.TABLE_STD_INFO, null, values);

        this.close();

        if (insertedRow > 0){
            return true;
        }

        return false;
    }

    public List<Student> getStudentInfo(){
        this.open();
        List<Student> students = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + MyDatabaseHelper.TABLE_STD_INFO, null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();

            do {
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_NAME));
                String dept = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COL_DEPT));

                Student student = new Student(id, name, dept);
                students.add(student);

            }while (cursor.moveToNext());
        }

        cursor.close();
        this.close();

        return students;
    }

    public boolean deleteRow(int id){
        this.open();

        int deleted_row =
                database.delete(MyDatabaseHelper.TABLE_STD_INFO, MyDatabaseHelper.COL_ID + " = " + id, null);

        this.close();

        if (deleted_row > 0){
            return true;
        }

        return false;

    }
}
