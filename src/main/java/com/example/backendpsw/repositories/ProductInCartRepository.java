package com.example.backendpsw.repositories;

import com.example.backendpsw.entities.Cart;
import com.example.backendpsw.entities.Product;
import com.example.backendpsw.entities.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Integer> {

    List<ProductInCart> findByProduct(Product prodotto);
    boolean existsByProduct(Product prodotto);
    boolean existsByCartAndProduct(Cart carrello, Product prodotto);
    ProductInCart findByCartAndProduct(Cart carrello, Product prodotto);

}
