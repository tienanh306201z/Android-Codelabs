package com.alva.studentmanager.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alva.studentmanager.models.Student

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    companion object {
        private val DATABASE_NAME = "StudentManagerDB"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "student"

        val ID_COL = "id"
        val STUDENT_ID_COL = "student_id"
        val NAME_COl = "name"
        val EMAIL_COL = "email"
        val BIRTH_DATE_COL = "birth_date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                STUDENT_ID_COL + " INTEGER, " +
                NAME_COl + " TEXT, " +
                EMAIL_COL + " TEXT, " +
                BIRTH_DATE_COL + " TEXT" + ")")
        db.execSQL(query)

        db.execSQL(
            "insert into StudentManagerDB($STUDENT_ID_COL, $NAME_COl, $EMAIL_COL, $BIRTH_DATE_COL) values(20193992,'Tran Tien Anh','abc@gmail.com','30/06/2001')"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addStudent(studentId: Int, name: String, email: String, birthDate: String) {
        val values = ContentValues()

        values.put(STUDENT_ID_COL, studentId)
        values.put(NAME_COl, name)
        values.put(EMAIL_COL, email)
        values.put(BIRTH_DATE_COL, birthDate)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun getStudentList(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    fun getStudent(id: Int): Array<Student> {
        val arrayStudent = mutableListOf<Student>()
        val db = this.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE id = $id", null)

//        cursor!!.moveToFirst()
//        arrayStudent.add(Student(id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL)) + "\n"))
//
//        // moving our cursor to next
//        // position and appending values
//        while (cursor.moveToNext()) {
//            Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
//            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
//        }
//
//        // at last we close our cursor
//        cursor.close()
        return arrayOf()
    }
}