//package com.checkout.mobile.infra.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Base64;
//
//@Configuration
//public class CertificateConfig {
//
//    @Value("${CERTIFICATE_BASE64}")
//    private String certificateBase64;
//
//    @Bean
//    public String certificatePath() throws IOException {
//        byte[] decoded = Base64.getDecoder().decode(certificateBase64);
//        File file = File.createTempFile("pix-cert", ".p12");
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(decoded);
//        }
//        return file.getAbsolutePath();
//    }
//}
