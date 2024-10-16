package lk.ijse.backend.controller;

import lk.ijse.backend.dto.impl.CustomerDto;
import lk.ijse.backend.dto.impl.ItemDto;
import lk.ijse.backend.exception.CustomerNotFoundException;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.exception.ItemNotFoundExseption;
import lk.ijse.backend.service.ItemServer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private final ItemServer itemServer;

    @GetMapping("health")
    public String healthCheck(){
        return "Health Check is working";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveItem(
            @RequestPart("itemcode")String itemcode,
            @RequestPart("desciption") String description,
            @RequestPart("qty") String qty,
            @RequestPart("price") String price
    ){
        try {
            ItemDto item =new ItemDto();
            item.setItemcode(itemcode);
            item.setDescription(description);
            item.setQty(Integer.parseInt(qty));
            item.setPrice(Integer.parseInt(price));
            itemServer.saveItem(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{itemcode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateItem(
            @PathVariable("itemcode")String itemcode,
            @RequestPart("desciption") String description,
            @RequestPart("qty") String qty,
            @RequestPart("price") String price
    ){
        try {
            ItemDto item =new ItemDto();
            item.setItemcode(itemcode);
            item.setDescription(description);
            item.setQty(Integer.parseInt(qty));
            item.setPrice(Integer.parseInt(price));
            itemServer.updateItem(item);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistFailedException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id){
        try {
            itemServer.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (ItemNotFoundExseption e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllCustomers() {
        return itemServer.getAllCustomer();
    }

}
