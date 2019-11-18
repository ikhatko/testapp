package com.ikhatko.testapp.service;

import com.ikhatko.testapp.exception.NotExistsException;
import com.ikhatko.testapp.model.Order;
import com.ikhatko.testapp.model.Product;
import com.ikhatko.testapp.repository.OrderDao;
import com.ikhatko.testapp.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

  @Autowired
  private OrderDao orderDao;

  @Autowired
  private ProductDao productDao;

  public List<Order> getAllOrders() {
    return orderDao.findAll();
  }

  public Order getOrderById(Integer orderId) throws NotExistsException {
    return orderDao.findById(orderId)
        .orElseThrow(() -> new NotExistsException(String.format("Order with id=%s not found", orderId)));
  }

  public Order createOrder(Order order) {
    //get all product id's from order
    List<Integer> productIds = order.getProducts()
        .stream()
        .map(Product::getId)
        .collect(Collectors.toList());

    List<Product> allProducts = productDao.findAllById(productIds);

    //calculate total price for order
    double total = allProducts
        .stream()
        .mapToDouble(Product::getPrice)
        .sum();

    order.setTotal(total);
    order.setTimestamp(LocalDateTime.now());

    return orderDao.save(order);
  }

  public Order updateOrder(Order order) {
    return orderDao.save(order);
  }

  public void deleteOrder(Integer orderId) throws NotExistsException {
    if (orderDao.existsById(orderId)) {
      orderDao.deleteById(orderId);
    } else throw new NotExistsException(String.format("Order with id=%s not found", orderId));
  }

  public List<Order> findByDates(LocalDateTime from, LocalDateTime to) {
    return orderDao.findAllByTimestampIsBetween(from, to);
  }
}
