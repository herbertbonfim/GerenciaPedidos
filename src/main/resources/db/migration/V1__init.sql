create table usuario(
	id bigint not null auto_increment,
	login varchar(100) not null,
	senha varchar(100) not null,
	primary key (id)
);

INSERT INTO `gerenciapedidos_api`.`usuario` (`login` ,`senha`) VALUES ('herbert', '$2a$12$JsK4KnAM6EkUL08bbUGoCeVdInJr/7oUwyeCO0q4.oOJTbSoh43WS');

create table endereco(
	id bigint not null auto_increment,
	cep varchar(15) not null,
	rua varchar(100) not null,
	bairro varchar(100) not null,
	cidade varchar(100) not null,
	uf varchar(2) not null,
	primary key (id)
);

create table cliente(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchar(200) not null,
	endereco_id bigint not null,
	constraint fk_cliente_endereco_id foreign key (endereco_id) references endereco (id),
	primary key (id)
);

create table produto(
	id bigint not null auto_increment,
	codigo bigint not null,
	nome varchar(200) not null,
	valor numeric(15,2),
	primary key (id)
);

create table pedido(
	id bigint not null auto_increment,
	codigo bigint not null,
	emissao varchar(50) not null,
	status varchar(50) not null,
	valor_produtos numeric(15,2),
	valor_total numeric(15,2),
	ativo tinyint not null,
	cliente_id bigint not null,		
	primary key (id),
	constraint fk_pedido_cliente_id foreign key (cliente_id) references cliente (id),
	constraint uc_pedido_codigo unique (codigo)
);

create table item_pedido(
	id bigint not null auto_increment,
	produto_id bigint not null,
	sequencia int not null,
	valor numeric(15,2),	
	primary key (id, sequencia),
	constraint fk_item_produto__id foreign key (produto_id) references produto (id)
);

create table pedido_itens (
	pedidos_id bigint not null,
	itens_id bigint not null,
	primary key (itens_id),
	constraint fk_itens_pedido_id foreign key (pedidos_id) references pedido (id),
	constraint fk_pedido_itens_id foreign key (itens_id) references item_pedido (id)
);