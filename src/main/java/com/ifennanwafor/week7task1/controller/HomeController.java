package com.ifennanwafor.week7task1.controller;

import com.ifennanwafor.week7task1.dto.ProductDto;
import com.ifennanwafor.week7task1.service.IProductService;
import com.ifennanwafor.week7task1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    private final IUserService userService;
    private final IProductService productService;
    @Autowired
    public HomeController(IUserService userService, IProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping(value = "/index")
    public ModelAndView displayLoginForm(ModelAndView modelAndView) {
        List<ProductDto> products = productService.findAllProducts();
        modelAndView.setViewName("index");
        modelAndView.addObject(products);
        return modelAndView;
    }
}
