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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        orderDto.setOrderId(AppUtil.createOrderId());
        orderDto.setOrderDate(AppUtil.getCurrentDateTime());
        orderDto.setTotal(orderDto.getOrderDetails().stream().mapToDouble(detail -> detail.getQty() * detail.getUnitPrice()).sum());
        OrderEntity orderEntity = mapping.convertToOrderEntity(orderDto);


//        List<OrderDetailsEntity> orderDetailEntities = orderDto.getOrderDetails().stream().map(detail -> {
//                    OrderDetailsEntity orderDetailEntity = mapping.convertToOrderDetailsEntity(detail);
//                    orderDetailEntity.setOrder(orderEntity);
//                    return orderDetailEntity;
//                })
//                .collect(Collectors.toList());
//
//        orderEntity.setOrderDetails(orderDetailEntities);

        List<OrderDetailsEntity> orderDetailEntities = new ArrayList<>();
        try {
            orderDetailEntities = orderDto.getOrderDetails().stream()
                    .map(detail -> {
                        try {
                            OrderDetailsEntity orderDetailEntity = mapping.convertToOrderDetailsEntity(detail);
                            orderDetailEntity.setOrder(orderEntity);
                            return orderDetailEntity;
                        } catch (Exception e) {
                            // Log the exception and return null to avoid adding a faulty entity to the list
                            System.err.println("Error converting order detail: " + e.getMessage());
                            return null; // or handle it in another way
                        }
                    })
                    .filter(Objects::nonNull) // Filter out null values in case of conversion errors
                    .collect(Collectors.toList());

            orderEntity.setOrderDetails(orderDetailEntities);

        } catch (NullPointerException e) {
            System.err.println("NullPointerException: OrderDto or its details are null - " + e.getMessage());
            // Handle the error (e.g., log, throw custom exception, or set default values)
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            // Handle any other unexpected errors
        }

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

}
