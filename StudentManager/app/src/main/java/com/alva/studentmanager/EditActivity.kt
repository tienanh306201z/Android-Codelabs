package com.alva.studentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                val builder = AlertDialog.Builder(this)
                builder.setTitle("This is alertDialog")
                builder.setMessage("Do you wanna update this student profile?")
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                //performing positive action
                builder.setPositiveButton("Yes") { dialogInterface, which ->
                    val student = Student(
                        id = 1,
                        studentID = studentIdEditText.text.toString().toInt(),
                        name = studentNameEditText.text.toString(),
                        email = studentEmailEditText.text.toString(),
                        birthDate = studentDateEditText.text.toString()
                    )
                    getMockData.add(student)
                    Log.d("Hello", getMockData.size.toString())
                    finish()
                }

                builder.setNegativeButton("No") { dialogInterface, which ->

                }
                val alertDialog: AlertDialog = builder.create()

                alertDialog.setCancelable(false)
                alertDialog.show()
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