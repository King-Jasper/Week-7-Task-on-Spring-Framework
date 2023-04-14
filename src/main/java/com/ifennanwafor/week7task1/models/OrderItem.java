//package com.ifennanwafor.week7task1.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.math.BigDecimal;
//
//@Data
//@Entity
//@Table(name = "order_items")
////@Getter
////@Setter
//@NoArgsConstructor
//
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @ManyToOne(optional = false)
//    private Order order;
//    @ManyToOne(optional = false)
//    private Product product;
//    @Column(nullable = false)
//    private Integer quantity;
//    @Column(nullable = false)
//    private BigDecimal unitPrice;
//}
