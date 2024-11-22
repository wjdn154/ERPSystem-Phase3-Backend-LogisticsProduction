package com.megazone.ERPSystem_phase3_LogisticsProduction.production.service.resource_data.equipment;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EquipmentDataImageService {

    String uploadEquipmentDataImage(MultipartFile image);

    String saveImageToS3(MultipartFile image) throws IOException;
}
