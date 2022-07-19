package com.alva.studentmanager.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alva.studentmanager.DetailActivity
import com.alva.studentmanager.EditActivity
import com.alva.studentmanager.R
import com.alva.studentmanager.models.Student

class StudentAdapter(private val context: Context, private val list: Array<Student>) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.student_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedStudent = list[position]
        holder.studentName.text = selectedStudent.name
        holder.studentId.text = selectedStudent.studentID.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("studentId", selectedStudent.studentID.toString())
            intent.putExtra("studentName", selectedStudent.name)
            intent.putExtra("studentEmail", selectedStudent.email)
            intent.putExtra("studentDate", selectedStudent.birthDate)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.student_name_text_view)
        val studentId: TextView = itemView.findViewById(R.id.student_id_text_view)
    }
}