INSERT INTO permissao (descricao) VALUES
	 ('ADMIN'),
	 ('MANAGER'),
	 ('USER');
	 
INSERT INTO usuario (account_non_expired,account_non_locked,credentials_non_expired,enabled,nome,senha,username) VALUES
	 (1,1,1,1,'admin','$2a$10$OByGe8eh90MwRD26P9b1POGirM7dHV1puSOpOmArEgjAuzyIcubTG','admin');
	 
	 INSERT INTO usuario_permissao (id_usuario, id_permissao) VALUES (1, 1);