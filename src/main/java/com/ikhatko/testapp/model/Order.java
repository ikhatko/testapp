package com.ikhatko.testapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "Buyer email is required.")
  @NotEmpty(message = "Buyer email is required.")
  @Email(message = "Wrong email format")
  private String buyerEmail;

  @JsonFormat(pattern = "dd-MM-yyyy-HH:mm")
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime timestamp;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Double total;

  @ManyToMany
  @JoinTable(
      name = "order2product",
      joinColumns = @JoinColumn(name = "fk_order_id"),
      inverseJoinColumns = @JoinColumn(name = "fk_product_id"))
  private List<Product> products;

  public Integer getId() {
    return id;
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
