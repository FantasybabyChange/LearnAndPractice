CREATE TABLE IF NOT EXISTS `fantasybaby_dev_error`.`user`(
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `name` VARCHAR(45) NULL COMMENT '组号',
    `nick_name` VARCHAR(100)  NULL COMMENT '集合单类型',
    `age` INT(11) null COMMENT '版本号',
    `created_date` DATETIME(3)  NULL COMMENT '创建时间',
    PRIMARY KEY (`id`))
    ENGINE = INNODB
    DEFAULT CHARACTER SET = utf8
    COMMENT '用户';
