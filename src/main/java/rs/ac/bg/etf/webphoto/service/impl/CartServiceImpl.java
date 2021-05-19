package rs.ac.bg.etf.webphoto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;
import rs.ac.bg.etf.webphoto.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private static final String SESSION_KEY = "cart";

    @Override
    public List<CartItemDto> findCart(HttpSession session) {
        return getItems(session);
    }

    @Override
    public void addToCart(CartItemDto cartItem, HttpSession session) {
        List<CartItemDto> items = getItems(session);
        items.add(cartItem);
        setItems(items, session);
    }

    @Override
    public void deleteItem(Long photoDetailsId, HttpSession session) {
        Assert.notNull(photoDetailsId, "Details id can't be null");
        List<CartItemDto> items = getItems(session);
        items.removeIf(item -> photoDetailsId.equals(item.getPhotoDetailsId()));
        setItems(items, session);
    }

    @Override
    public void clearCart(HttpSession session) {
        setItems(new ArrayList<>(), session);
    }

    private List<CartItemDto> getItems(HttpSession session) {
        return Optional.ofNullable(session.getAttribute(SESSION_KEY))
                .map(value -> (List<CartItemDto>) value)
                .orElse(new ArrayList<>());
    }

    private void setItems(List<CartItemDto> items, HttpSession session) {
        session.setAttribute(SESSION_KEY, items);
    }


}
