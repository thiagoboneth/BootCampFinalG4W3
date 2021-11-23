# Workplace-Meli



### Equipe 4 W-3

Requesito 5.1 bootcampw3



## Projeto Final Wave-3 Digital-House



***API Meli-Frios, aplicação desenvolvida para compra e venda de produtos frescos.***



### **Antes de usar a API siga as \*instruções de autenticação na documentação\* para ter seu token de acesso**



#### **Faça o clone do projeto e o execute, e faça uso do swagger. Seguindo as instruções abaixo na documentação**

Em seu **application.propeties**, insira as seguintes linhas:

```
spring.datasource.url=jdbc:mysql://localhost:3306/bootcampw3 /*nome do banco MySQL => bootcampw3*/
spring.datasource.username=root /*usuário*
spring.datasource.password	/*senha*/
```



<u>Insira no banco a seguinte Query para criar seu usuário:</u>



***INSERT** **into** users (username ,**password** )**values**('thiago','$2a$10$nuNzCf8js96QGhe53QeBTu3PEBcJV1sCY9xuE47kTIIAvSgzm8fYy');*

***UPDATE** users **set** enabled=**TRUE**;*



## Auth

Autenticação se faz necessária para utilizar os endpoints.



### AUTH - User



seguindo o exemplo abaixo de usuário teste, use para se registrar no banco:



```
{
  "user": "thiago",
  "senha": "123"
}
```



No End-Point a seguir:

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/autenticacao-controller



Com o token em mãos, você pode criar o seu usuário, seguindo o modelo abaixo:

* Pegue o token gerado e libere no botão "**Authorize**" ***no lado direito superior da página***, inserindo o token para uso da documentação Swagger.

​		- após liberação no sistema, você poderá usar a documentação por completo.



### POST - User

*Caso queira criar seu usuário, verifique se você já está com acesso liberado, caso receba um **ERROR403** verifique se o passo anterior foi feito corretamente.*



Pare criar um novo usuário você deve informar o "**user**" e colocar no campo de senha o seu **token** gerado no passo anterior:



```
{
    "user":"Meli",
    "senha":"$2a$10$nuNzCf8js96QGhe53QeBTu3PEBcJV1sCY9xuE47kTIIAvSgzm8fYy",
    "ativo":true
}
```



### POST - Product



Informando o nome do produto seguindo de suas descrição faça o cadastro no sistema seguindo o modelo de requisição.

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/save



```
{
  "name": "Manga ",
  "description": "Manga com Vermelho e sabor ligeiramente adocicado"
}
```



### GET2.1 - ListProducts



Para listar os produtos em sistema basta solicitar via barra de navegação a requisição

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/list



### GET5.2 - DueDateListFiltedByTypeAndTypeList

**Obs: para usar esse end point você precisa ter registrado um batchstock e uma section**

Obtenha todos os lotes armazenados em um setor e uma categoria especifica de um armazém, ordenados por sua data de vencimento, e possível ordena o produto de maneira crescente e decrescente.

* se faz necessário informar a quantidade máxima de dias que faltam para o vencimento.
* se faz necessário informar o nome da categoria onde esta armazenado o produto.
* se faz necessário informar o tipo de ordenação. "**asc** => para crescente e **desc**=> para decrescente"

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/duedatelist/list/%7BnumberOfDay%7D/%7BCategoryName%7D/%7BtypeOfList%7D



### GET5.1 -DueDate

**Obs: para usar esse end point você precisa ter registrado um batchstock e uma section**

Obtenha todos os lotes armazenados em um setor de um armazém, ordenados por sua data de vencimento.

- se faz necessário informar a quantidade máxima de dias que faltam para o vencimento.

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/duedatelist/%7BnumberOfDay%7D/%7BidSection%7D



### PUT - updateProduct

Para atualizar um produto no sistema basta informar seu ID na barra de navegação e enviar a atualização via corpo de requisição.

