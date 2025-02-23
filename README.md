# Desafio BTG Pactual - Backend

## Objetivo

Construir um microserviço capaz de processar pedidos a partir de uma fila RabbitMQ e fornecer uma API REST para as seguintes funcionalidades:

1. **Consultar o valor total de um pedido.**
2. **Consultar a quantidade de pedidos por cliente.**
3. **Consultar a lista de pedidos realizados por cliente.**

### Tecnologias Utilizadas:
- **MongoDB**: Para armazenar os dados de pedidos.
- **RabbitMQ**: Para processar os pedidos de forma assíncrona.
- **Spring Boot**: Para criar a API REST.
- **Docker**: para a containerização do RabbitMQ e do MongoDB.

---

## Mensagem da Fila RabbitMQ

Quando um novo pedido é realizado, ele será enviado para a fila RabbitMQ como um JSON com a seguinte estrutura:

```json
{
    "codigoPedido": 1,
    "codigoCliente": 1,
    "itens": [
        {
            "produto": "Aspirador de pó",
            "quantidade": 1,
            "preco" : 300.33
        },
        {
            "produto": "Espelho",
            "quantidade": 2,
            "preco" : 333
        },
        {
            "produto": "Moldura Para Quadro 40x40",
            "quantidade": 1,
            "preco" : 52.2
        }
    ]
}
```

---

## API REST

### Endpoint: `GET /customers/{customerId}/orders`

**Descrição**:  
Este endpoint retorna uma lista de pedidos realizados por um cliente, incluindo o valor total de cada pedido, o total acumulado dos pedidos e a quantidade de pedidos realizados por esse cliente.

#### Exemplo de Resposta:

```json
{
  "summary": {
    "totalOnOrders": 270.10
  },
  "data": [
    {
      "orderId": 1,
      "customerId": 1,
      "total": 120.12
    },
    {
      "orderId": 2,
      "customerId": 1,
      "total": 250.13
    }
  ],
  "pagination": {
    "page": 0,
    "pageSize": 10,
    "totalElements": 2,
    "totalPages": 1
  }
}
```

### Detalhes da Resposta:

- **summary**:
  - **totalOnOrders**: Valor total acumulado de todos os pedidos do cliente.

- **data**:
  - **orderId**: ID único do pedido.
  - **customerId**: ID único do cliente.
  - **total**: Valor total de cada pedido.

- **pagination**:
  - **page**: Número da página atual dos resultados.
  - **pageSize**: Número de pedidos retornados por página.
  - **totalElements**: Total de pedidos encontrados.
  - **totalPages**: Total de páginas de resultados.

---

## Requisitos

1. **Processamento de Pedidos via RabbitMQ**:
   - O microserviço deve consumir as mensagens da fila RabbitMQ com os dados dos pedidos e processá-los.
   
2. **Armazenamento no MongoDB**:
   - Os pedidos devem ser armazenados no MongoDB, incluindo informações como `codigoPedido`, `codigoCliente`, e a lista de itens (produto, quantidade, preço).
   
3. **API REST**:
   - O microserviço deve expor um endpoint REST para consultar a lista de pedidos de um cliente, o valor total acumulado dos pedidos e a quantidade de pedidos por cliente.

    
