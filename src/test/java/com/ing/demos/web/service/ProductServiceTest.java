package com.ing.demos.web.service;

import com.ing.demos.persistence.model.ProductEntity;
import com.ing.demos.persistence.repository.ProductRepository;
import com.ing.demos.web.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void whenGetProductById_thenNotFound() {
        ProductEntity product = ProductEntity.builder().id(1L).build();

        assertThrows(EntityNotFoundException.class, () -> productService.findProductById(product.getId()));
        verify(productRepository, times(1)).findById(any());
    }

    @Test
    void whenGetCreatedProductById_thenDelete() {
        ProductEntity product = ProductEntity.builder().id(1L).build();
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        productService.saveProduct(product);
        verify(productRepository, times(1)).save(any());

        product = productService.findProductById(product.getId());
        verify(productRepository, times(1)).findById(any());
        assertNotNull(product);

        productService.deleteProduct(product.getId());
        verify(productRepository, times(1)).deleteById(any());
    }

}
