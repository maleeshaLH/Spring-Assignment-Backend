package lk.ijse.backend.controller;

import lk.ijse.backend.dto.impl.OrderDto;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.service.OrderServer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderServer orderServer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDto orderDto) {

        try {
            orderServer.saveOrder(orderDto);
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (DataPersistFailedException e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

