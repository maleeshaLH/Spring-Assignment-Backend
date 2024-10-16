package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity implements SuperEntity {
    @Id
    private String orderDetailsId;
    @ManyToOne
    @JoinColumn(name = "orderid", referencedColumnName = "orderId")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "itemcode", referencedColumnName = "itemcode")
    private ItemEntity item;
    private double unitPrice;
    private int qty;
    private double totalPrice;




}
