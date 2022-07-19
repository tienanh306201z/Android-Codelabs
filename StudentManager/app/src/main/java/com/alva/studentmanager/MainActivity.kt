package com.alva.studentmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alva.studentmanager.adapters.StudentAdapter
import com.alva.studentmanager.data.getMockData

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        textView = findViewById(R.id.alert_text_view)

        if (getMockData.isEmpty()) {
            textView.visibility = View.VISIBLE
            return
        }

        textView.visibility = View.INVISIBLE

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        recyclerView.setHasFixedSize(true)
        val adapter = StudentAdapter(applicationContext, getMockData)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_student -> {
                startActivity(Intent(this, EditActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}