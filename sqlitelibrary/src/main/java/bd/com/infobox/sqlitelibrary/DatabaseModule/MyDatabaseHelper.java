package bd.com.infobox.sqlitelibrary.DatabaseModule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {


    public static final  String DATABASE_NAME = "student_db";
    public static final  int DATABASE_VERSION = 2;


    public static final String TABLE_STD_INFO = "student_info";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "student_name";
    public static final String COL_DEPT = "student_dept";
    public static final String COL_GENDER = "student_gender";
    public static final String COL_YEAR = "student_year";

    public static final String CREATE_TABLE_STD_INFO
            ="create table " + TABLE_STD_INFO + " ("
            + COL_ID + " integer primary key, "
            + COL_NAME + " text, "
            + COL_DEPT + " text, "
            + COL_GENDER + " text, "
            + COL_YEAR + " text)";
    public static final String ADD_COL_GENDER_TABLE_STD_INFO
            = "alter table " + TABLE_STD_INFO + " add " + COL_GENDER + " text";

    public static final String ADD_COL_YEAR_TABLE_STD_INFO
            = "alter table " + TABLE_STD_INFO + " add " + COL_YEAR + " text";
    ;


    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_STD_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ADD_COL_GENDER_TABLE_STD_INFO);
        sqLiteDatabase.execSQL(ADD_COL_YEAR_TABLE_STD_INFO);
    }
}
