package lk.ijse.backend.service;

import lk.ijse.backend.dto.impl.CustomerDto;

import java.util.List;

public interface CustomerServer{
    void saveCustomer(CustomerDto dto);

    void deleteCustomer(String id);

    void updateCustomer(CustomerDto updateDto);

    List<CustomerDto> getAllCustomer();

}
