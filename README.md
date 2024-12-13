# Projeto Gerencia Pedidos

## Introdução

Os principais requisitos e considerações são:

Funcionalidades: Receber pedidos de um sistema externo (Produto A). Calcular o valor total de cada pedido, somando o valor de seus produtos. 
Disponibilizar a consulta de pedidos e seus status para outro sistema externo (Produto B). 
Escalabilidade: Lidar com um alto volume de pedidos (150-200 mil por dia). 
Consistência: Garantir a consistência dos dados e evitar duplicidade de pedidos. 
Disponibilidade: Manter o sistema disponível mesmo com um alto volume de requisições. 
Tecnologias: Java com Spring Boot. Banco de dados a ser escolhido. 
Proposta de Arquitetura e Implementação Com base nos requisitos e considerando as boas práticas de desenvolvimento, propõe-se a seguinte arquitetura:

## Endpoints

Endereço **http://localhost:8081/**

Através do endpoint **POST /api/order** Collection do Postman consta no projeto com o nome: **GerenciaPedidosAPI.postman_collection.json**

O usuário do banco é root sem senha e para acesso a API o usuário é herbert senha: 123456

**Uma parte importante do sistema é a segurança, por isso foi utilizado o padrão JWT e obter o token é a primeira coisa a ser feita antes de acessar os demais endpoints.

Endpoint **POST /login** para obter o token de acesso.

```
{
	"login": "herbert",
	"senha": "123456"
}
```

O token de acesso deverá ser informado no header Authorization das requisições, exemplo abaixo.

Authorization →Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

## Diagrama de Classe

![image](https://github.com/user-attachments/assets/c546377d-901f-467d-ab82-acb56d93324a)
