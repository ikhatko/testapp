package com.ikhatko.testapp.controller;

import com.ikhatko.testapp.exception.NotExistsException;
import com.ikhatko.testapp.model.Order;
import com.ikhatko.testapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Orders CRUD operations
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  /**
   * Get all orders
   * @return Order list
   */
  @GetMapping
  public ResponseEntity<List<Order>> getOrders() {
    return ResponseEntity.ok(orderService.getAllOrders());
  }

  /**
   * Get order by id
   * @param orderId order id for search
   * @return Order
   * @throws NotExistsException order not exists
   */
  @GetMapping("{orderId}")
  public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) throws NotExistsException {
    return ResponseEntity.ok(orderService.getOrderById(orderId));
  }

  /**
   * Find orders between dates
   * @param from format dd-MM-yyyy-HH:mm
   * @param to format dd-MM-yyyy-HH:mm
   * @return Order list
   */
  @PostMapping("find")
  public ResponseEntity<List<Order>> findOrdersByParams(@RequestParam("from") @DateTimeFormat(pattern = "dd-MM-yyyy-HH:mm") LocalDateTime from,
                                                        @RequestParam("to") @DateTimeFormat(pattern = "dd-MM-yyyy-HH:mm") LocalDateTime to) {
    return ResponseEntity.ok(orderService.findByDates(from, to));
  }

  /**
   * Create order from request body
   * @param order Order
   * @return created Order
   */
  @PostMapping
  public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
    return ResponseEntity.ok(orderService.createOrder(order));
  }

  @PutMapping
  public ResponseEntity<Order> updateOrder(@Valid @RequestBody Order order) {
    return ResponseEntity.ok(orderService.updateOrder(order));
  }

  @DeleteMapping("{orderId}")
  public ResponseEntity deleteOrderById(@PathVariable Integer orderId) throws NotExistsException {
    orderService.deleteOrder(orderId);
    return ResponseEntity.ok("Order deleted");
  }
}
