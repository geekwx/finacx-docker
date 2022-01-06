CREATE TABLE `contas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `saldo` decimal(19,2) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKorvm50pcloonmtclp3ig609u1` (`usuario_id`),
  CONSTRAINT `FKorvm50pcloonmtclp3ig609u1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
)
