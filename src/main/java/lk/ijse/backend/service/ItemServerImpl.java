package lk.ijse.backend.service;

import jakarta.transaction.Transactional;
import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.dto.impl.ItemDto;
import lk.ijse.backend.entity.ItemEntity;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServerImpl implements ItemServer{
    @Autowired
    private final ItemDao itemDao;
    @Autowired
    private final Mapping mapping;
    @Override
    public void saveItem(ItemDto item) {
       ItemEntity itemEntity  =itemDao.save(mapping.convertToItemEntity(item));

       if (itemEntity != null && itemEntity.getItemcode() == null) {
           throw new DataPersistFailedException("Cannot save new item");
       }
    }
}
