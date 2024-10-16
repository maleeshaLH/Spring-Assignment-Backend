package lk.ijse.backend.dto.impl;

import lk.ijse.backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDto implements SuperDTO  {
    private String orderDetailsId;
    private String itemcode;
    private int qty;
    private double unitPrice;
    private double total;
}
