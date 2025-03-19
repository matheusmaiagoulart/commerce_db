create table usuarios (
    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    ativo TINYINT(1) not null,
primary key(id)
);