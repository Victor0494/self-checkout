package com.checkout.mobile.infra.service;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import com.checkout.mobile.infra.config.CertificateConfig;
import com.checkout.mobile.infra.dto.PixConfig;
import com.checkout.mobile.infra.dto.PixRequestPayload;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PixService {

    private final JSONObject configuracoes;

    public PixService(final PixConfig pixConfig, final CertificateConfig CertificateConfig) throws IOException {
//        String resolvedCertPath = resolveCertificatePath(pixConfig.certificatePath());

        this.configuracoes = new JSONObject();
        this.configuracoes.put("client_id", pixConfig.clientId());
        this.configuracoes.put("client_secret", pixConfig.clientSecret());
        this.configuracoes.put("certificate", CertificateConfig.certificatePath());
        this.configuracoes.put("sandbox", pixConfig.sandbox());
        this.configuracoes.put("debug", pixConfig.debug());
    }

    public JSONObject listChavesPix(){
        return executarOperacao("pixListEvp", new HashMap<>());
    }

    public String createQrCode(PixRequestPayload pixRequestPayload) {

        JSONObject body = setHeaders(pixRequestPayload);

        try {
            EfiPay efi = new EfiPay(configuracoes);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<>(), body);
            return response.get("location").toString().split("/")[2];
        } catch (EfiPayException e) {
            log.error("Erro da API {} {}", e.getErrorDescription(), e.getError());
        } catch (Exception e) {
            log.error("Erro genérico {}", e.getMessage());
        }
        return null;
    }

    private static JSONObject setHeaders(PixRequestPayload pixRequestPayload) {
        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "02588786065").put("nome", pixRequestPayload.name()));
        body.put("valor", new JSONObject().put("original", pixRequestPayload.valor()));
        body.put("chave", pixRequestPayload.chave());
        return body;
    }

    private JSONObject executarOperacao(String operacao, Map<String, String> params) {
        final var retorno = new JSONObject();
        try {
            EfiPay efi = new EfiPay(configuracoes);
            JSONObject response = efi.call(operacao, params, new JSONObject());
            log.info("Resultado: {}", response);
            return response;
        } catch (EfiPayException e) {
            log.error(e.getError());
            retorno.put("erro", e.getErrorDescription());
        } catch (UnsupportedOperationException | JSONException operationException) {
            log.warn("Invalid JSON format {}", operationException.getMessage());
        } catch (Exception e) {
            retorno.put("erro", "Não foi possível completar a operação!");
        }
        return retorno;
    }

    private String resolveCertificatePath(String certificatePath) {
        try {
            var resource = getClass().getClassLoader().getResource(certificatePath);
            if (resource != null) {
                Path path = Paths.get(resource.toURI());
                if (Files.exists(path)) {
                    return path.toAbsolutePath().toString();
                }
            }
        } catch (URISyntaxException ignored) {}

        Path localPath = Paths.get(certificatePath).toAbsolutePath();
        if (Files.exists(localPath)) {
            return localPath.toString();
        }

        throw new RuntimeException("Certificado não encontrado: " + certificatePath);
    }
}

