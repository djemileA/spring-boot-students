package com.example.students.repository

import com.example.students.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {
    
    fun findByFirstNameContainingIgnoreCase(firstName: String): List<Student>
    
    fun findByLastNameContainingIgnoreCase(lastName: String): List<Student>
    
    @Query("SELECT s FROM Student s WHERE :hobby MEMBER OF s.hobbies")
    fun findByHobby(@Param("hobby") hobby: String): List<Student>
    
    @Query("SELECT s FROM Student s WHERE LOWER(s.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    fun findByNameContaining(@Param("name") name: String): List<Student>
    
    @Query("SELECT DISTINCT s FROM Student s JOIN s.hobbies h WHERE LOWER(h) LIKE LOWER(CONCAT('%', :hobby, '%'))")
    fun findByHobbyContaining(@Param("hobby") hobby: String): List<Student>
}
