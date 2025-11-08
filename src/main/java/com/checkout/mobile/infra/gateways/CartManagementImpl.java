package com.checkout.mobile.infra.gateways;

import com.checkout.mobile.application.gateways.CartGateway;
import com.checkout.mobile.domain.entities.Cart;
import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.domain.valueObject.CartStatus;
import com.checkout.mobile.domain.valueObject.Item;
import com.checkout.mobile.infra.dto.PixRequestPayload;
import com.checkout.mobile.infra.mapper.CartEntityMapper;
import com.checkout.mobile.infra.mapper.ClientCartMapper;
import com.checkout.mobile.infra.mapper.ItemEntityMapper;
import com.checkout.mobile.infra.persistence.client.CartEntity;
import com.checkout.mobile.infra.persistence.client.ClientEntity;
import com.checkout.mobile.infra.persistence.client.ClientRepository;
import com.checkout.mobile.infra.persistence.client.ItemEntity;
import com.checkout.mobile.infra.security.AuthenticateUserProvider;
import com.checkout.mobile.infra.service.PixService;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartManagementImpl implements CartGateway {

    private static final String URL_PAYMENT = "https://pix.sejaefi.com.br/cob/pagar/";

    private final AuthenticateUserProvider authenticateUserProvider;
    private final ClientRepository clientRepository;
    private final ClientCartMapper clientCartMapper;
    private final ItemEntityMapper itemEntityMapper;
    private final PixService pixService;


    public CartManagementImpl(AuthenticateUserProvider authenticateUserProvider, ClientRepository clientRepository, ClientCartMapper clientCartMapper, ItemEntityMapper itemEntityMapper, PixService pixService) {
        this.authenticateUserProvider = authenticateUserProvider;
        this.clientRepository = clientRepository;
        this.clientCartMapper = clientCartMapper;
        this.itemEntityMapper = itemEntityMapper;
        this.pixService = pixService;
    }

    @Override
    public void addItem(String productId, String name, Integer quantity, Float price, String imgPath) {
        ClientEntity clientEntity = getClientEntity();
        Item item = new Item(productId, name, quantity, price, imgPath);

        Client clientDomain = clientCartMapper.toDomain(clientEntity);
        Optional<Cart> cartDomain = clientDomain.getCart().stream()
                .filter(cart -> CartStatus.OPEN.equals(cart.getStatus())).findFirst();

        if(cartDomain.isEmpty()) {
            Cart cart = new Cart("", new ArrayList<>(), 0.0f, CartStatus.OPEN, clientDomain);
            clientDomain.addCart(cart);
            cartDomain = Optional.of(cart);
        }

        cartDomain.get().addItem(item);
        item.setCart(cartDomain.get());

        ClientEntity updatedEntity = clientCartMapper.toEntity(clientDomain);
        clientRepository.save(updatedEntity);
    }

    @Override
    public void removerItem(String productId) {
        ClientEntity clientEntity = getClientEntity();

        CartEntity cartEntity = clientEntity.getCart().stream()
                .filter(cart -> CartStatus.OPEN.equals(cart.getStatus())).findFirst().orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cartEntity.getItems().removeIf(item -> item.getProductId().equals(productId));

        clientRepository.save(clientEntity);
    }

    @Override
    public void cleanCart() {
        ClientEntity clientEntity = getClientEntity();

        CartEntity cartEntity = clientEntity.getCart().stream()
                .filter(cart -> CartStatus.OPEN.equals(cart.getStatus())).findFirst().orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        cartEntity.getItems().removeAll(cartEntity.getItems());

        clientRepository.save(clientEntity);
    }


    @Override
    public Cart getUserCartInfo() {
        ClientEntity clientEntity = getClientEntity();

        Optional<CartEntity> cartEntity = clientEntity.getCart().stream()
                .filter(cart -> CartStatus.OPEN.equals(cart.getStatus()))
                .max(Comparator.comparing(CartEntity::getDateTime))
                .or(() ->clientEntity.getCart().stream()
                        .filter(cart -> CartStatus.WAITING_PAYMENT.equals(cart.getStatus()) && Objects.nonNull(cart.getDateTime()))
                        .max(Comparator.comparing(CartEntity::getDateTime)));

        if(cartEntity.isEmpty()) {
            return new Cart();
        }

        Cart response = new Cart();
        response.setId(cartEntity.get().getId());
        response.setItems((cartEntity.get().getItems().stream().map(itemEntityMapper::toDomainWithoutCart)).toList());
        response.setStatus(cartEntity.get().getStatus());
        response.setTotalValue(cartEntity.get().getTotalValue());

        return response;
    }

    @Override
    public String checkout() {
        ClientEntity clientEntity = getClientEntity();

        Optional<CartEntity> cartEntity = clientEntity.getCart().stream()
                .filter(cart -> CartStatus.OPEN.equals(cart.getStatus())).findFirst();

        if(cartEntity.isEmpty()) {
            throw new EntityNotFoundException("Cart not found");
        }

        cartEntity.get().setStatus(CartStatus.WAITING_PAYMENT);
        cartEntity.get().setDateTime(LocalDateTime.now());
        clientRepository.save(clientEntity);

        PixRequestPayload pixRequestPayload =
                new PixRequestPayload("1272ea48-32ed-46c7-8851-987f7b216a24", "0.01", clientEntity.getDocument(), clientEntity.getName());

        String qrCode = pixService.createQrCode(pixRequestPayload);

        return URL_PAYMENT + qrCode;
    }

    private ClientEntity getClientEntity() {
        String userId = authenticateUserProvider.getUserId();

        Optional<ClientEntity> optionalClientEntity = clientRepository.findById(userId);

        if (optionalClientEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return optionalClientEntity.get();
    }

}
