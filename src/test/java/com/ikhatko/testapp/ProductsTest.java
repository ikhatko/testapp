package com.ikhatko.testapp;

import com.ikhatko.testapp.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.ikhatko.testapp.util.TestUtil.asByteArray;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getAllProducts() throws Exception {
    mvc.perform(get("/products")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(8)))
        .andExpect(jsonPath("$[0].name", is("iPhone 3")));
  }

  @Test
  public void getProductById() throws Exception {
    mvc.perform(get("/products/2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is("iPhone 4")));
  }

  @Test
  public void createProduct() throws Exception {
    Product newProduct = new Product();
    newProduct.setName("iPhone 12");
    newProduct.setDescription("new phone!");
    newProduct.setPrice(2000d);

    mvc.perform(post("/products")
        .contentType(MediaType.APPLICATION_JSON)
    .content(asByteArray(newProduct)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", is("iPhone 12")));
  }

  @Test
  public void deleteProductByIdInTheOrderError() throws Exception {
    mvc.perform(delete("/products/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
    .andExpect(content().string("Can't delete product, that already in the order"));
  }

  @Test
  public void deleteProductByIdNotExistsError() throws Exception {
    mvc.perform(delete("/products/55")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isGone())
    .andExpect(content().string("Product with id=55 not found"));
  }

  @Test
  public void deleteProductById() throws Exception {
    mvc.perform(delete("/products/8")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
