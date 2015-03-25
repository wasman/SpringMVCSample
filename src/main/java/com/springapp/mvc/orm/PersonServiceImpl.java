package com.springapp.mvc.orm;

import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDAOImpl dao;
    private final TransactionTemplate transactionTemplate;

    public PersonServiceImpl(PersonDAOImpl dao, HibernateTransactionManager hibernateTransactionManager) {
        this.dao = dao;
        this.transactionTemplate = new TransactionTemplate(hibernateTransactionManager);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(final Person person) {
        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            public void doInTransactionWithoutResult(TransactionStatus status) {
                dao.save(person);
            }
        });

    }

    @Override
//    @Transactional(readOnly = true)
    public List<Person> list() {

        return this.transactionTemplate.execute(new TransactionCallback<List<Person>>() {

            @Override
            public List<Person> doInTransaction(TransactionStatus status) {
                return dao.list();
            }
        });
    }
}
