package lk.ijse.backend.controller;

import lk.ijse.backend.dto.impl.CustomerDto;
import lk.ijse.backend.exception.CustomerNotFoundException;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.service.CustomerServer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerServer customerServer;
    @GetMapping("health")
    public String healthCheck(){
        return "Health Check is working";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createCustomer(
            @RequestPart("id") String id,
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("salary") String salary){

        try {
            CustomerDto dto =new CustomerDto();
            dto.setId(id);
            dto.setName(name);
            dto.setAddress(address);
            dto.setSalary(salary);
            customerServer.saveCustomer(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") String id){
        try {
            customerServer.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") String id,
            @RequestPart("name") String name,
            @RequestPart("address") String address,
            @RequestPart("salary") String salary) {
        try {
            CustomerDto UpdateDto =new CustomerDto();
            UpdateDto.setId(id);
            UpdateDto.setName(name);
            UpdateDto.setAddress(address);
            UpdateDto.setSalary(salary);
            customerServer.updateCustomer(UpdateDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<CustomerDto> getAllCustomers() {
        return customerServer.getAllCustomer();
    }
}
