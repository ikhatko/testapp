package com.ikhatko.testapp.controller;

import com.ikhatko.testapp.exception.NotExistsException;
import com.ikhatko.testapp.exception.ProductAlreadyInOrderException;
import com.ikhatko.testapp.model.Product;
import com.ikhatko.testapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Products CRUD operations
 */
@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * Get all Products
   * @return Product list
   */
  @GetMapping
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  /**
   * Find and return Product by id or error
   * @param productId id of searching product
   * @return Product
   * @throws NotExistsException product not exists
   */
  @GetMapping("{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable Integer productId) throws NotExistsException {
    return ResponseEntity.ok(productService.getProductById(productId));
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    return ResponseEntity.ok(productService.saveProduct(product));
  }

  @PutMapping
  public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
    return ResponseEntity.ok(productService.saveProduct(product));
  }

  /**
   * Delete product by id
   * @param productId product id
   * @return Ok status or error
   * @throws ProductAlreadyInOrderException  we can't delete product that is in the order
   * @throws NotExistsException product not exists
   */
  @DeleteMapping("{productId}")
  public ResponseEntity deleteProductById(@PathVariable Integer productId) throws ProductAlreadyInOrderException, NotExistsException {
    productService.deleteProduct(productId);
    return ResponseEntity.ok("Product deleted");
  }
}
