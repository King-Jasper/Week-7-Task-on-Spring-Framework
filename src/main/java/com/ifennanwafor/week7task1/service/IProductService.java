package com.ifennanwafor.week7task1.service;

import com.ifennanwafor.week7task1.Exception.ProductNotFoundException;
import com.ifennanwafor.week7task1.dto.ProductDto;
import com.ifennanwafor.week7task1.models.Product;

import java.util.List;

public interface IProductService {
    List<ProductDto> findAllProducts();
    ProductDto findProductById(Long id) throws ProductNotFoundException, ProductNotFoundException;
    ProductDto saveAndFlush(ProductDto productDto);
    boolean deleteByID(Long id);
}
