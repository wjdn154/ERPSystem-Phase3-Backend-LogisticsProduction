-- 주문서(Orders) 초기 데이터 삽입
INSERT INTO orders (client_id, manager_id, warehouse_id, currency_id, vat_id, journal_entry_code, electronic_tax_invoice_status, date, delivery_date, state, remarks)
VALUES
    (1, 2, 1, 6, 1, '4', 'UNPUBLISHED', '2024-10-01', '2024-10-10', 'IN_PROGRESS', '고객 요청에 따른 주문서'),
    (2, 3, 2, 6, 2, '5', 'PUBLISHED', '2024-10-02', '2024-10-15', 'IN_PROGRESS', '정기 계약 주문서 발행'),
    (3, 4, 3, 6, 3, '6', 'UNPUBLISHED', '2024-10-03', '2024-10-20', 'IN_PROGRESS', '신규 거래처 주문서 작성'),
    (4, 5, 4, 6, 4, '5', 'UNPUBLISHED', '2024-10-04', '2024-10-25', 'COMPLETED', '대량 주문에 대한 주문서'),
    (5, 6, 5, 6, 5, '4', 'PUBLISHED', '2024-10-05', '2024-10-30', 'IN_PROGRESS', '고객 요청에 따른 추가 주문서'),
    (6, 2, 1, 1, 6, '4', 'UNPUBLISHED', '2024-10-06', '2024-10-16', 'IN_PROGRESS', '긴급 납품 요청에 따른 주문서'),
    (7, 3, 2, 2, 7, '4', 'PUBLISHED', '2024-10-07', '2024-10-17', 'IN_PROGRESS', '계절 상품 주문서'),
    (8, 4, 3, 1, 8, '5', 'UNPUBLISHED', '2024-10-08', '2024-10-18', 'IN_PROGRESS', '대형 거래처 신규 주문서'),
    (9, 5, 4, 2, 9, '4', 'PUBLISHED', '2024-10-09', '2024-10-19', 'IN_PROGRESS', '연말 프로모션 주문서'),
    (10, 6, 5, 1, 10, '4', 'UNPUBLISHED', '2024-10-10', '2024-10-20', 'COMPLETED', '정기 납품 요청에 따른 추가 주문서'),
    (11, 2, 6, 2, 1, '6', 'PUBLISHED', '2024-10-11', '2024-10-21', 'IN_PROGRESS', '특별 할인 적용된 주문서'),
    (12, 3, 1, 1, 2, '4', 'UNPUBLISHED', '2024-10-12', '2024-10-22', 'IN_PROGRESS', '고객 피드백 반영된 주문서'),
    (13, 4, 2, 2, 3, '4', 'PUBLISHED', '2024-10-13', '2024-10-23', 'IN_PROGRESS', '신제품 출시 관련 주문서');
