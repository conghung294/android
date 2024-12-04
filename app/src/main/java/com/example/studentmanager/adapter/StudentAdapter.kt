package com.example.studentmanager.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import android.widget.BaseAdapter
import com.example.studentmanager.R
import com.example.studentmanager.model.Student

class StudentAdapter(
    private val context: Context,
    private val students: MutableList<Student>,
    private val onEdit: (Student) -> Unit,
    private val onRemove: (Student) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = students.size
    override fun getItem(position: Int): Any = students[position]
    override fun getItemId(position: Int): Long = students[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        val nameTextView = view.findViewById<TextView>(android.R.id.text1)
        val idTextView = view.findViewById<TextView>(android.R.id.text2)

        val student = students[position]
        nameTextView.text = student.name
        idTextView.text = student.studentId

        view.setOnLongClickListener {
            val popup = PopupMenu(context, it)
            popup.menuInflater.inflate(R.menu.context_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.edit -> onEdit(student)
                    R.id.remove -> onRemove(student)
                }
                true
            }
            popup.show()
            true
        }

        return view
    }
}
