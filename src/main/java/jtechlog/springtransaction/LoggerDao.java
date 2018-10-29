package jtechlog.springtransaction;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class LoggerDao {

    @PersistenceContext
    private EntityManager entityManager;

    //@Transactional //(dontRollbackOn = IllegalArgumentException.class) //(Transactional.TxType.REQUIRES_NEW)
    public void saveLog(String message) throws IllegalArgumentException {
        entityManager.persist(new LogEntry(message));

        if (message.isEmpty()) {
            throw new IllegalArgumentException("Message is too short");
        }
    }
}
