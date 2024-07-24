package com.example.backendpsw.controllers;

import jakarta.validation.Valid;
import com.example.backendpsw.entities.Cart;
import com.example.backendpsw.entities.Customer;
import com.example.backendpsw.entities.Product;
import com.example.backendpsw.exceptions.*;
import com.example.backendpsw.repositories.CustomerRepository;
import com.example.backendpsw.security.Utils;
import com.example.backendpsw.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/purchases")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRepository customerRepository;




    @PostMapping("/add/in")
    @PreAuthorize("hasRole('User')" )
    public ResponseEntity<Cart> addIn(@RequestBody List<Product> prodotti) throws IllegalQuantityException, PriceChangedException {
        Customer utente=customerRepository.findByUsernameIgnoreCase(Utils.getUsername());
        return new ResponseEntity(cartService.setProductsInCarrello(prodotti, utente),HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Cart> getCart (@RequestParam int utenteId) throws UserNotFoundException {
        return cartService.getCarrello(utenteId);
    }

    @GetMapping("/all/{user}")
    public List<Cart> getPurchases(@Valid @PathVariable("user") String user) {
        try {
            return cartService.getCarrello(customerRepository.findByUsernameIgnoreCase(user).getCustId());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!", e);
        }
    }
    @GetMapping("/all/products")
    public List<Product> getCartProducts(@RequestParam int cartId, @RequestParam int utenteId) throws UserNotFoundException {
        return cartService.getCarrelloProducts(cartId, utenteId);

    }

    @Transactional(readOnly = false)
    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestBody List<Product> prodotti, @RequestParam int carrelloId) throws IllegalQuantityException, PriceChangedException {
        return new ResponseEntity(cartService.updateCarrello(carrelloId, prodotti),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Cart> deleteProdFromCart(@RequestParam int carrelloId, @RequestParam int prodId, @RequestParam int quantity) throws IllegalQuantityException, PriceChangedException, NoProductException {
        return new ResponseEntity(cartService.removeProdCart(carrelloId, prodId, quantity),HttpStatus.OK);
    }
}
