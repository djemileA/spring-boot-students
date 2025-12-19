package com.example.students.service

import com.example.students.entity.Student
import com.example.students.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {
    
    fun getAllStudents(): List<Student> {
        return studentRepository.findAll()
    }
    
    fun getStudentById(id: Long): Student? {
        return studentRepository.findById(id).orElse(null)
    }
    
    @Transactional
    fun saveStudent(student: Student): Student {
        return studentRepository.save(student)
    }
    
    @Transactional
    fun deleteStudent(id: Long) {
        studentRepository.deleteById(id)
    }
    
    fun searchStudents(name: String?, hobby: String?): List<Student> {
        return when {
            !name.isNullOrBlank() && !hobby.isNullOrBlank() -> {
                val byName = studentRepository.findByNameContaining(name)
                val byHobby = studentRepository.findByHobbyContaining(hobby)
                byName.intersect(byHobby.toSet()).toList()
            }
            !name.isNullOrBlank() -> studentRepository.findByNameContaining(name)
            !hobby.isNullOrBlank() -> studentRepository.findByHobbyContaining(hobby)
            else -> studentRepository.findAll()
        }
    }
    
    @Transactional
    fun addHobbyToStudent(studentId: Long, hobby: String): Student? {
        return studentRepository.findById(studentId).map { student ->
            student.addHobby(hobby)
            studentRepository.save(student)
        }.orElse(null)
    }
    
    @Transactional  
    fun removeHobbyFromStudent(studentId: Long, hobby: String): Student? {
        return studentRepository.findById(studentId).map { student ->
            student.removeHobby(hobby)
            studentRepository.save(student)
        }.orElse(null)
    }
}
