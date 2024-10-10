package lk.ijse.backend.dto.impl;

import lk.ijse.backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto implements SuperDTO {
    private String id;
    private String name;
    private String address;
    private String salary;
}
