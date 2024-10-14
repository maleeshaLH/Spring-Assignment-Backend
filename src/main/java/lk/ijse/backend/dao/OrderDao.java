package lk.ijse.backend.dao;

import lk.ijse.backend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity, Integer> {
}
