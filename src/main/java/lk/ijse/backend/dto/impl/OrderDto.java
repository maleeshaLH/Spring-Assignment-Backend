package lk.ijse.backend.dto.impl;

import jakarta.persistence.OneToMany;
import lk.ijse.backend.dto.SuperDTO;
import lk.ijse.backend.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements SuperDTO {
    private String orderId;
    private LocalDateTime  orderDate;
    private String customerId;
    private List<OrderDetailsDto> orderDetails;
    private double total;

}
