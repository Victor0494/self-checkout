package com.checkout.mobile.infra.controller;

import com.checkout.mobile.infra.dto.PixRequestPayload;
import com.checkout.mobile.infra.dto.PixResponseDTO;
import com.checkout.mobile.infra.service.PixService;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/pix", produces = MediaType.APPLICATION_JSON_VALUE)
public class PixController {

    private final PixService pixService;

    public PixController(PixService pixService) {
        this.pixService = pixService;
    }

    @PostMapping("/qrcode")
    public ResponseEntity<PixResponseDTO> criarQrCode(@RequestBody PixRequestPayload pixRequestPayload){
        var response = this.pixService.createQrCode(pixRequestPayload);
        return ResponseEntity.ok().body(new PixResponseDTO(response));
    }

    @GetMapping("/listar")
    public ResponseEntity<String> listarChavesPix(){
        var response = this.pixService.listChavesPix();
        return ResponseEntity.ok().body(response.toString());
    }

}
