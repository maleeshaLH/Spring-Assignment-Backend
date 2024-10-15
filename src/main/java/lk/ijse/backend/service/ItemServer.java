package lk.ijse.backend.service;

import lk.ijse.backend.dto.impl.ItemDto;

import java.util.List;

public interface ItemServer  {
    void saveItem(ItemDto item);

    void updateItem(ItemDto item);

    void deleteItem(String id);

    List<ItemDto> getAllCustomer();
}
