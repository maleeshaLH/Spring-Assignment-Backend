package lk.ijse.backend.service;

import lk.ijse.backend.dto.impl.ItemDto;

public interface ItemServer  {
    void saveItem(ItemDto item);

    void updateItem(ItemDto item);

    void deleteitem(String itemcode);
}
