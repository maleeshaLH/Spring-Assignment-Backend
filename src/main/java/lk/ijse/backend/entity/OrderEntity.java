package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements SuperEntity {
    @Id
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "id" ,nullable = false)
    private CustomerEntity  customer;
    private LocalDateTime  orderDate;
    private double total;
//    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<OrderDetailsEntity> orderDetails;
}
