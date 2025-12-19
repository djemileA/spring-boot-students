package com.example.students.controller

import com.example.students.entity.Student
import com.example.students.service.StudentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {
    
    @GetMapping
    fun getAllStudents(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) hobby: String?,
        model: Model
    ): String {
        val students = studentService.searchStudents(name, hobby)
        model.addAttribute("students", students)
        model.addAttribute("searchName", name ?: "")
        model.addAttribute("searchHobby", hobby ?: "")
        return "students/list"
    }
    
    @GetMapping("/new")
    fun showAddForm(model: Model): String {
        model.addAttribute("student", Student())
        return "students/add"
    }
    
    @PostMapping
    fun addStudent(@ModelAttribute student: Student): String {
        studentService.saveStudent(student)
        return "redirect:/students"
    }
    
    @GetMapping("/{id}/edit")
    fun showEditForm(@PathVariable id: Long, model: Model): String {
        val student = studentService.getStudentById(id)
        if (student != null) {
            model.addAttribute("student", student)
            return "students/edit"
        }
        return "redirect:/students"
    }
    
    @PostMapping("/{id}")
    fun updateStudent(@PathVariable id: Long, @ModelAttribute student: Student): String {
        val existingStudent = studentService.getStudentById(id)
        if (existingStudent != null) {
            existingStudent.firstName = student.firstName
            existingStudent.lastName = student.lastName
            existingStudent.age = student.age
            existingStudent.email = student.email
            studentService.saveStudent(existingStudent)
        }
        return "redirect:/students"
    }
    
    @GetMapping("/{id}/delete")
    fun deleteStudent(@PathVariable id: Long): String {
        studentService.deleteStudent(id)
        return "redirect:/students"
    }
    
    @PostMapping("/{id}/hobbies")
    fun addHobby(@PathVariable id: Long, @RequestParam hobby: String): String {
        studentService.addHobbyToStudent(id, hobby)
        return "redirect:/students/$id/edit"
    }
    
    @GetMapping("/{id}/hobbies/{hobby}/delete")
    fun removeHobby(@PathVariable id: Long, @PathVariable hobby: String): String {
        studentService.removeHobbyFromStudent(id, hobby)
        return "redirect:/students/$id/edit"
    }
}
