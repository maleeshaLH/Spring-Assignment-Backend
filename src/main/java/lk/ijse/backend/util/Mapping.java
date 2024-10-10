package lk.ijse.backend.util;

import lk.ijse.backend.dto.impl.CustomerDto;
import lk.ijse.backend.dto.impl.ItemDto;
import lk.ijse.backend.entity.CustomerEntity;
import lk.ijse.backend.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
     //Customer Mapping
     public CustomerEntity convertToCustomerEntity(CustomerDto customerDto) {
         return modelMapper.map(customerDto, CustomerEntity.class);
     }
    public CustomerDto convertToCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }
    public List<CustomerDto> convertCustomerToDTOList(List<CustomerEntity> customerEntities) {
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDto>>() {}.getType());
    }

    public ItemEntity convertToItemEntity(ItemDto itemDto) {
         return modelMapper.map(itemDto, ItemEntity.class);
    }
}
