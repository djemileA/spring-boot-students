package com.example.students.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(name = "first_name", nullable = false)
    var firstName: String = "",
    
    @Column(name = "last_name", nullable = false)
    var lastName: String = "",
    
    @Column(name = "age")
    var age: Int = 0,
    
    @Column(name = "email")
    var email: String = "",
    
    @ElementCollection
    @CollectionTable(name = "student_hobbies", joinColumns = [JoinColumn(name = "student_id")])
    @Column(name = "hobby")
    var hobbies: MutableSet<String> = mutableSetOf(),
    
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun addHobby(hobby: String) {
        hobbies.add(hobby)
    }
    
    fun removeHobby(hobby: String) {
        hobbies.remove(hobby)
    }
}
