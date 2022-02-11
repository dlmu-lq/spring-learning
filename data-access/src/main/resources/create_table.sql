CREATE TABLE `a_pos` (
                         `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                         `lng` double unsigned NOT NULL COMMENT '经度',
                         `lat` double unsigned NOT NULL COMMENT '纬度',
                         `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='历史消息表';