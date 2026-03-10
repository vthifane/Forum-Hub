# Challenge Forum Hub

Este projeto foi desenvolvido como entrega do **Challenge Forum Hub** do programa **Oracle Next Education (ONE)** em parceria com a **Alura**.

O projeto consiste em uma **API REST de um fórum**, desenvolvida com **Spring Boot**, permitindo criar, listar, atualizar e deletar tópicos.

---

# Tecnologias

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Flyway (Migrations)
- MySQL
- Bean Validation

---

# Descrição

Esse projeto é uma versão simples de um **fórum**, permitindo realizar operações de **CRUD de tópicos**.

A API possui funcionalidades de:

- autenticação de usuário
- criação de tópicos
- listagem de tópicos
- atualização de tópicos
- exclusão de tópicos

Para acessar os endpoints protegidos é necessário realizar autenticação utilizando **JWT**.

---

# Instruções para executar o projeto

Primeiro configure o banco de dados no arquivo `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

api.security.token.secret=SEU_SECRET
```

Depois crie o banco de dados manualmente no MySQL:

```sql
CREATE DATABASE forumhub;
```

Após isso execute o projeto pela IDE ou utilizando Maven.

---

# Autenticação

Para utilizar os endpoints da API é necessário realizar login para obter um **token JWT**.

Endpoint:

```
POST
http://localhost:8080/login
```

Body:

```json
{
 "login": "LOGIN",
 "senha": "SENHA"
}
```

Se o login for realizado corretamente, a API retornará um **token JWT**.

Esse token deve ser enviado no header das próximas requisições:

```
Authorization: Bearer SEU_TOKEN
```

---

# Usuário

Para cadastrar um usuário utilize o endpoint:

```
POST
http://localhost:8080/usuarios
```

Body:

```json
{
 "login": "usuario@forum.com",
 "senha": "SENHA_COM_BCRYPT"
}
```

A senha precisa estar criptografada com **BCrypt**.

Você pode gerar a senha criptografada em:

https://bcrypt-generator.com

---

# Cursos

Para cadastrar um curso:

```
POST
http://localhost:8080/cursos
```

Body:

```json
{
 "nome": "Spring Boot",
 "categoria": "BACKEND"
}
```

---

# Tópicos

Antes de criar um tópico é necessário que exista pelo menos:

- um usuário
- um curso

---

## Criar tópico

```
POST
http://localhost:8080/topicos
```

Body:

```json
{
 "titulo": "Erro no Spring Boot",
 "mensagem": "Não consigo iniciar minha aplicação",
 "idAutor": 1,
 "idCurso": 1
}
```

---

## Listar tópicos

```
GET
http://localhost:8080/topicos
```

Retorna uma lista paginada de tópicos.

---

## Buscar tópico por ID

```
GET
http://localhost:8080/topicos/{id}
```

Retorna o tópico correspondente ao ID informado.

---

## Atualizar tópico

```
PUT
http://localhost:8080/topicos/{id}
```

Body:

```json
{
 "titulo": "Erro resolvido",
 "mensagem": "Consegui resolver o problema",
 "idCurso": 1
}
```

---

## Deletar tópico

```
DELETE
http://localhost:8080/topicos/{id}
```

Remove o tópico do banco de dados.

---

# Testando a API

Para testar os endpoints você pode utilizar ferramentas como:

- Postman
- Insomnia

Primeiro realize o **login**, copie o **token JWT** e utilize nas requisições autenticadas.

---

# Autor

Projeto desenvolvido por **Vitória Thifane**.