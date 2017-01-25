CREATE TABLE `ticker`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `currency_id` INT NOT NULL COMMENT '货币代码',
  `price` INT NOT NULL COMMENT '货币价格,单位聪',
  `lowest_ask` INT NOT NULL COMMENT '卖盘最低价,单位聪',
  `higest_bid` INT NOT NULL COMMENT '买盘最高价,单位聪',
  `percent_change` INT NOT NULL DEFAULT 0 COMMENT '价格波动,单位万',
  `base_vol` INT NOT NULL DEFAULT 0 COMMENT '成交量',
  `quote_vol` INT NOT NULL DEFAULT 0,
  `high_24h` INT NOT NULL DEFAULT 0 COMMENT '24小时最高价,单位聪',
  `low_24h` INT NOT NULL DEFAULT 0 COMMENT '24小时最低价,单位聪',
  `utm_create` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = INNODB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT '历史价格,计价货币单位BTC';

CREATE TABLE `currency` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `currency_id` BIGINT NOT NULL COMMENT '货币代码',
  `currency_name` VARCHAR(32) NOT NULL COMMENT '货币名称',
  `currency_code` VARCHAR(32) NOT NULL COMMENT '货币简称',
  `utm_create` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utm_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_currency_name_code` (`currency_name`,`currency_code`) USING BTREE
)ENGINE = INNODB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT '货币种类';