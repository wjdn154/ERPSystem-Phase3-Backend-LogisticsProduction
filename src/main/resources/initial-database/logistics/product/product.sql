INSERT INTO product_image (image_path, created_at) VALUES
('images/product1_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product2_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product3_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product4_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product5_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product6_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product7_image.jpg', '2024-09-30 12:04:05.630002'),
('images/product8_image.jpg', '2024-09-30 12:04:05.630002');

-- # INSERT INTO product (code, product_group_id, client_id, process_routing_id, product_type, purchase_price, sales_price, name, standard, unit, image_path, remarks, is_active)
-- # VALUES
-- #     ('B0001', 6, 1, 1, 'PRODUCTS', 500000, 550000, '고급 노트북', '15인치', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/74c51b6e-7e0e-495a-89c4-ad70879cae33_고급노트북.png', '디지털/가전', TRUE),
-- #     ('B0002', 6, 2, 1, 'PRODUCTS', 200000, 250000, '게이밍 모니터', '27인치', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/f7d382a2-1526-481e-afab-3a49b15561f4_게이밍-모니터.jpg', '디지털/가전', TRUE),
-- #     ('B0003', 3, 3, 2, 'GOODS', 80000, 100000, '무선 키보드', '1세트', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/881809f7-9e4f-4b8c-ba37-c58a3b512d63_무선키보드.jpg', '일상용품', TRUE),
-- #     ('B0004', 6, 4, 3, 'PRODUCTS', 150000, 180000, '블루투스 스피커', '1개', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/ea8fce6a-cc31-4131-ad54-c199bf9f5aa9_블루투스 스피커.jpg', '디지털/가전', TRUE),
-- #     ('B0005', 6, 5, 4, 'GOODS', 10000, 15000, '고속 충전기', '1개', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/cd7e3f77-66f4-4ff4-abab-6dfec158ce96_고속충전기.jpg', '디지털/가전', TRUE),
-- #     ('B0006', 6, 6, 5, 'GOODS', 30000, 40000, '스마트워치 스트랩', '1개', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/ea8fce6a-cc31-4131-ad54-c199bf9f5aa9_블루투스 스피커.jpg', '디지털/가전', TRUE),
-- #     ('B0007', 6, 7, 6, 'PRODUCTS', 700000, 750000, '게이밍 데스크탑', '1대', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/a53a352f-621b-4c01-bdf1-5627adf8d129_게이밍데스크탑.jpg', '디지털/가전', TRUE),
-- #     ('B0008', 6, 8, 2, 'GOODS', 40000, 50000, '외장 하드디스크', '1TB', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/55a10d48-54ed-49c5-ace8-2178c7c92a4c_외장하드디스크.jpg', '디지털/가전', TRUE),
-- #     ('B0009', 6, 9, 3, 'GOODS', 25000, 30000, 'USB-C 허브', '1개', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/a4d4dd35-4fe2-4c09-bc0b-29f604b13238_usb-c허브.jpg', '디지털/가전', TRUE),
-- #     ('B0010', 6, 10, 4, 'GOODS', 120000, 150000, '무선 이어폰', '1세트', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/0f17a09a-ad85-4ca9-a71b-ee765ea22a77_무선이어폰.jpg', '디지털/가전', TRUE),
-- #     ('B0011', 6, 1, 5, 'GOODS', 50000, 60000, '스마트폰 보호 케이스', '1개', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/585efc81-06a5-41dc-9930-3e4bd5efe027_스마트폰_케이스.jpg', '패션잡화', TRUE),
-- #     ('B0012', 6, 2, 6, 'PRODUCTS', 180000, 220000, '무선 공유기', '1대', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/89ffe569-5fff-4357-b75d-608f1a5c6be2_무선공유기.jpg', '디지털/가전', TRUE),
-- #     ('B0013', 3, 3, 2, 'GOODS', 250000, 300000, '프린터', '1대', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/7b1a5530-393e-4ad5-8ae4-b2208b9a888d_프린터.jpg', '일상용품', TRUE),
-- #     ('B0014', 6, 4, 1, 'PRODUCTS', 1500000, 1800000, '4K 스마트 TV', '55인치', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/fd9fffe6-4e67-4b92-ac05-905ffbb8063a_스마트티비.jpg', '디지털/가전', TRUE),
-- #     ('B0015', 6, 5, 3, 'PRODUCTS', 90000, 120000, '프로젝터', '1대', 'SET', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/f357a4bd-6319-45bd-9b73-23b9ef0aa0bb_프로젝터.jpg', '디지털/가전', TRUE),
-- # -- 부품 목록
-- #     ('P0016', 6, 6, 2, 'SEMI_FINISHED_PRODUCT', 10000, 15000, 'RAM 메모리', '8GB', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/dcde0f98-136c-4f67-a5c5-70ef74d5dd84_ram.jpg', '컴퓨터 부품', TRUE),
-- #     ('P0017', 6, 7, 3, 'SEMI_FINISHED_PRODUCT', 30000, 40000, 'SSD', '256GB', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/2bc40c0c-dbc2-4131-9894-65f81082c77e_ssd.jpg', '저장 장치', TRUE),
-- #     ('P0018', 6, 8, 4, 'SEMI_FINISHED_PRODUCT', 15000, 20000, '파워 서플라이', '600W', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/8e16e776-4441-4f05-a7a1-bdb240ad8a19_파워서플라이.jpg', '컴퓨터 부품', TRUE),
-- #     ('P0019', 6, 9, 5, 'SEMI_FINISHED_PRODUCT', 20000, 30000, '쿨러', '120mm', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/8d78c0fa-1163-49cd-bfb7-28e9c0a62082_쿨러.jpg', '컴퓨터 부품', TRUE),
-- #     ('P0020', 6, 10, 6, 'SEMI_FINISHED_PRODUCT', 5000, 10000, 'USB 케이블', '1m', 'EA', 'https://rjsgh-bucket.s3.ap-northeast-2.amazonaws.com/4c06a5e5-04e9-4c13-9d8b-8cc4bf8e441a_usb캐이블.jpg', '모바일 액세서리', TRUE);
-- #

