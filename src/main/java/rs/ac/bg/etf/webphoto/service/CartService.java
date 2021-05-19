package rs.ac.bg.etf.webphoto.service;

import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    List<CartItemDto> findCart(HttpSession session);

    void addToCart(CartItemDto cartItemRequest, HttpSession session);

    void deleteItem(Long photoDetailsId, HttpSession session);

    void clearCart(HttpSession session);

}
