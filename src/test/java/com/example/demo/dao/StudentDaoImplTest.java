package com.example.demo.dao;

import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentDaoImplTest {
    @Autowired
    private StudentDao studentDao;

    @Test
    public void getById(){
        Student student = studentDao.getById(1);
        assertNotNull(student);
        assertEquals("Amy",student.getName());
        assertEquals(90.3,student.getScore());
        assertTrue(student.isGraduate());
        assertNotNull(student.getCreateDate());

    }
    //家上Transactional 可以在測試時 有針對 資料庫的變動 roll back
    @Transactional
    @Test
    public void deletebyId(){
        studentDao.deleteById(5);
        Student student = studentDao.getById(5);
        assertNull(student);

    }
    @Transactional
    @Test
    public void insert(){
        Student student = new Student();
        student.setName("Howard");
        student.setScore(66.2);
        student.setGraduate(true);
        Integer studentID = studentDao.insert(student);
        Student result = studentDao.getById(studentID);
        assertNotNull(result);
        assertEquals("Howard",result.getName());
        assertEquals(66.2,result.getScore());
        assertTrue(result.isGraduate());
        assertNotNull(result.getCreateDate());
    }
    @Transactional
    @Test
    public void update(){
        Student student = studentDao.getById(3);
        student.setName("John");

        studentDao.update(student);

        Student result = studentDao.getById(3);
        assertNotNull(result);
        assertEquals("John",result.getName());

    }

}