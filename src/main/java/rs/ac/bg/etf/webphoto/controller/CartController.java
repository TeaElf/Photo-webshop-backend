package rs.ac.bg.etf.webphoto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;
import rs.ac.bg.etf.webphoto.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItemDto> findCart(HttpSession session) {
        return cartService.findCart(session);
    }

    @PostMapping
    public void addToCart(@Validated @RequestBody CartItemDto cartItem, HttpSession session) {
        cartService.addToCart(cartItem, session);
    }

    @DeleteMapping("/{photoDetailsId}")
    public void deleteItem(@PathVariable Long photoDetailsId, HttpSession session) {
        cartService.deleteItem(photoDetailsId, session);
    }

    @DeleteMapping
    public void clearCart(HttpSession session) {
        cartService.clearCart(session);
    }
}
