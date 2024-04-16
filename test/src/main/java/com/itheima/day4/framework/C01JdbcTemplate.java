package com.itheima.day4.framework;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// JdbcTemplate
public class C01JdbcTemplate {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        /*List<Student> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stat = conn.prepareStatement("select * from student");
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                list.add(new Student(id, name, sex));
            }
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        JdbcTemplate template = new JdbcTemplate(dataSource);
        List<Student> list = template.query("select * from student", (rs, row) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            return new Student(id, name, sex);
        });
        for (Student student : list) {
            System.out.println(student);
        }
    }

    record Student(int id, String name, String sex) {

    }
}
