package com.example.viewsdcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class ExternalDataActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var alertTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_data)

        recyclerView = findViewById(R.id.recycler_view)
        alertTextView = findViewById(R.id.alert_text_view)

        val path = intent.getStringExtra("path")

        val root = File(path!!)
        val filesAndFolders = root.listFiles()

        if (filesAndFolders == null || filesAndFolders.isEmpty()) {
            alertTextView.visibility = View.VISIBLE
            return
        }

        alertTextView.visibility = View.INVISIBLE

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        recyclerView.setHasFixedSize(true)
        val adapter = FileAdapter(applicationContext, filesAndFolders)
        recyclerView.adapter = adapter
    }
}