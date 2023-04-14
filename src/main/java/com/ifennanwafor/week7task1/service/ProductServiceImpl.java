package com.ifennanwafor.week7task1.service;

import com.ifennanwafor.week7task1.Exception.ProductNotFoundException;
import com.ifennanwafor.week7task1.dto.ProductDto;
import com.ifennanwafor.week7task1.models.Product;
import com.ifennanwafor.week7task1.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return modelMapper.map(product, ProductDto.class);
        } else {
            String message = String.format("Product with ID: %d, not found", id);
            throw new ProductNotFoundException(message);
        }
    }

    @Override
    public ProductDto saveAndFlush(ProductDto productDto) {
        BigDecimal price = new BigDecimal(productDto.getPrice());

        // Create a new Product object with the converted price
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(price);
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setImageURL(productDto.getImageURL());
        System.out.println("Product i want to save" + product);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public boolean deleteByID(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}