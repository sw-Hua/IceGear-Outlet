package org.ntu.service;

import org.ntu.domain.Order;

public interface IOrderService {
    Order createOrder(Long productId, Long userId);
}
