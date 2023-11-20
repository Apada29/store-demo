package com.ing.demos.web.controller;

import com.ing.demos.persistence.model.ProductEntity;
import com.ing.demos.web.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<ProductEntity> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductEntity getProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @PostMapping(produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseBody
    public ProductEntity saveProduct(@RequestBody ProductEntity product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}