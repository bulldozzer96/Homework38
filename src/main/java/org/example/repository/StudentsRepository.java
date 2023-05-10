package org.example.repository;

import org.example.domain.Student;

import java.util.List;

public interface StudentsRepository {
    void save(Student student);
    void updateByGroupId(int groupId);
    List<Student> findAll();

}
