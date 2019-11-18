package com.ikhatko.testapp.repository;

import com.ikhatko.testapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

  @Query("select o from Order o where o.timestamp between :from and :to")
  List<Order> findAllByTimestampIsBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
