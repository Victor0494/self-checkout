package com.checkout.mobile.infra.controller;

import com.checkout.mobile.application.usecases.CartManagement;
import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.valueObject.Item;
import com.checkout.mobile.infra.dto.CartDTO;
import com.checkout.mobile.infra.dto.ItemDto;
import com.checkout.mobile.infra.dto.PixResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartManagement cartManagement;
    private final ObjectMapper mapper;

    public CartController(CartManagement cartManagement, ObjectMapper mapper) {
        this.cartManagement = cartManagement;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto) {
        cartManagement.addItemToCart(itemDto.productId(), itemDto.name(), itemDto.quantity(), itemDto.pricePerUnity(), itemDto.imgPath());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> removeItemById(@PathVariable ("itemId") String itemId) {
        cartManagement.removeItem(itemId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/item")
    public ResponseEntity<?> clearCart() {
        cartManagement.clearCart();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<?> getCartById() {
        Cart response = cartManagement.getUserCartInfo();
        CartDTO cartDTO = mapper.convertValue(response, CartDTO.class);
        return ObjectUtils.isEmpty(response.getId()) ? ResponseEntity.noContent().build() : ResponseEntity.ok(cartDTO);
    }

    @GetMapping("/checkout")
    public ResponseEntity<PixResponseDTO> checkout() {
        String response = cartManagement.checkout();
        return ResponseEntity.ok(new PixResponseDTO(response));
    }

}
