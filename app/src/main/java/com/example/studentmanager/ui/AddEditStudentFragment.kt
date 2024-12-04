package com.example.studentmanager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studentmanager.R
import com.example.studentmanager.model.Student

class AddEditStudentFragment : Fragment() {
    private var studentId: Int? = null
    private lateinit var students: MutableList<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_student, container, false)
        val etName = view.findViewById<EditText>(R.id.et_name)
        val etStudentId = view.findViewById<EditText>(R.id.et_student_id)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        studentId = arguments?.getInt("studentId")
        val student = students.find { it.id == studentId }

        student?.let {
            etName.setText(it.name)
            etStudentId.setText(it.studentId)
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val studentId = etStudentId.text.toString()

            if (student == null) {
                val newStudent = Student(students.size + 1, name, studentId)
                students.add(newStudent)
            } else {
                student.name = name
                student.studentId = studentId
            }

            findNavController().popBackStack()
        }

        return view
    }
}
