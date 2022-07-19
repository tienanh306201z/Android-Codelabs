package com.alva.studentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.alva.studentmanager.data.getMockData
import com.alva.studentmanager.models.Student

class EditActivity : AppCompatActivity() {
    lateinit var studentIdEditText: EditText
    lateinit var studentNameEditText: EditText
    lateinit var studentEmailEditText: EditText
    lateinit var studentDateEditText: EditText

    lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentIdEditText = findViewById(R.id.student_id_edit_text)
        studentNameEditText = findViewById(R.id.student_name_edit_text)
        studentEmailEditText = findViewById(R.id.student_email_edit_text)
        studentDateEditText = findViewById(R.id.student_date_edit_text)

        updateButton = findViewById(R.id.updateButton)

        updateButton.setOnClickListener {
            if (!studentIdEditText.text.equals("") && !studentNameEditText.text.equals("")
                && !studentEmailEditText.text.equals("") && !studentDateEditText.text.equals("")
            ) {
                val student = Student(
                    id = 1,
                    studentID = studentIdEditText.text.toString().toInt(),
                    name = studentNameEditText.text.toString(),
                    email = studentEmailEditText.text.toString(),
                    birthDate = studentDateEditText.text.toString()
                )
                getMockData.add(student)
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}