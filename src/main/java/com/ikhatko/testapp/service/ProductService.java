package com.ikhatko.testapp.service;

import com.ikhatko.testapp.exception.ProductAlreadyInOrderException;
import com.ikhatko.testapp.exception.NotExistsException;
import com.ikhatko.testapp.model.Product;
import com.ikhatko.testapp.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  public List<Product> getAllProducts() {
    return productDao.findAll();
  }

  public Product saveProduct(Product product) {
    return productDao.save(product);
  }

  public Product getProductById(Integer productId) throws NotExistsException {
    return productDao.findById(productId)
        .orElseThrow(() -> new NotExistsException("Product not found"));
  }

  public void deleteProduct(Integer productId) throws ProductAlreadyInOrderException, NotExistsException {
    if (productDao.existsById(productId)) {
      if (productDao.isProductInTheOrder(productId) > 0) {
        throw new ProductAlreadyInOrderException("Can't delete product, that already in the order");
      }
      productDao.deleteById(productId);
    } else {
      throw new NotExistsException(String.format("Product with id=%s not found", productId));
    }
  }
}
