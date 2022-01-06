CREATE TABLE `movimentacoes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_criacao` date DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  `conta_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKugay2c47j9h4k9tuoou16ayp` (`categoria_id`),
  KEY `FK4jw7098ybfubrvutbgpdjvvx` (`conta_id`),
  KEY `FKjrbf4at8psgc5avthvf1oahbq` (`usuario_id`),
  CONSTRAINT `FK4jw7098ybfubrvutbgpdjvvx` FOREIGN KEY (`conta_id`) REFERENCES `contas` (`id`),
  CONSTRAINT `FKjrbf4at8psgc5avthvf1oahbq` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKugay2c47j9h4k9tuoou16ayp` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`)
)
