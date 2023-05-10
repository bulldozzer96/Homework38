package org.example.repository;

import org.example.domain.Student;

import java.sql.*;
import java.util.*;



public class SqlRepository implements StudentsRepository {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_schema";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "ujdyj100";
    private static final String SELECT_FROM_STUDENTS = "SELECT * FROM students_list";
    private static final String INSERT_STUDENT = "INSERT INTO students_list (name, age, groupId) VALUES (?, ?, ?)";

    private static final String UPDATE_STUDENT_GROUP = "UPDATE students_list SET groupId = '99' WHERE groupId = ?";


    @Override
    public void save(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(INSERT_STUDENT);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getGroupId());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException ex) {

            }
            e.printStackTrace();
        } finally {
            try {
                assert conn != null;
                conn.close();
                assert ps != null;
                ps.close();
            } catch (SQLException e) {

            }
        }
    }

    @Override
    public void updateByGroupId(int groupId) {

        PreparedStatement ps = null;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            ps = conn.prepareStatement(UPDATE_STUDENT_GROUP);
            ps.setInt(1, groupId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ps != null;
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENTS)) {
            while (rs.next()) {
                Student student =  Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("groupId"))
                        .build();

                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



}
