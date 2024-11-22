package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.product_registration.product;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImageService {

    String uploadProductImage(MultipartFile image);

    String saveImageToS3(MultipartFile image) throws IOException;
}
