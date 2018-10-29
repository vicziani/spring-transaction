package jtechlog.springtransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    private LoggerDao loggerDao;

    public EmployeeDao(LoggerDao loggerDao) {
        this.loggerDao = loggerDao;
    }

    @Transactional
    public void saveEmployee(String name) throws IllegalArgumentException {
        entityManager.persist(new Employee(name));

        try {
            loggerDao.saveLog(name);
        } catch (IllegalArgumentException iae) {
            LOGGER.error("Error by logging", iae);
        }

        if (name != null && !name.isEmpty() && !Character.isUpperCase(name.charAt(0))) {
            throw new IllegalArgumentException("Cannot create employee with name starting with lowercase character");
        }
    }

    public Employee findEmployeeByName(String name) {
        return entityManager.createQuery("select e from Employee e where e.name = :name",
                Employee.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
