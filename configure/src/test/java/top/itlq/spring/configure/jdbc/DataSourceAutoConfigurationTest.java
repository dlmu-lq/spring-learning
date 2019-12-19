package top.itlq.spring.configure.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataSourceAutoConfigurationTest {
    @Autowired
    DataSource dataSource;

    @Test
    void testJdbc(){
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()){
                System.out.println();
                System.out.printf("%-5s%-5s%-5s%-10s",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDate("birth"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}