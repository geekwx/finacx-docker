CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKloth4k4vj0m5yyxfavjug9aum` (`usuario_id`),
  CONSTRAINT `FKloth4k4vj0m5yyxfavjug9aum` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
)