package com.checkout.mobile.infra.controller;

import com.checkout.mobile.application.usecases.ClientManagement;
import com.checkout.mobile.domain.entities.Client;
import com.checkout.mobile.infra.dto.ClientRequestDTO;
import com.checkout.mobile.infra.dto.ClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private final ClientManagement clientManagement;

    public ClientController(ClientManagement clientManagement) {
        this.clientManagement = clientManagement;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDto) {
        Client response = clientManagement
                .createClient(new Client(clientRequestDto.name(),
                        clientRequestDto.document(),
                        clientRequestDto.password()));
        URI location = URI.create("/client/" + response.getId());

        return ResponseEntity.created(location)
                .body(new ClientResponseDTO(response.getId(), response.getName()));

    }
}
