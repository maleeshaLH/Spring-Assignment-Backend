package lk.ijse.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String salary;
    @OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
    private List<OrderEntity> orders;
}
