package dev.isaac.tests.springjdbc;

import dev.isaac.tests.springjdbc.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJdbcApplication implements CommandLineRunner {

    @Autowired
    private TestDao dao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //var data = dao.findAll();
        var data = dao.findByGender("Female");
        for (var result : data) {
            System.out.println(result);
        }
        if (data.isEmpty()) System.out.println("No Results");
    }
}
