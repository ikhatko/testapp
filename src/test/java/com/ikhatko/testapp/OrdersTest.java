package com.ikhatko.testapp;

import com.ikhatko.testapp.model.Order;
import com.ikhatko.testapp.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.ikhatko.testapp.util.TestUtil.asByteArray;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrdersTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getAllOrders() throws Exception {
    mvc.perform(get("/orders")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(4)))
        .andExpect(jsonPath("$[0].buyerEmail", is("example1@mail.com")))
        .andExpect(jsonPath("$[0].total").value(1100.0d));
  }

  @Test
  public void getOrderById() throws Exception {
    mvc.perform(get("/orders/2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("products.length()").value(2))
        .andExpect(jsonPath("products[0].name", is("iPhone 4")))
        .andExpect(jsonPath("buyerEmail", is("example2@mail.com")));
  }

  @Test
  public void createOrder() throws Exception {
    Order newOrder = new Order();
    newOrder.setBuyerEmail("mail@mail.com");
    Product product = new Product();
    product.setId(1);
    Product product1 = new Product();
    product1.setId(2);
    newOrder.setProducts(Arrays.asList(product, product1));

    mvc.perform(post("/orders")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asByteArray(newOrder)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("buyerEmail", is("mail@mail.com")))
        .andExpect(jsonPath("total").value(300.0d));
  }

  @Test
  public void deleteOrderByIdNotExistsError() throws Exception {
    mvc.perform(delete("/orders/55")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isGone())
        .andExpect(content().string("Order with id=55 not found"));
  }

  @Test
  public void deleteOrderById() throws Exception {
    mvc.perform(delete("/orders/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
