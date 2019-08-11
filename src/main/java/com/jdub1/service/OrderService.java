package com.jdub1.service;

import com.jdub1.db.EntityDao;
import com.jdub1.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {
    private EntityDao entityDao = new EntityDao();

    public void add(Order order) {
        entityDao.saveOrUpdate(order);
    }

    public List<Order> findAll() {
        return entityDao.list(Order.class);
    }

    public void delivered(Long id) {
        Order delivered = entityDao.getById(Order.class, id);
        delivered.setTimeDelivered(LocalDateTime.now());

        entityDao.saveOrUpdate(delivered);
    }

    public void paid(Long id, Double amount) {
        Order order = entityDao.getById(Order.class, id);
        order.setPaid(amount);

        if (order.getPaid() < order.getToPay()) {
            System.err.println("Ty brzydalu za mala kwota");
        } else if (order.getPaid() == order.getToPay()) {
            System.err.println("Ty brzydalu nie dales napiwku");
        }
        entityDao.saveOrUpdate(order);
    }
}
