package lk.ijse.backend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.backend.dao.CustomerDao;
import lk.ijse.backend.dto.impl.CustomerDto;
import lk.ijse.backend.entity.CustomerEntity;
import lk.ijse.backend.exception.CustomerNotFoundException;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.service.CustomerServer;
import lk.ijse.backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServerImpl implements CustomerServer {

    @Autowired
    private final CustomerDao customerDao;
    @Autowired
    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto dto) {
        CustomerEntity SaveCustomer = customerDao.save(mapping.convertToCustomerEntity(dto));

        if (SaveCustomer == null && SaveCustomer.getId() == null) {
            throw new DataPersistFailedException("Save customer failed");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        if (customerDao.existsById(id)) {
            customerDao.deleteById(id);
        }else {
            throw new CustomerNotFoundException("Delete not Customer");
        }
    }

    @Override
    public void updateCustomer(CustomerDto updateDto) {
        CustomerEntity updateCustomer =customerDao.save(mapping.convertToCustomerEntity(updateDto));
        if (updateCustomer == null && updateDto.getId() == null) {
            throw new DataPersistFailedException("Update customer failed");
        }
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return mapping.convertCustomerToDTOList(customerDao.findAll());
    }
}
