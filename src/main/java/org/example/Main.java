package org.example;


import org.example.domain.*;
import org.example.repository.*;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        String name = StudentGenerator.studentName();
        int age = new Random().nextInt(18, 21);
        int groupId = new Random().nextInt(1, 12);

        Student newStudent = Student.builder()
                .name(name)
                .age(age)
                .groupId(groupId)
                .build();

        StudentsRepository studentRepository = new SqlRepository();

        studentRepository.save(newStudent);
        studentRepository.updateByGroupId(2);

        List<Student> studentList = studentRepository.findAll();

        //System.out.println(studentList);
        studentList.forEach(System.out::println);
    }
}