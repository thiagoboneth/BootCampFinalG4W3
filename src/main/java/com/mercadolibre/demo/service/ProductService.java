package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.ProductDTO;
import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product save(ProductDTO dto) {
        Product product = convertProductToDTO(dto);
        return productRepository.save(product);
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product update(ProductDTO dto, Long id) throws Exception {
        Optional<Product> existProduct = findById(id);
        if (existProduct.isPresent()) {
            Product product = convertProductToDTO(dto);
            product.setId(id);
            return productRepository.saveAndFlush(product);
        } else {
            throw new Exception("Id não cadastrado");
        }
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product convertProductToDTO(ProductDTO dto) {
        return new Product(dto.getName(), dto.getDescription());
    }
}
