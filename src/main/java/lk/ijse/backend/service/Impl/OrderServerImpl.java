package lk.ijse.backend.service.Impl;

import lk.ijse.backend.dao.ItemDao;
import lk.ijse.backend.dao.OrderDao;
import lk.ijse.backend.dto.impl.OrderDetailsDto;
import lk.ijse.backend.dto.impl.OrderDto;
import lk.ijse.backend.entity.ItemEntity;
import lk.ijse.backend.entity.OrderDetailsEntity;
import lk.ijse.backend.entity.OrderEntity;
import lk.ijse.backend.exception.DataPersistFailedException;
import lk.ijse.backend.exception.ItemNotFoundExseption;
import lk.ijse.backend.service.OrderServer;
import lk.ijse.backend.util.AppUtil;
import lk.ijse.backend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServerImpl implements OrderServer {

    private final OrderDao orderDao;
    private final ItemDao itemDao;
    private final Mapping mapping;
    @Override
    public void saveOrder(OrderDto orderDto) {

        //orderDto.setOrderId(generateOrderID());
        orderDto.setOrderDate(AppUtil.getCurrentDateTime());
        orderDto.setTotal(orderDto.getOrderDetails().stream().mapToDouble(detail -> detail.getQty() * detail.getUnitPrice()).sum());
        OrderEntity orderEntity = mapping.convertToOrderEntity(orderDto);


//        List<OrderDetailsEntity> orderDetailsEntities = orderDto.getOrderDetails()
//                        .stream().map(detail ->{
//                            OrderDetailsEntity orderDetailsEntity = mapping.convertToOrderDetailsEntity(detail);
//                            orderDetailsEntity.setOrder(orderEntity);
//                            return orderDetailsEntity;
//                }).collect(Collectors.toList());
//            orderEntity.setOrderDetails(orderDetailsEntities);
       // orderDao.save(orderEntity);
        boolean allItemUpdate = orderDto.getOrderDetails().stream().allMatch(this::updateItemQty);
        if (allItemUpdate) {
            orderDao.save(orderEntity);

        }else {
            throw new DataPersistFailedException("order save failed");
        }
    }
    private boolean updateItemQty(OrderDetailsDto orderDetailDTO) {
        ItemEntity item = itemDao.findById(orderDetailDTO.getItemcode()).orElse(null);
        if (item == null) {
            throw new ItemNotFoundExseption("Item not found");
        }

        if (item.getQty() < orderDetailDTO.getQty()) {
            throw new ItemNotFoundExseption("Item qty not enough");
        }

        item.setQty(item.getQty() - orderDetailDTO.getQty());
        itemDao.save(item);
        return true;
    }
    private String generateOrderID() {
        if (orderDao.count() == 0) {
            return "O001";
        } else {
            String lastId = String.valueOf(orderDao.findAll().get(orderDao.findAll().size() - 1).getOrderId());
            int newId = Integer.parseInt(lastId.substring(1)) + 1;
            if (newId < 10) {
                return "O00" + newId;
            } else if (newId < 100) {
                return "O0" + newId;
            } else {
                return "O" + newId;
            }
        }
    }
}
