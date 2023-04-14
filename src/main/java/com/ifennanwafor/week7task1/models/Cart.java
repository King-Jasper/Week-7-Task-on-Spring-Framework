//package com.ifennanwafor.week7task1.models;
//
//import com.ifennanwafor.week7task1.dto.ProductDto;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.apache.catalina.User;
//
//
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//
//@Data
//@Entity
//@Table(name="Cart")
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @OneToOne
//    @JoinColumn(name = "product")
//    private Product product;
//
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
//    private User user;
//
//    private int quantity;
//
//    public Cart(CartDto cartDto){
//    this.id = cartDto.getId();
//    this.product = cartDto.getId();
//    this.user=cartDto.getId();
//    this.quantity=cartDto.getQuantity();
//
//    }
//}
//
//
//
//
//
//
