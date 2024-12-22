package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class StudentServiceImplMockTest {
    @Autowired
    private  StudentService studentService;

    @MockBean
    private StudentDao studentDao;
    @SpyBean
    private StudentDao studentDaos;


    @Test
    public void insert(){
        Mockito.doReturn(100).when(studentDaos).insert(Mockito.any());
        Student student = new Student();
        student.setName("Test");
        student.setScore(87.5);
        student.setGraduate(false);
        Integer Id = studentService.insert(student);

        assertEquals(100,Id);
    }


    @BeforeEach
    public void beforeEach(){
        Student mockstudent = new Student();
        mockstudent.setId(100);
        mockstudent.setName("I'm Mock");

        Mockito.when(studentDao.getById(Mockito.any())).thenReturn(mockstudent);
    }

    @Test
    public void getById(){
        Student student = studentService.getById(3);
        assertNotNull(student);
        assertEquals(100,student.getId());
        assertEquals("I'm Mock",student.getName());
    }
    @Test
    public void getById2(){

        Student student = studentService.getById(2);
        assertNotNull(student);
        assertEquals(100,student.getId());
        assertEquals("I'm Mock",student.getName());
    }
}