```
{
  "name": "Castanha do Pará",
  "description": "Fruta da região norte do Brasil"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/duedatelist/%7BnumberOfDay%7D/%7BidSection%7D



### DEL - deleteProduct

Para deletar um produto basta informar o ID na barra de navegação e efetuar requisição para remover o produto do sistema

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/product/delete/%7Bid%7D





## WareHouse - Armazém



Esse é o ponto de registro onde você nomeia o armazém e cria o armazém onde irá alocar os seus produtos do anúncio em nosso marketplace



### POST - WareHouse

```
{
  "wareHouseName": "Nome do Armazém"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/warehouse/save



### GET - listWareHouse

Listar Todos os Warehouses registrados em sistema no momento em que a requisição é enviada. Não precisa enviar dados

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/warehouse/list



### PUT - updateWareHouse

Para atualizar informações de um WareHouse. Basta enviar na barra de navegação o ID do WareHouse que deseja atualizar e enviar no corpo a atualização de informação

```
{
  "idWareHouse": 1,
  "wareHouseName": "WareHouse 1"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/warehouse/update/%7Bid%7D





## Buyer

Controller para cadastro de compradores



### POST - Buyer

Para cadastrar o Comprador você deve informar o nome e sobrenome por corpo e enviar a requisição.

```
{
  "name": "João",
  "lastname": "II"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/buyer-controller/saveBuyer



### GET - listBuyer

Para listar todos os compradores cadastrado em sistema basta enviar a requisição

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/buyer/list



### PUT - updateBuyer

Para atualizar um Comprador em sistema, basta informar na barra de navegação o ID e enviar via requisição os dados atualizados seguindo o mesmo modelo do cadastro

```
{
  "name": "Thiago",
  "lastname": "Boneth"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/buyer/update/%7Bid%7D



### DEL - deleteBuyer

Para deletar um comprador basta informar o ID na barra de navegação que o mesmo será removido do sistema

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/buyer/delete/%7Bid%7D





## Section

Todo WareHouse é composto de sessões, seguindo o modelo de projeto temos 3 sessões, FRESCO, REFRIGERADOR E CONGELADO. Mas caso queira poderá cadastrar quantas sessões achar necessário para que sua WareHouse opere conforme sua necessidade.



### GET 2.2 - ListProductsForCategory

Informe na key "name" o "value" do seu produto cadastrado. Por exemplo "FRESCO", "CONGELADOS" e etc para listar os produtos que lhe são de interesse. Desde que o mesmo exista no sistema

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/section/listCategory/%7Bcategory%7D



### GET 4.1 - ListProductById

Listar Todos os produtos registrados no carrinho, momento em que a requisição é enviada. Não precisa enviar dados

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/section/listProduct/%7Bid%7D



### POST 1.1 (1/2) Section

Informe a capacidade de armazenamento da sua sessão assim como o ID do WareHouse em que você fará o vinculo dessa sessão. E nomeie a sessão conforme o seu funcionamento

```
{
  "capacity": 400,
  "idWareHouse": 1,
  "category": "CONGELADO"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/section/save



### GET - listSection

Liste todas as sessões enviando a requisição via barra de navegação

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/section-controller/listSection



### PUT - updateSection

Informe o ID da sessão em barra de navegação e atualize as informações fazendo a requisição em corpo

```
{
  "capacity": 352,
  "category": "CONGELADO",
  "idWareHouse": 2
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/section/update/%7Bid%7D





## Delegate

Toda Section de WareHouse tem seu responsável, segue passos de cadastro de responsável e uso



### POST - Delegate

Para cadastrar um Delegate informe o nome e sobrenome e o ID da sessão que o mesmo irá trabalhar

```
{
  "name": "Roberta",
  "lastname": "Motta",
  "idSection": 1
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/delegate/save



### GET - listDelegate

Para listar todos os delegados de sessão basta enviar a requisição pelo navegador

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/delegate/list



### PUT - updateDelegate

Informe o ID na barra de navegação para atualizar a informações sendo enviadas pelo corpo de requisição

```
{
  "name": "Thiago",
  "lastname": "Boneth",
  "idSection": 1
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/delegate-controller/updateDelegate





## Seller

Controller para vinculo de vendedor no sistema



### POST - Seller

Para cadastrar um vendedor use o seu nome e sobrenome em requisição no corpo

```
{
  "name": "Thiago",
  "lastname": "Luis"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/seller/save



### GET - listSeller

Chame a lista de Vendedores por requisição na barra de navegação

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/seller/list



### PUT - updateSeller

Para atualizar um vendedor, basta enviar o ID na barra de requisição e as informações atualizadas no corpo

```
{
  "name": "Salvador",
  "lastname": "Dali"
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/seller/update/%7Bid%7D



### DEL - deleteSeller

Para deletar um Vendedor basta informar seu ID em requisição na barra de navegação

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/seller/delete/%7Bid%7D





## SalesAd

Aqui será onde você vai fazer sua gestão de anúncios na plataforma



### POST - SalesAD

Para criar um anuncio você deve informar:

Temperatura máxima/mínima, para controle na sessão do Warehouse
O preço do produto que está vendendo.
A quantidade que o armazém possui
Quem está vendendo pelo ID
E o ID do produto.



```
{
  "volume": 50,
  "minimumTemperature": 1,
  "maximumTemperature": 10,
  "price": 80,
  "idSeller": 1,
  "idProduct": 4
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/salesad/save



### GET - listSalesAd

Para listar todos os anúncios em sistema basta fazer a requisição pela barra de navegação

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/salesad/list



### PUT - updateSalesAd

Para atualizar basta retornar as informações em corpo com o ID em navegação

```
{
  "volume": 50,
  "minimumTemperature": 1,
  "maximumTemperature": 10,
  "price": 80,
  "idSeller": 1,
  "idProduct": 1
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/salesad/update/%7Bid%7D





## BathStock

Faça o controle do seu lote de produtos



### POST 1.1 - BatchStock

Para controlar seu lote do anuncio você precisa pro as seguintes informações
Temperatura média.
Minima temperatura.
Quantidade inicial
Quantidade atual no lote
Data de validade do produto
ID do anuncio

```
{
  "currentTemperature": 38,
  "minimumTemperature": 5,
  "initialQuantity": 200,
  "currentQuantity": 25,
  "dueDate": "2021-11-11",
  "idSalesAd": 1
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/batchstock/save



### GET - listBatchStock

Usando a barra de navegação faça a requisição para acompanhar o status de todos os lotes cadastrados em anúncios

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/batchstock/list



### PUT - updateBatchStock

Informe o ID do lote na barra de navegação e faça a atualização do lote no corpo de requisição

```
{
  "currentTemperature": 35,
  "minimumTemperature": 1,
  "initialQuantity": 50,
  "currentQuantity": 25,
  "dueDate": "2021-11-25",
  "idSalesAd": 1
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/batchstock/update/%7Bid%7D





## ItemOfProduct

Interaja com os itens do carrinho.



### GET 3.1 - ListProductOfBatchStock

Procura por produto por lotes.

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/item-of-product-controller/listProductOfBatchStock



### GET 3.2 - ListOrderProduct

Lista os produtos em ordem: ex - FRIOS, QUENTES E ETC

Basta informar no filtro.

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/itemOfProduct/listOrderProduct/%7Bname%7D



### DEL2.5 - ZerarCarrinho

Ele devolve os itens para o estoque e zera o carrinho

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/itemOfProduct/delete/%7Bid%7D



### GET2.4 - ListProductForCar

Liste todos os produtos do carrinho.

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/itemOfProduct/lisProductForCar/%7BidCar%7D





## InboundOrder

Cada compra chega como uma solicitação de entrada no Warehouse onde o produto recém chegado será alocado



### POST 1.1 (2/2) - InboundOrder

Informe o ID do BathStock que irá receber o produto e o ID da sessão em que ele ficará guardado.

```
{
  "idBatchStock": 1,
  "idSection": 4
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/inboundorder/save



### PUT 1.2 - UpdateInboundOrder

Informe o ID na barra de navegação para atualização e as informações atualizadas no corpo de requisição

```
{
  "idBatchStock": 1,
  "idSection": 5
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/inboundorder/update/%7Bid%7D



### GET ListInboundOrder

Liste todos os produtos seguidos de suas respectivas sessões

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/inboundorder/list







## PurchOrder

O carrinho de compras da aplicação



### POST 2.3 - PurchaseOrder

O carrinho de compras recebe o ID do comprados e o ID dos itens que serão comprados

```
{
  "idBuyer": 1,
  "itemOfProduct": [
    {
      "idSalesAd": 2,
      "quantity": 10
    },
    {
      "idSalesAd": 1,
      "quantity": 10
    }
  ]
}
```

***Link para uso:***

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#:~:text=/api/v1/fresh-products/purchaseorder/add
