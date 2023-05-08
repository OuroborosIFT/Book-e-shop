package com.dsu.arslan.coursework.service;

import com.dsu.arslan.coursework.domain.Order;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(Long id);

}
