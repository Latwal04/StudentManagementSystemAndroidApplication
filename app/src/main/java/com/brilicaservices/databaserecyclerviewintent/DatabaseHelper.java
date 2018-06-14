package com.brilicaservices.databaserecyclerviewintent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
class DatabaseHelper extends SQLiteOpenHelper{
    /*
   * Database details*/
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_db";

    /*
    * Student_Record table details*/
    private static final String TABLE_NAME = "student_record";
    private static final String STUDENT_NAME = "student_name";
    private static final String STUDENT_ID = "student_id";
    private static final String STUDENT_COLLEGE = "student_college";
    private static final String STUDENT_ADDRESS = "student_address";
    private static final String STUDENT_PHONE_NUMBER = "student_phone";

    /*
    * Table structure*/
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            STUDENT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " TEXT, " +
            STUDENT_COLLEGE + " TEXT, " + STUDENT_ADDRESS
            + " TEXT, " + STUDENT_PHONE_NUMBER + " INTEGER ); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public long addNewStudent(StudentModel studentModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENT_NAME, studentModel.name);
        contentValues.put(STUDENT_COLLEGE, studentModel.collegeName);
        contentValues.put(STUDENT_ADDRESS, studentModel.address);
        contentValues.put(STUDENT_PHONE_NUMBER, studentModel.phoneNumber);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }

    public StudentModel getSingleStudentDetails(long id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{STUDENT_ID, STUDENT_NAME, STUDENT_COLLEGE, STUDENT_ADDRESS,
                        STUDENT_PHONE_NUMBER}, STUDENT_ID + "=?", new String[]{String.valueOf(id)}, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        StudentModel studentModel = new StudentModel(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)),
                cursor.getString(cursor.getColumnIndex(STUDENT_NAME)), cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)),
                cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)), cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

        cursor.close();

        return studentModel;
    }

    public SQLiteDatabase getReadableDatabase() {
        return null;
    }

    public List<StudentModel> allStudentsDetails() {
        List<StudentModel> studentsList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(cursor.getInt(cursor.getColumnIndex(STUDENT_ID)));
                studentModel.setName(cursor.getString(cursor.getColumnIndex(STUDENT_NAME)));
                studentModel.setCollegeName(cursor.getString(cursor.getColumnIndex(STUDENT_COLLEGE)));
                studentModel.setAddress(cursor.getString(cursor.getColumnIndex(STUDENT_ADDRESS)));
                studentModel.setPhoneNumber(cursor.getLong(cursor.getColumnIndex(STUDENT_PHONE_NUMBER)));

                studentsList.add(studentModel);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return  studentsList;
    }

    public int getStudentsCount() {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

    public int updateIndividualStudentDetails(StudentModel studentModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, studentModel.getName());
        values.put(STUDENT_COLLEGE, studentModel.getCollegeName());
        values.put(STUDENT_ADDRESS, studentModel.getAddress());
        values.put(STUDENT_PHONE_NUMBER, studentModel.getPhoneNumber());

        // updating row
        return sqLiteDatabase.update(TABLE_NAME, values, STUDENT_ID + " = ?",
                new String[]{String.valueOf(studentModel.getId())});
    }


}

