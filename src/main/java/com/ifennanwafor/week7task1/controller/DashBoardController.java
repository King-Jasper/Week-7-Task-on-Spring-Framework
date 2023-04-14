package com.ifennanwafor.week7task1.controller;

import com.cloudinary.Cloudinary;
import com.ifennanwafor.week7task1.Exception.ProductNotFoundException;
import com.ifennanwafor.week7task1.dto.ProductDto;
import com.ifennanwafor.week7task1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class DashBoardController {

    private final IProductService productService;
    private final Cloudinary cloudinary;

    @Autowired
    public DashBoardController(IProductService productService, Cloudinary cloudinary) {
        this.productService = productService;
        this.cloudinary = cloudinary;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("successMessage", model.asMap().get("successMessage"));
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("productList", productService.findAllProducts());
        return "dashboard";
    }

    @PostMapping("/save-product")
    public String addProduct(@ModelAttribute("product") ProductDto productDto,
                             @RequestParam("productImage") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) throws IOException {
        String publicID = cloudinary.uploader()
                .upload(imageFile.getBytes(),
                        Map.of("public_id", UUID.randomUUID().toString()))
                .get("url").toString();
        productDto.setImageURL(publicID);
        productService.saveAndFlush(productDto);

        // add success message
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully");
        redirectAttributes.addFlashAttribute("products", productService.findAllProducts());
        return "redirect:/dashboard";
    }
    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        try {
            ProductDto productDto = productService.findProductById(id);
            model.addAttribute("productDto", productDto);
            return "edit";
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteByID(id);
        return "redirect:/dashboard";
    }

}
