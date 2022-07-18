package com.example.viewsdcard

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class FileAdapter(private val context: Context, private val filesAndFolders: Array<File>) :
    RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.file_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedFile = filesAndFolders[position]
        holder.textView.text = selectedFile.name
        if (selectedFile.isDirectory) {
            holder.imageView.setImageResource(R.drawable.folder)
        } else {
            holder.imageView.setImageResource(R.drawable.file)
        }

        holder.itemView.setOnClickListener {
            if (selectedFile.isDirectory) {
                val intent = Intent(context, ExternalDataActivity::class.java)
                val path = selectedFile.absolutePath
                intent.putExtra("path", path)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else {
                try {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    val trailingSubList =
                        selectedFile.absolutePath.substring(selectedFile.absolutePath.length - 3)
                            .lowercase()
                    Log.d("Gello", selectedFile.absolutePath)
                    when (trailingSubList) {
                        "jpg", "png" -> {
                            val type = "image/jpeg"
                            intent.setDataAndType(
                                Uri.parse(selectedFile.absolutePath).normalizeScheme(),
                                Intent.normalizeMimeType(type)
                            )
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        "doc", "ocx", "txt" -> {
                            val type = "text/plain"
                            intent.setDataAndType(
                                Uri.parse(selectedFile.absolutePath).normalizeScheme(),
                                Intent.normalizeMimeType(type)
                            )
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        else -> {
                            return@setOnClickListener
                        }
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        context.applicationContext,
                        "Cannot open the file",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        holder.edit.setOnClickListener { view ->
            val popupMenu = PopupMenu(context, view)
            popupMenu.menu.add("DELETE")
            popupMenu.menu.add("MOVE")
            popupMenu.menu.add("RENAME")

            popupMenu.setOnMenuItemClickListener { item ->
                if (item.title.equals("DELETE")) {
                    val deleted = selectedFile.delete()
                    if (deleted) {
                        Toast.makeText(
                            context.applicationContext,
                            "DELETED ",
                            Toast.LENGTH_SHORT
                        ).show()
                        view.visibility = View.GONE
                    }
                }
                if (item.title.equals("MOVE")) {
                    Toast.makeText(context.applicationContext, "MOVED ", Toast.LENGTH_SHORT)
                        .show()
                }
                if (item.title.equals("RENAME")) {
                    Toast.makeText(context.applicationContext, "RENAME ", Toast.LENGTH_SHORT).show()
                }
                true
            }
            popupMenu.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return filesAndFolders.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.icon_view)
        val textView: TextView = itemView.findViewById(R.id.file_text_view)
        val edit: ImageView = itemView.findViewById(R.id.edit)
    }
}