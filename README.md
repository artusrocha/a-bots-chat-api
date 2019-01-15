# Teste Vivo
A aplicação foi desenvolvida em Java Spring Boot, com Maven.
Utilizei hibernate e a base de dados testada foi MySQL.

Está ainda com a documentação do contrato do API gerada no padrão Swagger e com um modulo gráfico Swagger UI.
O SwaggerUI pode ser acessado no caminho http://[DOMINIO]:8088/swagger-ui.html (mudar a porta 8088, caso ela seja trocada na configuração).
O contrato em formato json do swagger pode ser encontrado em http://[DOMINIO]:8088/api-docs

Para execução é necessário configurar em  src/main/resources/application.properties  uma base de dados preexistente.
O Spring está configurado (no application.properties) para criar as tabelas ao executar.
Um modo de fazer o build com o uso do Maven (testado em ambiente Linux):
```bash
cd CAMINHO/PARA/PASTA/DO/PROJETO
mvn clean
mvn install
```
O build estará disponivél em target/vivoteste-0.0.1-SNAPSHOT.jar
Para exucutar:
```bash
java -jar target/vivoteste-0.0.1-SNAPSHOT.jar
```

Em seu modelo relacional existem 3 entidades, cada uma destas entidades possuem um campo *id* que é do tipo UUID armazenado no banco de dados como binário (16 bits) para uma melhor performance, mas traduzido para hexadecimal de forma transparente:
 - > Agent: que representa todo agente que envia mensagens. Podendo ser um bot ou um usuário anônimo. Para sinalizar que é um bot á campo booleano nomeado *isBot*.
 - > Conversation: Esta entidade agrupa as diversas mensagens dentro de um mesmo tópico.
 - > Message: que representa cada mensagem enviada. Esta possui uma entidade do tipo Agent nomeada no campo toAgent e uma outra entidade do tipo Agent nomeada no campo fromAgent. Esta entidade ainda possui o campo texto nomeado *text* e o campo nomeado *conversation* que corresponde a uma entidade do tipo Conversation. Há ainda campos *transients* que não são armazenados no banco de dados e servem apenas nas chamadas da API, são eles os campos *to*, *from* e *conversation_id* que são traduzidos respectivamentos para os campos *toAgent*, *fromAgent* e *conversation*.
  
## A API

Endpoints:
- /bots
GET [/bots] Return a Bot List
POST [/bots] Create a Bot
GET [/bots/{id}] Return a Bot
PUT [/bots/{id}] Update a Bot
DELETE [/bots/{id}] Delete a Bot

- /conversation | Este não estava prevista nos requisitos, mas é opcional, não é necessário usa-lo na operação da API.
GET [/conversation] Return a Conversation List
POST [/conversation] Create a Conversation
GET [/conversation/{id}] Return a Conversation

- /messages 
GET [/messages] Return a Message List From a Conversation
POST [/messages] Create a Message
*obs: Caso o campo 'from' seja omitido será criado um novo usuário anônimo. Caso o campo 'conversation_id seja omitido uma nova Conversation é criada e associada a mensagem.'*
GET [/messages/{id}] Return a Message


## Débito Técnico
Os outputs e inputs da API não estão ainda iguais aos passados no requisitos.
Algumas mudanças foram feitas para transmitir certos campos como Entidades.
Por exemplo a API está retornando o campo toAgent (com uma entidade Agent).
Isto teria que ser ajustado.

Os relacionamentos ainda não estão bons.
O relacionamento Conversation possui muitas Messages precisa ser melhorado.

Uma entidade adicional chamada Bot deve ser um campo opcional da entidade Agent.
Para que este possua mais informações especificas do Bot caso o seja.

Para os objetivos de interação via chat, usar streaming via websocket talvez seja uma melhor opção do que uma API Restful.
Outra tecnologia interessante neste caso pode ser o kafka ou o AWS Kinesis.

  
