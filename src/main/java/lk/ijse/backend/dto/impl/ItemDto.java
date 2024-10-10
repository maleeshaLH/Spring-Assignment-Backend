package lk.ijse.backend.dto.impl;

import lk.ijse.backend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto implements SuperDTO {
    private String itemcode;
    private String description;
    private int qty;
    private int price;
}