-- 주문서 상세(OrdersDetail) 초기 데이터 삽입
INSERT INTO orders_detail (orders_id, product_id, quantity, supply_price, local_amount, vat, remarks)
VALUES
    -- 주문서 1의 상세 항목들 (여러 품목을 섞음)
    (1, 1, 100, 150000.00, NULL, 15000.00, '엔진 오일 필터'),
    (1, 2, 200, 1000000.00, NULL, 100000.00, '브레이크 패드'),
    (1, 5, 50, 250000.00, NULL, 25000.00, '에어 필터'),
    (1, 10, 75, 750000.00, NULL, 75000.00, '와이퍼 블레이드'),

    -- 주문서 2의 상세 항목들
    (2, 3, 30, 240000.00, NULL, 24000.00, '연료 필터'),
    (2, 6, 10, 600000.00, NULL, 60000.00, '알터네이터'),
    (2, 7, 80, 2400000.00, NULL, 240000.00, '서스펜션 스프링'),

    -- 주문서 3의 상세 항목들
    (3, 8, 50, 3000000.00, NULL, 300000.00, '배기 머플러'),
    (3, 9, 40, 4800000.00, NULL, 480000.00, '디스크 로터'),
    (3, 15, 90, 4050000.00, NULL, 405000.00, '브레이크 캘리퍼'),
    (3, 20, 25, 120000.00, NULL, 12000.00, '점화 코일'),

    -- 주문서 4의 상세 항목들 (대량 주문 포함)
    (4, 4, 300, 4500000.00, NULL, 450000.00, '점화 플러그'),
    (4, 11, 250, 750000.00, NULL, 75000.00, '라디에이터 캡'),
    (4, 18, 150, 1200000.00, NULL, 120000.00, '냉각 팬'),
    (4, 22, 200, 6400000.00, NULL, 640000.00, '쇼크 업소버'),

    -- 주문서 5의 상세 항목들 (다양한 품목 추가)
    (5, 12, 80, 440000.00, NULL, 44000.00, '클러치 디스크'),
    (5, 14, 60, 900000.00, NULL, 90000.00, '오일 펌프'),
    (5, 16, 90, 1890000.00, NULL, 189000.00, '브레이크 디스크'),
    (5, 19, 70, 910000.00, NULL, 91000.00, '연료 펌프'),
    (5, 25, 40, 680000.00, NULL, 68000.00, '배기 가스 센서'),

    -- 주문서 6의 상세 항목들
    (6, 5, 120, 360000.00, NULL, 36000.00, '에어 필터'),
    (6, 10, 90, 900000.00, NULL, 90000.00, '와이퍼 블레이드'),
    (6, 12, 50, 275000.00, NULL, 27500.00, '클러치 디스크'),

    -- 주문서 7의 상세 항목들
    (7, 8, 60, 3600000.00, NULL, 360000.00, '배기 머플러'),
    (7, 14, 75, 1125000.00, NULL, 112500.00, '오일 펌프'),
    (7, 16, 45, 945000.00, NULL, 94500.00, '브레이크 디스크'),

    -- 주문서 8의 상세 항목들
    (8, 19, 100, 1300000.00, NULL, 130000.00, '연료 펌프'),
    (8, 20, 120, 384000.00, NULL, 38400.00, '점화 코일'),
    (8, 3, 80, 640000.00, NULL, 64000.00, '연료 필터'),

    -- 주문서 9의 상세 항목들
    (9, 4, 90, 1350000.00, NULL, 135000.00, '점화 플러그'),
    (9, 11, 55, 165000.00, NULL, 16500.00, '라디에이터 캡'),
    (9, 22, 30, 960000.00, NULL, 96000.00, '쇼크 업소버'),

    -- 주문서 10의 상세 항목들
    (10, 6, 40, 2400000.00, NULL, 240000.00, '알터네이터'),
    (10, 17, 100, 520000.00, NULL, 52000.00, 'ABS 모듈'),
    (10, 25, 80, 1360000.00, NULL, 136000.00, '배기 가스 센서'),

    -- 주문서 11의 상세 항목들
    (11, 7, 60, 1800000.00, NULL, 180000.00, '서스펜션 스프링'),
    (11, 15, 70, 3150000.00, NULL, 315000.00, '브레이크 캘리퍼'),
    (11, 24, 40, 3640000.00, NULL, 364000.00, '터보차저'),

    -- 주문서 12의 상세 항목들
    (12, 9, 40, 4800000.00, NULL, 480000.00, '디스크 로터'),
    (12, 1, 50, 75000.00, NULL, 7500.00, '엔진 오일 필터'),
    (12, 13, 70, 630000.00, NULL, 63000.00, '타이밍 벨트'),

    -- 주문서 13의 상세 항목들
    (13, 2, 120, 600000.00, NULL, 60000.00, '브레이크 패드'),
    (13, 18, 85, 697500.00, NULL, 69750.00, '냉각 팬'),
    (13, 23, 30, 1380000.00, NULL, 138000.00, '배터리 케이블');