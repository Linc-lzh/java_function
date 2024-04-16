package com.itheima.day4.framework.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@MapperScan
public class TestMyBatisPlus {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestMyBatisPlus.class, args);
        StudentMapper mapper = context.getBean(StudentMapper.class);

        test(mapper, List.of("赵一伤"));
    }

    static void test(StudentMapper mapper, List<String> names) {
        LambdaQueryWrapper<Student> query = new LambdaQueryWrapper<>();
        // where name in (?,?,...)
        // stu -> stu.getName()
        query.in(!names.isEmpty(), stu -> stu.getName(), names);
        /*
            in => in
            eq => =
            ...

            Student::getName => 列名
         */

        for (Student student : mapper.selectList(query)) {
            System.out.println(student);
        }
    }
}
