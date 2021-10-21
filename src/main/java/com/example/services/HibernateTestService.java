package com.example.services;

import com.example.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class HibernateTestService {

    @Inject
    EntityManager em;

    @Transactional
    public void createProduct(String productName) {
      var product = new Product();
      product.setName(productName);
      em.persist(product);
    }
}
