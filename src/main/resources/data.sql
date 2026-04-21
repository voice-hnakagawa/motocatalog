INSERT INTO m_motorcycle (moto_no, moto_name, seat_height, cylinder, cooling, price, brand_id, version, ins_dt) VALUES
(1, 'YZF-R1', 850, 4, '水冷', 1500000, 1, 1, NOW()),
(2, 'CBR1000RR', 820, 4, '水冷', 1400000, 2, 1, NOW()),
(3, 'GSX-R1000', 810, 4, '水冷', 1300000, 3, 1, NOW()),
(4, 'Ninja ZX-10R', 830, 4, '水冷', 1600000, 4, 1, NOW());

INSERT INTO m_brand (brand_id, brand_name) VALUES
(1, 'Yamaha'),
(2, 'Honda'),
(3, 'Suzuki'),
(4, 'Kawasaki'),
(5, 'moto guzzi');
