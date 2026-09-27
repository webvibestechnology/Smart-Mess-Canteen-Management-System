package com.smartmess.smart_mess_management.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartmess.smart_mess_management.Services.FoodOrderService;
import com.smartmess.smart_mess_management.entity.FoodOrder;
import com.smartmess.smart_mess_management.entity.Payment;
import com.smartmess.smart_mess_management.repository.FoodOrderRepository;
import com.smartmess.smart_mess_management.repository.PaymentRepository;

import enums.OrderStatus;
import enums.PaymentStatus;
import enums.PaymentType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodOrderServiceImpl implements FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;

    private final PaymentRepository paymentRepository;


    @Override
    public FoodOrder createOrder(FoodOrder order) {

        BigDecimal total = order.getOrderItems()
                .stream()
                .map(item ->
                        item.getUnitPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                )
                )
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

        order.setTotalAmount(total);

        order.setStatus(OrderStatus.PENDING);

        return foodOrderRepository.save(order);
    }


    @Override
    public FoodOrder getOrderById(Long id) {

        return foodOrderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + id
                        )
                );
    }


    @Override
    public List<FoodOrder> getAllOrders() {

        return foodOrderRepository.findAll();
    }


    @Override
    public void deleteOrder(Long id) {

        foodOrderRepository.deleteById(id);
    }


    @Override
    public List<FoodOrder> getOrdersByStudent(Long studentId) {

        return foodOrderRepository.findByStudentId(studentId);
    }


    @Override
    public List<FoodOrder> getOrdersByStatus(
            OrderStatus status) {

        return foodOrderRepository.findByStatus(status);
    }


    @Override
    public FoodOrder updateOrderStatus(
            Long orderId,
            OrderStatus status) {

        FoodOrder order = foodOrderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with id: " + orderId
                        )
                );


        // Check previous status

        OrderStatus previousStatus = order.getStatus();


        // Update order status

        order.setStatus(status);

        FoodOrder savedOrder =
                foodOrderRepository.save(order);


        // Create payment only when order becomes DELIVERED

        if (status == OrderStatus.DELIVERED
                && previousStatus != OrderStatus.DELIVERED) {

            Payment payment = new Payment();

            payment.setStudent(order.getStudent());

            payment.setAmount(order.getTotalAmount());

            payment.setType(PaymentType.CANTEEN_ORDER);

            payment.setStatus(PaymentStatus.COMPLETED);

            payment.setTransactionId(
                    "ORDER-" + order.getId()
            );

            payment.setPaymentMethod("ONLINE");

            paymentRepository.save(payment);

            System.out.println(
                    "Payment created for Order ID: "
                    + order.getId()
            );
        }


        return savedOrder;
    }
}