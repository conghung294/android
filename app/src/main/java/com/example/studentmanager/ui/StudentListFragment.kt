package com.example.studentmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.adapter.StudentAdapter
import com.example.studentmanager.model.Student

class StudentListFragment : Fragment() {
    private lateinit var studentAdapter: StudentAdapter
    private val students = mutableListOf<Student>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        val listView = view.findViewById<ListView>(R.id.lv_students)
        val addButton = view.findViewById<Button>(R.id.btn_add_student)

        studentAdapter = StudentAdapter(requireContext(), students,
            onEdit = { student ->
                val action = StudentListFragmentDirections.actionEditStudent(student.id)
                findNavController().navigate(action)
            },
            onRemove = { student ->
                students.remove(student)
                studentAdapter.notifyDataSetChanged()
            }
        )

        listView.adapter = studentAdapter

        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_add_student)
        }

        return view
    }
}
