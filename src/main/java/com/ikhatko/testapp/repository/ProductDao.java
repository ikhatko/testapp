package com.ikhatko.testapp.repository;

import com.ikhatko.testapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

  @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM order2product WHERE fk_product_id =:productId")
  long isProductInTheOrder(@Param("productId") Integer productId);
}
