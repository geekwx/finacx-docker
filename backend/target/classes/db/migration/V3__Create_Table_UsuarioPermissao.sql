CREATE TABLE `usuario_permissao` (
  `id_usuario` bigint(20) NOT NULL,
  `id_permissao` bigint(20) NOT NULL,
  KEY `FKjvcxjnrmdhdv6eti5d7svm5xw` (`id_permissao`),
  KEY `FKbo8hww1whbpxq8ancjokhnfds` (`id_usuario`),
  CONSTRAINT `FKbo8hww1whbpxq8ancjokhnfds` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKjvcxjnrmdhdv6eti5d7svm5xw` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id`)
)