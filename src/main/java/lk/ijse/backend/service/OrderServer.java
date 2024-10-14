package lk.ijse.backend.service;

import lk.ijse.backend.dto.impl.OrderDto;

public interface OrderServer {
    void saveOrder(OrderDto orderDto);
}
