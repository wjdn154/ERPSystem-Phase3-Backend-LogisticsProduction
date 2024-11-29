package com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.service.purchase_management.purchase_order;

import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.dto.RecentActivityEntryDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.dashboard.enums.ActivityType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.dto.UserNotificationCreateAndSendDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.ModuleType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.NotificationType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.model.notification.enums.PermissionType;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service.IntegratedService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.Integrated.service.NotificationService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.client.Client;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.model.basic_information_management.voucher_entry.sales_and_purchase_voucher_entry.enums.ElectronicTaxInvoiceStatus;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.repository.basic_information_management.client.ClientRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.VatTypeService;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatAmountWithSupplyAmountDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.financial.vat_type.dto.VatTypeShowDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.model.basic_information_management.employee.Employee;
import com.megazone.ERPSystem_phase3_LogisticsProduction.hr.repository.basic_information_management.Employee.EmployeeRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.product_registration.Product;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseOrder;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.PurchaseOrderDetail;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderCreateDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDetailDto.PurchaseOrderItemDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDetailDto.PurchaseOrderItemDetailDto.ClientDetailDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.PurchaseOrderResponseDto;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.purchase_management.dto.SearchDTO;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.model.warehouse_management.warehouse.Warehouse;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.basic_information_management.warehouse.WarehouseRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.product_registration.product.ProductRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.CurrencyRepository;
import com.megazone.ERPSystem_phase3_LogisticsProduction.logistics.repository.purchase_management.purchase_order.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final WarehouseRepository warehouseRepository;
    private final CurrencyRepository currencyRepository;
    private final ProductRepository productRepository;
    private final VatTypeService vatTypeService;
    private final IntegratedService integratedService;
    private final NotificationService notificationService;
    /**
     * 발주서 목록 조회
     * @return
     */
    @Override
    public List<PurchaseOrderResponseDto> findAllPurchaseOrders(SearchDTO dto) {

        List<PurchaseOrder> purchaseOrders;

        // dto가 null이거나 조건이 모두 null일 경우 모든 발주서 조회
        if (dto == null || (dto.getStartDate() == null && dto.getEndDate() == null && dto.getClientId() == null && dto.getState() == null)) {
            purchaseOrders = purchaseOrderRepository.findAll(); // 전체 발주서 조회
        } else {
            // 조건이 있는 경우 QueryDSL을 사용하여 검색
            purchaseOrders = purchaseOrderRepository.findBySearch(dto);
        }

        List<Long> vatTypeIdList = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            vatTypeIdList.add(purchaseOrder.getVatId());
        }

        Map<Long,VatTypeShowDTO> vatTypeMap = vatTypeService.getVatTypeList(vatTypeIdList).stream()
                .collect(Collectors.toMap(VatTypeShowDTO::getVatTypeId, v -> v));

        // 발주서가 없는 경우 빈 리스트 반환
        return purchaseOrders.isEmpty()
                ? Collections.emptyList()
                : purchaseOrders.stream()
                .map((purchaseOrder) -> toListDto(purchaseOrder,vatTypeMap))
                .toList();
    }


    /** 발주서 목록 조회 관련 메서드 **/
    // Entity -> 발주서 목록 조회용 DTO 변환 메소드
    private PurchaseOrderResponseDto toListDto(PurchaseOrder purchaseOrder,Map<Long,VatTypeShowDTO> vatTypeMap) {
        return PurchaseOrderResponseDto.builder()
                .id(purchaseOrder.getId())
                .clientName(purchaseOrder.getClient().getPrintClientName())  // 첫 번째 품목의 거래처 이름
                .managerName(purchaseOrder.getManager().getLastName() + purchaseOrder.getManager().getFirstName())
                .productName(getProductNameWithCount(purchaseOrder))  // 첫 번째 품목 이름과 "외 몇건" 추가
                .date(purchaseOrder.getDate())
                .deliveryDate(purchaseOrder.getDeliveryDate())
                .totalQuantity(getTotalQuantity(purchaseOrder))  // 총 수량
                .totalPrice(getTotalPrice(purchaseOrder))  // 총 가격
                .vatName(vatTypeMap.get(purchaseOrder.getVatId()).getVatTypeName())
                .status(purchaseOrder.getStatus().toString())  // 진행 상태
                .build();
    }

    // 총 금액 계산
    private BigDecimal getTotalPrice(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getPurchaseOrderDetails().stream()
                .map(PurchaseOrderDetail::getSupplyPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 총 수량 계산
    private Integer getTotalQuantity(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getPurchaseOrderDetails().stream()
                .mapToInt(PurchaseOrderDetail::getQuantity)
                .sum();
    }

    // 첫 번째 품목 이름과 외 몇건 처리
    private String getProductNameWithCount(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderDetail> details = purchaseOrder.getPurchaseOrderDetails();

        if (details.isEmpty()) {
            return "품목 없음";  // 품목이 없을 경우 처리
        }

        String firstProductName = details.get(0).getProduct().getName();

        // 품목이 2개 이상일 때 "외 N건"을 붙임
        if (details.size() > 1) {
            return firstProductName + " 외 " + (details.size() - 1) + "건";
        } else {
            return firstProductName;  // 품목이 하나일 경우 그냥 첫 번째 품목 이름만 반환
        }
    }

    /**
     * 발주서 상세 정보 조회
     * @param id
     * @return
     */
    @Override
    public Optional<PurchaseOrderResponseDetailDto> findPurchaseOrderDetailById(Long id) {
        
        return purchaseOrderRepository.findById(id)
                .map(this::toDetailDto);
    }

    /** 발주서 상세 정보 조회 관련 메서드 **/
    // Entity -> 상세 조회용 DTO 변환 메소드
    private PurchaseOrderResponseDetailDto toDetailDto(PurchaseOrder purchaseOrder) {

        VatTypeShowDTO vatTypeShowDTO = vatTypeService.getVatType(purchaseOrder.getVatId());

        return PurchaseOrderResponseDetailDto.builder()
                .id(purchaseOrder.getId())
                .date(purchaseOrder.getDate())
                .deliveryDate(purchaseOrder.getDeliveryDate())
                .status(purchaseOrder.getStatus().toString())
                .clientId(purchaseOrder.getClient().getId())
                .clientName(purchaseOrder.getClient().getPrintClientName())
                .managerId(purchaseOrder.getManager().getId())
                .managerCode(purchaseOrder.getManager().getEmployeeNumber())
                .managerName(purchaseOrder.getManager().getLastName() + purchaseOrder.getManager().getFirstName())
                .warehouseId(purchaseOrder.getReceivingWarehouse().getId())
                .warehouseCode(purchaseOrder.getReceivingWarehouse().getCode())
                .warehouseName(purchaseOrder.getReceivingWarehouse().getName())
                .vatCode(vatTypeShowDTO.getVatTypeCode())
                .vatName(vatTypeShowDTO.getVatTypeName())
                .journalEntryCode(purchaseOrder.getJournalEntryCode())
                .electronicTaxInvoiceStatus(purchaseOrder.getElectronicTaxInvoiceStatus().toString())
                .currencyId(purchaseOrder.getCurrency().getId())
                .currency(purchaseOrder.getCurrency().getName())
                .exchangeRate(purchaseOrder.getCurrency().getExchangeRate())
                .remarks(purchaseOrder.getRemarks())
                .purchaseOrderDetails(toItemDetailDtoList(purchaseOrder.getPurchaseOrderDetails()))
                .build();
    }

    // PurchaseOrderDetail 리스트 -> PurchaseOrderItemDetailDto 리스트 변환 메서드
    private List<PurchaseOrderItemDetailDto> toItemDetailDtoList(List<PurchaseOrderDetail> details) {
        return details.stream()
                .map(this::toItemDetailDto)
                .collect(Collectors.toList());
    }

    // PurchaseOrderDetail 엔티티 -> PurchaseOrderItemDetailDto 변환 메서드
    private PurchaseOrderItemDetailDto toItemDetailDto(PurchaseOrderDetail detail) {
        Product product = detail.getProduct();
        return PurchaseOrderItemDetailDto.builder()
                .detailId(detail.getId())
                .productId(product.getId())
                .productName(product.getName())
                .productCode(product.getCode())
                .quantity(detail.getQuantity())
                .price(product.getPurchasePrice())
                .supplyPrice(detail.getSupplyPrice())
                .vat(detail.getVat())
                .remarks(detail.getRemarks())
                .client(toClientDto(product.getClient()))
                .build();
    }

    // Client 엔티티 -> ClientDetailDto 변환 메서드
    private ClientDetailDto toClientDto(Client client) {
        return ClientDetailDto.builder()
                .clientId(client.getId())
                .clientCode(client.getCode())
                .clientName(client.getPrintClientName())
                .build();
    }

    /**
     * 발주서 직접 등록
     * @param createDto
     * @return
     */
    @Override
    public PurchaseOrderResponseDetailDto createPurchaseOrder(PurchaseOrderCreateDto createDto) {
        try {
            PurchaseOrder purchaseOrder = toEntity(createDto);
            purchaseOrder = purchaseOrderRepository.save(purchaseOrder);


            integratedService.recentActivitySave(
                    RecentActivityEntryDTO.create(
                            "신규 발주서 등록 : " + purchaseOrder.getDate() + " -" + purchaseOrder.getId(),
                            ActivityType.LOGISTICS));

            notificationService.createAndSend(
                    UserNotificationCreateAndSendDTO.create(
                            ModuleType.LOGISTICS,
                            PermissionType.USER,
                            "신규 발주서 (" + purchaseOrder.getDate() + " -" + purchaseOrder.getId() + ") 가 등록되었습니다.",
                            NotificationType.NEW_ENTRY));
            return toDetailDto(purchaseOrder);
        } catch (Exception e) {
            log.error("발주서 생성 실패: ", e);
            return null;
        }
    }

    /** 발주서 직접 등록 관련 메서드 **/
    // 발주서 등록 DTO -> Entity 변환 메소드
    private PurchaseOrder toEntity(PurchaseOrderCreateDto dto) {
        PurchaseOrder newOrder = PurchaseOrder.builder()
                .client(clientRepository.findById(dto.getClientId())
                        .orElseThrow(() -> new RuntimeException("거래처 정보를 찾을 수 없습니다.")))
                .manager(employeeRepository.findById(dto.getManagerId())
                        .orElseThrow(() -> new RuntimeException("담당자 정보를 찾을 수 없습니다.")))
                .receivingWarehouse(warehouseRepository.findById(dto.getWarehouseId())
                        .orElseThrow(() -> new RuntimeException("창고 정보를 찾을 수 없습니다.")))
                .currency(currencyRepository.findById(dto.getCurrencyId())
                        .orElseThrow(() -> new RuntimeException("통화 정보를 찾을 수 없습니다.")))
                .date(dto.getDate())
                .deliveryDate(dto.getDeliveryDate())
                .vatId(dto.getVatId())
                .journalEntryCode(dto.getJournalEntryCode())
                .remarks(dto.getRemarks())
                .build();

        return getPurchaseOrder(dto, newOrder);
    }

    private PurchaseOrder getPurchaseOrder(PurchaseOrderCreateDto dto, PurchaseOrder newOrder) {

        dto.getItems().forEach(item -> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("품목 정보를 찾을 수 없습니다."));


            BigDecimal supplyPrice = BigDecimal.valueOf(item.getQuantity()).multiply(product.getSalesPrice());

            VatAmountWithSupplyAmountDTO vatAmountWithSupplyAmountDTO = new VatAmountWithSupplyAmountDTO();
            vatAmountWithSupplyAmountDTO.setSupplyAmount(supplyPrice);
            vatAmountWithSupplyAmountDTO.setVatTypeId(newOrder.getVatId());

            BigDecimal localAmount = null;
            BigDecimal vat = null;

            if (newOrder.getCurrency().getId() == 6) {
                vat = vatTypeService.vatAmountCalculate(vatAmountWithSupplyAmountDTO);
            } else if (newOrder.getCurrency().getExchangeRate() != null) {
                localAmount = supplyPrice.multiply(newOrder.getCurrency().getExchangeRate());
            } else {
                throw new RuntimeException("환율 정보가 없습니다.");
            }


            // 발주서 상세 항목 생성
            PurchaseOrderDetail detail = PurchaseOrderDetail.builder()
                    .product(product)
                    .quantity(item.getQuantity())
                    .supplyPrice(supplyPrice)
                    .localAmount(localAmount)
                    .vat(vat)
                    .remarks(item.getRemarks())
                    .build();

            newOrder.addPurchaseOrderDetail(detail);
        });
        return newOrder;
    }


    /**
     * 발주서 수정
     * @param id
     * @param updateDto
     * @return
     */
    @Override
    public PurchaseOrderResponseDetailDto updatePurchaseOrder(Long id, PurchaseOrderCreateDto updateDto) {
        try {

            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("해당 발주 요청 정보를 찾을 수 없습니다."));


            if(updateDto.getClientId() != null){
                Client client = clientRepository.findById(updateDto.getClientId())
                        .orElseThrow(() -> new RuntimeException("해당 거래처 정보를 찾을 수 없습니다."));
                purchaseOrder.setClient(client);
            }
            
            if (updateDto.getManagerId() != null) {
                Employee manager = employeeRepository.findById(updateDto.getManagerId())
                        .orElseThrow(() -> new RuntimeException("해당 담당자 정보를 찾을 수 없습니다."));
                purchaseOrder.setManager(manager);
            }

            if (updateDto.getWarehouseId() != null) {
                Warehouse warehouse = warehouseRepository.findById(updateDto.getWarehouseId())
                        .orElseThrow(() -> new RuntimeException("해당 창고 정보를 찾을 수 없습니다."));
                purchaseOrder.setReceivingWarehouse(warehouse);
            }

            // 발주요청 일자, 납기일자 수정
            purchaseOrder.setDate(updateDto.getDate() != null ? updateDto.getDate() : purchaseOrder.getDate());
            purchaseOrder.setDeliveryDate(updateDto.getDeliveryDate() != null ? updateDto.getDeliveryDate() : purchaseOrder.getDeliveryDate());

            // 통화 수정
            if (updateDto.getCurrencyId() != null) {
                purchaseOrder.setCurrency(currencyRepository.findById(updateDto.getCurrencyId())
                        .orElseThrow(() -> new RuntimeException("통화 정보를 찾을 수 없습니다.")));
                purchaseOrder.setCurrency(purchaseOrder.getCurrency());

            }

            // 부가세 적용 수정
            purchaseOrder.setVatId(updateDto.getVatId() != null ? updateDto.getVatId() : purchaseOrder.getVatId());
            purchaseOrder.setRemarks(updateDto.getRemarks());

            ElectronicTaxInvoiceStatus status = ElectronicTaxInvoiceStatus.valueOf(updateDto.getElectronicTaxInvoiceStatus());
            purchaseOrder.setElectronicTaxInvoiceStatus(status);

            purchaseOrder.getPurchaseOrderDetails().clear();  // 기존 항목을 제거

            // 발주 상세 정보 업데이트 - 등록 관련 메서드의 getPurchaseRequest 메서드 사용
            PurchaseOrder newOrder = getPurchaseOrder(updateDto, purchaseOrder);

            PurchaseOrder updateOrder = purchaseOrderRepository.save(newOrder);

            return toDetailDto(updateOrder);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 요청입니다.: " + e.getMessage());
        } catch (RuntimeException e) {
            log.error("발주서 수정 중 오류 발생: ", e);
            throw new RuntimeException("발주서 수정 중 오류가 발생했습니다.");
        }
    }

    /**
     * 발주서 삭제
     * @param id
     * @return
     */
    @Override
    public String deletePurchaseOrder(Long id) {
        try{
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("해당 발주 요청을 찾을 수 없습니다."));
            purchaseOrderRepository.delete(purchaseOrder);
            return "발주서가 삭제되었습니다.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (RuntimeException e) {
            return "발주서 삭제 중 오류가 발생했습니다.";
        }
    }
}
