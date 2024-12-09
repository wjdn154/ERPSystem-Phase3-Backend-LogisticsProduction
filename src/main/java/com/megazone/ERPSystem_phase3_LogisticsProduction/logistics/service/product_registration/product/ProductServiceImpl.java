package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.product_registration.product;

import com.megazone.ERPSystem_phase3_LogisticsProduction.common.config.redis.RedisCacheable;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.client.Client;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.repository.basic_information_management.client.ClientRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.ProductGroup;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductRequestDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.dto.ProductResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.product_registration.product.ProductRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.product_registration.product_group.ProductGroupRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.model.basic_data.process_routing.ProcessRouting;
import com.megazone.ERPSystem_phase3_LogisticsProduction.production.repository.basic_data.process_routing.PrcessRouting.ProcessRoutingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProcessRoutingRepository processRoutingRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ClientRepository clientRepository;
    private final ProductImageService productImageService;

    // 이미지가 저장된 경로 (src/main/resources/static/uploads/)
    private static final String UPLOAD_DIR = "src/main/resources/static";

    /**
     * 품목등록 리스트 조회
     * @return 등록된 모든 품목을 반환
     */
    @RedisCacheable(cacheName = "products")
    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 특정 id 값을 가진 품목의 상세 정보 조회하기
     *
     * @param id
     * @return id가 일치한 품목의 상세 정보 dto 반환
     */
    @Override
    public Optional<ProductResponseDto> findProductDetailById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("해당 품목을 찾을 수 없습니다."));

        return Optional.of(toDto(product));
    }

    /**
     * 새로운 품목 등록하기
     *
     * @param productRequestDto 저장할 품목의 정보가 담긴 DTO
     * @param imageFile
     * @return 저장된 품목 정보를 담은 DTO를 Optional로 반환함.
     */
    @Override
    public Optional<ProductResponseDto> saveProduct(ProductRequestDto productRequestDto, MultipartFile imageFile) {

        // 코드 중복 검사
        validateProductCodeUnique(productRequestDto.getCode());

        // 거래처, 품목 그룹, 생산 라우팅 정보 조회
        Client client = clientRepository.findById(productRequestDto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("해당 거래처를 찾을 수 없습니다."));
        ProductGroup productGroup = productGroupRepository.findById(productRequestDto.getProductGroupId())
                .orElseThrow(() -> new IllegalArgumentException("해당 품목 그룹을 찾을 수 없습니다."));

        ProcessRouting processRouting = null;
        if(productRequestDto.getProcessRoutingId() != null) {
            processRouting = processRoutingRepository.findById(productRequestDto.getProcessRoutingId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 생산 라우팅을 찾을 수 없습니다."));
        }

        // 이미지 파일 업로드 및 경로 가져오기
        String imagePath = null;
        if (imageFile != null) {
            imagePath = productImageService.uploadProductImage(imageFile);
            // 파일 저장 로직 추가
        }

        // 엔티티로 변환 후 저장
        Product product = toEntity(productRequestDto, client ,productGroup, processRouting, imagePath);

        Product savedProduct = productRepository.save(product);


        // 다시 DTO로 변환 후 반환
        return Optional.of(toDto(savedProduct));
    }

    /**
     * 등록된 품목 수정하기
     *
     * @param id
     * @param productRequestDto
     * @param imageFile
     * @return 수정된 품목의 DTO를 반환
     */
    @Override
    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto productRequestDto, MultipartFile imageFile) {

        // 품목 조회 및 검증
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 품목을 찾을 수 없습니다."));

        // 코드 중복 검사, 업데이트 할 품목은 제외하고 검사
        if (productRepository.existsByCodeAndIdNot(productRequestDto.getCode(), id)) {
            throw new IllegalArgumentException("동일한 코드를 가진 품목이 이미 존재합니다.");
        }

        // 거래처, 품목 그룹, 생산 라우팅 조회
        Client client = clientRepository.findById(productRequestDto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("해당 거래처를 찾을 수 없습니다."));
        ProductGroup productGroup = productGroupRepository.findById(productRequestDto.getProductGroupId())
                .orElseThrow(() -> new IllegalArgumentException("해당 품목 그룹을 찾을 수 없습니다."));
        ProcessRouting processRouting = null;
        if(productRequestDto.getProcessRoutingId() != null) {
            processRouting = processRoutingRepository.findById(productRequestDto.getProcessRoutingId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 생산 라우팅을 찾을 수 없습니다."));
        }

        // 이미지가 전송된 경우 기존 이미지 삭제 후 새 이미지 저장
        if (imageFile != null && !imageFile.isEmpty()) {
            // 기존 이미지 경로 삭제 로직
            String oldImagePath = product.getImagePath();
            if (oldImagePath != null) {
                deleteOldImage(oldImagePath);  // 기존 이미지 삭제
            }

            // 새로운 이미지 업로드 및 경로 설정
            String newImagePath = productImageService.uploadProductImage(imageFile);
            product.setImagePath(newImagePath);
        }

        // 나머지 필드 업데이트
        product.setClient(client);
        product.setProductGroup(productGroup);
        product.setProcessRouting(processRouting);
        updateProductFields(product, productRequestDto);

        // 저장
        Product updatedProduct = productRepository.save(product);
        
        return Optional.of(toDto(updatedProduct));
    }

    private void deleteOldImage(String oldImagePath) {
        try {
            // 이미지 경로를 완전한 파일 시스템 경로로 변환
            File file = new File(UPLOAD_DIR + oldImagePath);

            if (file.exists()) {
                Files.delete(Paths.get(file.getPath()));  // 파일 삭제
            }
        } catch (IOException e) {
            // 삭제 실패 시 예외 처리
            throw new RuntimeException("이미지 파일 삭제 실패", e);
        }
    }

    /**
     * 수정 필요
     * 품목 삭제
     *
     * @param id
     * @return 삭제 완료 유무 문자열 반환
     */
    @Override
    public String deleteProduct(Long id) {

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("삭제 실패: 해당 품목을 찾을 수 없습니다."));

            productRepository.delete(product);
            return product.getName() + " 품목이 삭제되었습니다.";

        } catch (DataIntegrityViolationException e) {
            // 외래 키 제약 조건으로 인한 삭제 실패 처리
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                Throwable rootCause = e.getRootCause();
                if (rootCause instanceof java.sql.SQLIntegrityConstraintViolationException) {
                    return "삭제 실패: 다른 곳에서 해당 품목을 참조하고 있어 삭제할 수 없습니다.";
                }
            }
            return "삭제 실패: 이 품목은 다른 데이터와 연결되어 있어 삭제할 수 없습니다.";

        } catch (IllegalArgumentException e) {
            return e.getMessage();

        } catch (RuntimeException e) {
            return "삭제 중 오류가 발생했습니다.";
        }

    }

    /**
     * 주어진 ID를 기준으로 품목을 사용중단.
     *
     * @param id 사용중단할 품목의 ID.
     * @return 품목의 사용중단 상태를 나타내는 메시지.
     * @throws IllegalArgumentException 주어진 ID로 품목을 찾을 수 없는 경우.
     */
    @Override
    public String deactivateProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 품목을 찾을 수 없습니다."));

        product.deactivate();
        productRepository.save(product);

        return product.getName() + " 품목이 사용 중단되었습니다.";
    }

    /**
     * 주어진 ID를 기준으로 품목을 재사용.
     *
     * @param id 재사용할 품목의 ID.
     * @return 품목의 재사용 상태를 나타내는 메시지.
     * @throws IllegalArgumentException 주어진 ID로 품목을 찾을 수 없는 경우.
     */
    @Override
    public String reactivateProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 회사에 해당 품목을 찾을 수 없습니다."));

        product.reactivate();
        productRepository.save(product);

        return product.getName() + " 품목을 재사용합니다.";
    }


    // 주어진 코드에 해당하는 상품이 이미 존재하는지 확인하는 메서드
    private void validateProductCodeUnique(String code) {
        if (productRepository.existsByCode(code)) {
            throw new IllegalArgumentException("해당 코드로 등록된 품목이 이미 존재합니다.");
        }
    }

    // 나머지 필드 업데이트하는 메서드
    private void updateProductFields(Product product, ProductRequestDto productRequestDto) {
        product.setCode(productRequestDto.getCode());
        product.setName(productRequestDto.getName());
        product.setStandard(productRequestDto.getStandard());
        product.setUnit(productRequestDto.getUnit());
        product.setPurchasePrice(productRequestDto.getPurchasePrice());
        product.setSalesPrice(productRequestDto.getSalesPrice());
        product.setProductType(productRequestDto.getProductType());
    }

    // Entity -> DTO 변환 메서드
    private ProductResponseDto toDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .clientId(product.getClient() != null ? product.getClient().getId() : null)
                .clientName(product.getClient() != null ? product.getClient().getPrintClientName() : null)
                .productGroupId(product.getProductGroup()  != null ? product.getProductGroup().getId() : null)
                .productGroupCode(product.getProductGroup() != null ? product.getProductGroup().getCode() : null)
                .productGroupName(product.getProductGroup() != null ? product.getProductGroup().getName() : null)
                .standard(product.getStandard())
                .unit(product.getUnit())
                .purchasePrice(product.getPurchasePrice())
                .salesPrice(product.getSalesPrice())
                .productType(product.getProductType())
                .processRoutingId(product.getProcessRouting() != null ? product.getProcessRouting().getId() : null)
                .processRoutingCode(product.getProcessRouting() != null ? product.getProcessRouting().getCode() : null)
                .processRoutingName(product.getProcessRouting() != null ? product.getProcessRouting().getName() : null)
                .imagePath(product.getImagePath())
                .remarks(product.getRemarks())
                .isActive(product.isActive())
                .build();
    }

    // DTO -> Entity 변환 메서드
    public Product toEntity(ProductRequestDto productRequestDto, Client client, ProductGroup productGroup, ProcessRouting processRouting, String imagePath) {
        return Product.builder()
                .code(productRequestDto.getCode())
                .name(productRequestDto.getName())
                .client(client)
                .productGroup(productGroup)
                .processRouting(processRouting)
                .standard(productRequestDto.getStandard())
                .unit(productRequestDto.getUnit())
                .purchasePrice(productRequestDto.getPurchasePrice())
                .salesPrice(productRequestDto.getSalesPrice())
                .productType(productRequestDto.getProductType())
                .imagePath(imagePath)
                .remarks(productRequestDto.getRemarks())
                .build();
    }

}