INSERT INTO product (
    code, product_group_id, client_id, process_routing_id, product_type, purchase_price, sales_price, name, standard, unit, image_path, remarks, is_active
) VALUES
      ('B0001', 1, 1, 1, 'SEMI_FINISHED_PRODUCT', 1500, 2000, '엔진오일필터 엑시브250', '200mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/48bd0525-91f0-4e71-94f2-b732c191c025_엔진오일필터-엑시브250.jpg', '자동차 엔진 오일 필터', TRUE),
      ('B0002', 2, 2, 1, 'SEMI_FINISHED_PRODUCT', 5000, 7000, '브레이크 패드', '300mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/33678db6-1f6c-497f-877c-e957003783a5_브레이크 패드.jpg', '고성능 브레이크 패드', TRUE),
      ('B0003', 3, 3, 2, 'SEMI_FINISHED_PRODUCT', 8000, 10000, '연료 필터', '150mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/4c0adb4e-b8a3-4ab6-b12b-51113ddca4b1_3_연료필터.png', '차량 연료 정화용 필터', TRUE),
      ('B0004', 4, 4, 3, 'SEMI_FINISHED_PRODUCT', 12000, 15000, '점화 플러그', '100mm', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/8d164f9d-7222-417a-84ce-9ef964235c26_4_점화 플러그.jpg', '자동차 점화 플러그 세트', TRUE),
      ('B0005', 5, 5, 4, 'SEMI_FINISHED_PRODUCT', 3000, 5000, '에어 필터', '250mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/65d48528-e21d-4442-a04d-ba5df14dff2f_5_에어 필터.jpg', '자동차 실내 공기 정화용 에어 필터', TRUE),
      ('B0006', 6, 6, 5, 'SEMI_FINISHED_PRODUCT', 60000, 80000, '알터네이터', '1대', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/719d0a90-cbce-42ae-a1be-52a319f752c7_6_알터네이터.jpg', '자동차 전기 발전기', TRUE),
      ('B0007', 7, 7, 6, 'SEMI_FINISHED_PRODUCT', 20000, 30000, '서스펜션 스프링', '1개', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/293fdf5c-4473-4996-80db-20af5bcb833f_7_서스펜션 스프링.jpg', '차량 서스펜션용 스프링', TRUE),
      ('B0008', 8, 8, 2, 'SEMI_FINISHED_PRODUCT', 45000, 60000, '배기 머플러', 'M사이즈', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/ecf48a01-372b-4878-a188-20918dac417f_8_배기 머플러.jpg', '자동차 배기 시스템 부품', TRUE),
      ('B0009', 2, 9, 3, 'SEMI_FINISHED_PRODUCT', 90000, 120000, '디스크 로터', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/c2aa6837-8ed8-4f44-a5ab-6cd948941058_9_디스크 로터.jpg', '브레이크 시스템용 디스크 로터', TRUE),
      ('B0010', 9, 10, 4, 'SEMI_FINISHED_PRODUCT', 10000, 15000, '와이퍼 블레이드', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/a3646268-7fe3-4603-9f42-828a4815fad5_10_와이퍼 블레이드.jpg', '자동차 유리 와이퍼', TRUE),
      ('B0011', 5, 1, 5, 'SEMI_FINISHED_PRODUCT', 1800, 3000, '라디에이터 캡', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/0d739caa-ed1a-4a67-93e5-f0beb45b83b9_11_라디에이터 캡.jpg', '자동차 냉각 시스템용 캡', TRUE),
      ('B0012', 1, 2, 6, 'SEMI_FINISHED_PRODUCT', 4000, 5500, '클러치 디스크', '180mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/e8539012-0b3b-4fe3-ac7b-71bbabf866ce_12_클러치 디스크.jpg', '자동차 클러치 시스템 부품', TRUE),
      ('B0013', 1, 3, 2, 'SEMI_FINISHED_PRODUCT', 7000, 9000, '타이밍 벨트', '1.2m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/f22ee828-73c6-430e-90bf-be8baa637611_13_타이밍 벨트.jpg', '엔진 타이밍 벨트', TRUE),
      ('B0014', 1, 4, 1, 'SEMI_FINISHED_PRODUCT', 12000, 15000, '오일 펌프', '600ml', 'BOTTLE', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/58953524-81d7-45f0-ad99-faee611c26c2_14_오일 펌프.jpg', '자동차 엔진 오일 펌프', TRUE),
      ('B0015', 2, 5, 3, 'SEMI_FINISHED_PRODUCT', 35000, 45000, '브레이크 캘리퍼', '1세트', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/92715b09-8531-4c16-a332-5767945c01a8_15_브레이크 캘리퍼.png', '브레이크 시스템용 캘리퍼', TRUE),
      ('B0016', 2, 1, 1, 'SEMI_FINISHED_PRODUCT', 1600, 2100, '브레이크 디스크', '220mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/55ceac37-97d4-4a0e-89d8-627b6f38c6d5_16_브레이크 디스크.jpg', '자동차 브레이크 디스크', TRUE),
      ('B0017', 6, 2, 1, 'SEMI_FINISHED_PRODUCT', 5200, 7500, 'ABS 모듈', '300mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/835539c8-b3f5-42f7-8a2b-f9ac637347b0_17_ABS 모듈.jpg', '자동차 ABS 시스템 모듈', TRUE),
      ('B0018', 5, 3, 2, 'SEMI_FINISHED_PRODUCT', 8200, 10500, '냉각 팬', '350mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/882f1d98-edb6-4399-8fed-423778368188_18냉각 팬.jpg', '엔진 냉각 시스템용 팬', TRUE),
      ('B0019', 3, 4, 1, 'SEMI_FINISHED_PRODUCT', 13000, 16000, '연료 펌프', '150mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/836436f6-ca29-4cd0-936c-c003d38c0398_19_연료 펌프.png', '자동차 연료 시스템용 펌프', TRUE),
      ('B0020', 4, 5, 4, 'SEMI_FINISHED_PRODUCT', 3200, 4800, '점화 코일', '120mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/6bc5869f-6003-49b3-9dbc-2fdf0a7f0d33_20_점화 코일.jpg', '자동차 점화 시스템용 코일', TRUE),
      ('B0021', 6, 6, 5, 'SEMI_FINISHED_PRODUCT', 61000, 81000, '발전기 벨트', '2m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/f7e41692-938d-4471-b58e-ef0de4574110_21_발전기 벨트.jpg', '자동차 발전기 벨트', TRUE),
      ('B0022', 7, 7, 6, 'SEMI_FINISHED_PRODUCT', 21000, 32000, '쇼크 업소버', '1개', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/e723412b-12cf-4961-a3f5-8eab8b50ee8e_22_쇼크 업소버.jpg', '차량 서스펜션용 쇼크 업소버', TRUE),
      ('B0023', 6, 8, 2, 'SEMI_FINISHED_PRODUCT', 46000, 62000, '배터리 케이블', '3m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/5bb6b58f-aa2e-4cbb-bc7c-98f007c2a517_23_배터리 케이블.jpg', '자동차 배터리 연결 케이블', TRUE),
      ('B0024', 1, 9, 3, 'SEMI_FINISHED_PRODUCT', 91000, 122000, '터보차저', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/05595026-94dd-4039-a0e5-e18ce687e84c_24_터보차저.jpg', '자동차 엔진용 터보차저', TRUE),
      ('B0025', 8, 10, 4, 'SEMI_FINISHED_PRODUCT', 11000, 17000, '배기 가스 센서', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/97022638-3475-4eb9-a005-98ae1b388759_25_배기 가스 센서.jpg', '자동차 배기가스 제어 센서', TRUE),
      ('B0026', 5, 1, 5, 'SEMI_FINISHED_PRODUCT', 2000, 3100, '서모스탯', '90mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/4133dd2a-780d-4464-a738-b1a25b255777_26_서모스탯.jpg', '자동차 냉각 시스템용 서모스탯', TRUE),
      ('B0027', 7, 2, 6, 'SEMI_FINISHED_PRODUCT', 4200, 5700, '핸들 조인트', '250mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/e5cc669e-19f0-4fd5-a4ce-24e3b2b75beb_27_핸들 조인트.jpg', '자동차 스티어링 시스템용 조인트', TRUE),
      ('B0028', 3, 3, 2, 'SEMI_FINISHED_PRODUCT', 7500, 9500, '연료 레일', '1.5m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/328a68d7-d586-46b4-aea3-7e7349b9d6ea_28_연료 레일.jpg', '엔진 연료 공급 레일', TRUE),
      ('B0029', 3, 4, 1, 'SEMI_FINISHED_PRODUCT', 12500, 15500, '변속기 오일 팬', '400mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/91d84aec-47d9-4e86-8fdf-b897f5994c70_29_변속기오일팬.jpg', '자동차 변속기 오일 팬', TRUE),
      ('B0030', 7, 5, 3, 'SEMI_FINISHED_PRODUCT', 36000, 47000, '스티어링 휠', '1개', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/33bef4d0-7983-43b9-95ef-25e2b8996c68_30_스티어링 휠.jpg', '자동차 스티어링 휠', TRUE),
      ('B0031', 5, 6, 4, 'SEMI_FINISHED_PRODUCT', 5000, 7200, '워터 펌프', '200mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/3963140a-d589-4ffd-9705-e50261b6850a_31_워터 펌프.jpg', '자동차 냉각 시스템 워터 펌프', TRUE),
      ('B0032', 5, 7, 5, 'SEMI_FINISHED_PRODUCT', 2800, 4000, '라디에이터 호스', '1m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/6b5b6104-4dd3-47ca-bd35-b351f0b2e006_32_라디에이터호스.jpg', '자동차 라디에이터 연결 호스', TRUE),
      ('B0033', 7, 8, 6, 'SEMI_FINISHED_PRODUCT', 8000, 10500, '브레이크 라인', '2m', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/5b8e4998-5d45-4e8c-8352-899c729459a2_33_브레이크 라인.jpg', '브레이크 시스템용 라인', TRUE),
      ('B0034', 5, 9, 2, 'SEMI_FINISHED_PRODUCT', 18500, 23000, '에어컨 압축기', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/0ffce683-9ad9-4468-84d5-b1439318f123_34_에어컨 압축기.jpg', '자동차 에어컨 시스템 압축기', TRUE),
      ('B0035', 7, 10, 3, 'SEMI_FINISHED_PRODUCT', 34000, 46000, '스태빌라이저 링크', '1개', 'SET', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/cccaf7c8-2951-4df4-8b0c-624cc91e5293_35_스태빌라이저 링크.png', '서스펜션 안정화 장치', TRUE),
      ('B0036', 6, 1, 4, 'SEMI_FINISHED_PRODUCT', 1200, 1900, '퓨즈 박스', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/44d74eb5-7ba5-4823-91c6-f6f874a4cb9c_36_퓨즈박스.jpg', '자동차 전기 시스템용 퓨즈 박스', TRUE),
      ('B0037', 6, 2, 5, 'SEMI_FINISHED_PRODUCT', 5400, 7100, '차량 제어 유닛', '500g', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/7b51edf3-7569-4938-8725-0cb671336765_37_ECU.jpg', '자동차 제어 시스템 유닛', TRUE),
      ('B0038', 4, 3, 6, 'SEMI_FINISHED_PRODUCT', 8800, 12000, '점화 분배기', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/7e96c4f2-011c-4be0-8beb-c062d05b3099_38_점화 분배기.jpg', '자동차 점화 시스템 분배기', TRUE),
      ('B0039', 1, 4, 1, 'SEMI_FINISHED_PRODUCT', 13500, 17500, '엔진 마운트', '1개', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/7d2453d1-3566-4af6-abdc-883a2eda9e20_39_엔진 마운트.jpg', '엔진 장착용 마운트', TRUE),
      ('B0040', 1, 5, 2, 'SEMI_FINISHED_PRODUCT', 26000, 34000, '클러치 마스터 실린더', '300mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/15d7ff34-0394-495c-b165-d30f0796a358_40_클러치 마스터 실린더.jpg', '자동차 클러치 작동용 실린더', TRUE),
      ('B0041', 6, 6, 3, 'SEMI_FINISHED_PRODUCT', 9000, 12000, '릴레이 어셈블리', '150mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/00e4df26-1529-4e4f-8663-d6c1b18c2f37_41_릴레이 어셈블리.jpg', '자동차 전기 시스템 릴레이', TRUE),
      ('B0042', 7, 7, 4, 'SEMI_FINISHED_PRODUCT', 7000, 9500, '서스펜션 암', '450mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/3461163e-f347-4149-8429-523e35cc9109_42_서스펜션 암.jpg', '차량 서스펜션 연결 암', TRUE),
      ('B0043', 6, 8, 5, 'SEMI_FINISHED_PRODUCT', 1500, 2500, '배터리 홀더', '200mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/5cc08a0d-f306-44dd-bfea-c716be724d1e_43_배터리 홀더.jpg', '자동차 배터리 고정용 홀더', TRUE),
      ('B0044', 7, 9, 6, 'SEMI_FINISHED_PRODUCT', 4800, 6200, '공기 흐름 센서', '100mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/8804e84f-54fa-49aa-be11-8fb9ee431aec_44_공기 흐름 센서.jpg', '자동차 공기 흐름 감지 센서', TRUE),
      ('B0045', 5, 10, 1, 'SEMI_FINISHED_PRODUCT', 11500, 15000, '에어컨 필터', '180mm', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/14f8bbb2-40c6-428e-a5b7-5ccb1d3908fa_45_에어컨 필터.jpg', '자동차 에어컨 공기 필터', TRUE),

-- PRODUCTS 유형
      ('P0001', 1, 1, 1, 'PRODUCTS', 55000, 75000, '카 엔진', '2.0L', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/8778aaa5-3965-4bab-bca2-d0e275247b01_46_카 엔진.jpg', '자동차 엔진', TRUE),
      ('P0002', 2, 2, 1, 'PRODUCTS', 35000, 48000, '자동차 변속기', '6단', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/83957a2e-7092-423a-a88b-efbaa1629471_47_자동차변속기.jpg', '자동차 변속 시스템', TRUE),
      ('P0003', 6, 3, 2, 'PRODUCTS', 15000, 20000, '자동차 배터리', '12V', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/2b2d6fcf-1ecd-4c92-b888-a682216d39b1_48_자동차배터리.jpg', '자동차 전원 공급용 배터리', TRUE),
-- GOODS 유형
      ('G0001', 9, 4, 3, 'GOODS', 3000, 5000, '타이어 광택제', '500ml', 'BOTTLE', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/33766f42-0807-42a2-99e4-9e814b936f37_49_타이어광택제.jpg', '타이어 보호 및 광택제', TRUE),
      ('G0002', 9, 5, 4, 'GOODS', 10000, 15000, '차량용 청소기', '소형', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/9bcdcab5-259d-495e-9784-5966bafa95f7_50_차량용청소기.jpg', '차량 내부 청소용 소형 청소기', TRUE),
      ('G0003', 9, 6, 5, 'GOODS', 5000, 7500, '차량용 방향제', '레몬향', 'EA', 'https://omz-erp-image-upload-bucket.s3.ap-northeast-2.amazonaws.com/18a25cc5-6f89-4729-bab0-096f5bb35f6e_51_차량용청소기.jpg', '차량 내부용 방향제', TRUE),
-- INTANGIBLE_GOODS 유형
      ('I0001', 11, 7, 6, 'INTANGIBLE_GOODS', 200000, 250000, '차량 소프트웨어 업데이트', 'v3.1', 'N/A', NULL, '차량 내부 시스템 소프트웨어 업데이트 서비스', TRUE),
      ('I0002', 11, 8, 2, 'INTANGIBLE_GOODS', 150000, 180000, '네비게이션 소프트웨어', 'v2.0', 'N/A', NULL, '차량 내비게이션 시스템 소프트웨어', TRUE),
      ('I0003', 11, 9, 3, 'INTANGIBLE_GOODS', 50000, 70000, '차량 진단 소프트웨어', 'v1.5', 'N/A', NULL, '차량 진단용 소프트웨어 서비스', TRUE);
