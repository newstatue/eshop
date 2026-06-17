CREATE DATABASE IF NOT EXISTS eshop DEFAULT CHARACTER SET utf8mb4;
USE eshop;

CREATE TABLE IF NOT EXISTS `user` (
                                      `id`          BIGINT      NOT NULL AUTO_INCREMENT,
                                      `phone`       VARCHAR(20) NOT NULL COMMENT '手机号',
                                      `nickname`    VARCHAR(50) COMMENT '昵称',
                                      `deleted`     TINYINT     NOT NULL DEFAULT 0,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `uk_phone` (`phone`)
);

CREATE TABLE IF NOT EXISTS `spu` (
                                     `id`          BIGINT       NOT NULL AUTO_INCREMENT,
                                     `name`        VARCHAR(100) NOT NULL COMMENT '商品名称',
                                     `description` VARCHAR(500) COMMENT '商品描述',
                                     `status`      TINYINT      NOT NULL DEFAULT 1 COMMENT '1上架 0下架',
                                     `deleted`     TINYINT      NOT NULL DEFAULT 0,
                                     PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `sku` (
                                     `id`      BIGINT        NOT NULL AUTO_INCREMENT,
                                     `spu_id`  BIGINT        NOT NULL COMMENT '关联SPU',
                                     `spec`    VARCHAR(200)  NOT NULL COMMENT '规格 如: 黑色/128G',
                                     `price`   DECIMAL(10,2) NOT NULL COMMENT '价格',
                                     `stock`   INT           NOT NULL DEFAULT 0 COMMENT '库存',
                                     `deleted` TINYINT       NOT NULL DEFAULT 0,
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `uk_spu_spec` (`spu_id`,`spec`),
                                     INDEX `idx_spu_id` (`spu_id`)
);

CREATE TABLE IF NOT EXISTS `spu_image` (
                                           `id`      BIGINT       NOT NULL AUTO_INCREMENT,
                                           `spu_id`  BIGINT       NOT NULL COMMENT '关联SPU',
                                           `url`     VARCHAR(500) NOT NULL COMMENT '图片地址',
                                           `sort`    INT          NOT NULL DEFAULT 0 COMMENT '排序 0为主图',
                                           `deleted` TINYINT      NOT NULL DEFAULT 0,
                                           PRIMARY KEY (`id`),
                                           UNIQUE KEY `uk_spu_url`(`spu_id`,`url`),
                                           INDEX `idx_spu_id` (`spu_id`)
);

CREATE TABLE IF NOT EXISTS `order` (
                                       `id`           BIGINT        NOT NULL AUTO_INCREMENT,
                                       `order_no`     VARCHAR(50)   NOT NULL COMMENT '订单编号',
                                       `user_id`      BIGINT        NOT NULL COMMENT '用户ID',
                                       `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
                                       `status`       TINYINT       NOT NULL DEFAULT 0 COMMENT '0待付款 1已付款 2已取消',
                                       `deleted`      TINYINT       NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_order_no` (`order_no`),
                                       INDEX `idx_user_id` (`user_id`)
);

CREATE TABLE IF NOT EXISTS `order_item` (
                                            `id`        BIGINT        NOT NULL AUTO_INCREMENT,
                                            `order_id`  BIGINT        NOT NULL COMMENT '订单ID',
                                            `sku_id`    BIGINT        NOT NULL COMMENT 'SKU ID',
                                            `sku_spec`  VARCHAR(200)  NOT NULL COMMENT '规格快照',
                                            `spu_name`  VARCHAR(100)  NOT NULL COMMENT '商品名快照',
                                            `price`     DECIMAL(10,2) NOT NULL COMMENT '下单时价格快照',
                                            `quantity`  INT           NOT NULL COMMENT '购买数量',
                                            PRIMARY KEY (`id`),
                                            INDEX `idx_order_id` (`order_id`)
);

CREATE TABLE IF NOT EXISTS `stock_log` (
                                           `id`         BIGINT      NOT NULL AUTO_INCREMENT,
                                           `sku_id`     BIGINT      NOT NULL COMMENT 'SKU ID',
                                           `order_no`   VARCHAR(50) NOT NULL COMMENT '关联订单号',
                                           `before_qty` INT         NOT NULL COMMENT '扣减前库存(Redis快照)',
                                           `deduct_qty` INT         NOT NULL COMMENT '扣减数量',
                                           `after_qty`  INT         NOT NULL COMMENT '扣减后库存(Redis快照)',
                                           `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           PRIMARY KEY (`id`),
                                           INDEX `idx_sku_id` (`sku_id`),
                                           INDEX `idx_order_no` (`order_no`)
);

-- ===== 测试数据 =====

INSERT IGNORE INTO `spu` (`id`, `name`, `description`) VALUES
                                                           (1,  'iPhone 17',         'Apple iPhone 17 系列'),
                                                           (2,  'iPhone 17 Pro',     'Apple iPhone 17 Pro 系列'),
                                                           (3,  'iPhone 17 Pro Max', 'Apple iPhone 17 Pro Max 系列'),
                                                           (4,  'iPhone 17e',        'Apple iPhone 17e 系列'),
                                                           (5,  'iPhone Air',        'Apple iPhone Air 系列'),
                                                           (6,  'MacBook Air 13"',   'Apple MacBook Air 13英寸'),
                                                           (7,  'MacBook Air 15"',   'Apple MacBook Air 15英寸'),
                                                           (8,  'MacBook Pro 14"',   'Apple MacBook Pro 14英寸'),
                                                           (9,  'MacBook Pro 16"',   'Apple MacBook Pro 16英寸'),
                                                           (10, 'MacBook Neo',       'Apple MacBook Neo 系列'),
                                                           (11, 'Mac mini',          'Apple Mac mini');

INSERT IGNORE INTO `sku` (`spu_id`, `spec`, `price`, `stock`) VALUES
                                                                  (1, '黑色/128G',    5999.00, 100),
                                                                  (1, '蓝色/128G',    5999.00, 100),
                                                                  (1, '薰衣草/128G',  5999.00,  80),
                                                                  (1, '鼠尾草/128G',  5999.00,  80),
                                                                  (1, '白色/128G',    5999.00, 100),
                                                                  (2, '蓝色/256G',    7999.00,  60),
                                                                  (2, '橙色/256G',    7999.00,  60),
                                                                  (2, '银色/256G',    7999.00,  60),
                                                                  (3, '蓝色/256G',    9999.00,  50),
                                                                  (3, '橙色/256G',    9999.00,  50),
                                                                  (3, '银色/256G',    9999.00,  50),
                                                                  (4, '黑色/128G',    4999.00, 120),
                                                                  (4, '粉色/128G',    4999.00, 120),
                                                                  (4, '白色/128G',    4999.00, 120),
                                                                  (5, '黑色/256G',    6999.00,  80),
                                                                  (5, '蓝色/256G',    6999.00,  80),
                                                                  (5, '金色/256G',    6999.00,  80),
                                                                  (5, '白色/256G',    6999.00,  80),
                                                                  (6, '蓝色/8G/256G',   8999.00, 40),
                                                                  (6, '午夜色/8G/256G', 8999.00, 40),
                                                                  (6, '银色/8G/256G',   8999.00, 40),
                                                                  (6, '星光色/8G/256G', 8999.00, 40),
                                                                  (7, '蓝色/8G/256G',   10999.00, 30),
                                                                  (7, '午夜色/8G/256G', 10999.00, 30),
                                                                  (7, '银色/8G/256G',   10999.00, 30),
                                                                  (7, '星光色/8G/256G', 10999.00, 30),
                                                                  (8, '深空黑/16G/512G', 14999.00, 30),
                                                                  (8, '银色/16G/512G',   14999.00, 30),
                                                                  (9, '深空黑/16G/512G', 17999.00, 20),
                                                                  (9, '银色/16G/512G',   17999.00, 20),
                                                                  (10, '粉色/8G/256G',   9999.00, 40),
                                                                  (10, '柠檬色/8G/256G', 9999.00, 40),
                                                                  (10, '靛蓝色/8G/256G', 9999.00, 40),
                                                                  (10, '银色/8G/256G',   9999.00, 40),
                                                                  (11, '银色/8G/256G',   4999.00, 50);

INSERT IGNORE INTO `spu_image` (`spu_id`, `url`, `sort`) VALUES
                                                             (1,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-black.webp',        0),
                                                             (1,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-blue.webp',         1),
                                                             (1,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-lavender.webp',     2),
                                                             (1,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-sage.webp',         3),
                                                             (1,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-white.webp',        4),
                                                             (2,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-pro-blue.webp',     0),
                                                             (2,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-pro-orange.webp',   1),
                                                             (2,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-pro-silver.webp',   2),
                                                             (3,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-promax-blue.webp',  0),
                                                             (3,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-promax-orange.webp',1),
                                                             (3,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17-promax-silver.webp',2),
                                                             (4,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17e-black.webp',       0),
                                                             (4,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17e-pink.webp',        1),
                                                             (4,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-17e-white.webp',       2),
                                                             (5,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-air-black.webp',       0),
                                                             (5,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-air-blue.webp',        1),
                                                             (5,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-air-gold.webp',        2),
                                                             (5,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/iphone-air-white.webp',       3),
                                                             (6,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-13inch-blue.webp',      0),
                                                             (6,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-13inch-midnight.webp',  1),
                                                             (6,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-13inch-silver.webp',    2),
                                                             (6,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-13inch-starlight.webp', 3),
                                                             (7,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-15inch-blue.webp',      0),
                                                             (7,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-15inch-midnight.webp',  1),
                                                             (7,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-15inch-silver.webp',    2),
                                                             (7,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-air-15inch-starlight.webp', 3),
                                                             (8,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/mac-macbook-pro-14inch-black.webp', 0),
                                                             (8,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/mac-macbook-pro-14inch-silver.webp',1),
                                                             (9,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/mac-macbook-pro-16inch-black.webp', 0),
                                                             (9,  'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/mac-macbook-pro-16inch-silver.webp',1),
                                                             (10, 'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-neo-blush.webp',   0),
                                                             (10, 'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-neo-citrus.webp',  1),
                                                             (10, 'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-neo-indigo.webp',  2),
                                                             (10, 'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/macbook-neo-silver.webp',  3),
                                                             (11, 'https://base.evorsio.com/storage/v1/object/public/eshop-bucket/mac-mini.webp', 0);