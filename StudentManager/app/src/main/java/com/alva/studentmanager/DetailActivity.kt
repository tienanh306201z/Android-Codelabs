package com.alva.studentmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    lateinit var studentId: TextView
    lateinit var studentName: TextView
    lateinit var studentEmail: TextView
    lateinit var studentDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        studentId = findViewById(R.id.studentId)
        studentName = findViewById(R.id.studentName)
        studentEmail = findViewById(R.id.studentEmail)
        studentDate = findViewById(R.id.studentBirthDate)

        studentId.text = intent.getStringExtra("studentId")
        studentName.text = intent.getStringExtra("studentName")
        studentEmail.text = intent.getStringExtra("studentEmail")
        studentDate.text = intent.getStringExtra("studentDate")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.edit_student -> {
                startActivity(Intent(this, EditActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}