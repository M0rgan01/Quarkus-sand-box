package com.example.services;

import com.example.entity.Product;
import com.example.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    EntityManager em;

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void createProduct(String productName) {
      var product = new Product();
      product.setName(productName);
      em.persist(product);
    }

    @Transactional
    public Product createProductWithPanache(Product product) {
        Product.persist(product);
        return product;
    }

    @Transactional
    public List<Product> getProductsWithPanache() {
        return Product.listAll();
    }

    @Transactional
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
}
