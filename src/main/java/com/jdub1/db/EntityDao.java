package com.jdub1.db;

import com.jdub1.model.IBaseEntity;
import com.jdub1.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class EntityDao {

    public void saveOrUpdate(IBaseEntity entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try(Session session = factory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }
    }

    public <T extends IBaseEntity> List<T> list(Class<T> classT) {
        List<T> orderList = new ArrayList<>();
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try(Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = builder.createQuery(classT);

            Root<T> table = criteriaQuery.from(classT);
            criteriaQuery.select(table);
            orderList.addAll(session.createQuery(criteriaQuery).list());
        }
        return orderList;
    }

    public <T extends IBaseEntity> T getById(Class<T> classT, Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try(Session session = factory.openSession()) {
            T result = session.get(classT, id);
            return result;
        }
    }
}
