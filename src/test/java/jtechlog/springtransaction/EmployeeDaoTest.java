package jtechlog.springtransaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {"delete from employees", "delete from log_entries"})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSaveAndFind() {
        employeeDao.saveEmployee("John Doe");
        assertEquals("John Doe", employeeDao.findEmployeeByName("John Doe").getName());
    }
}
