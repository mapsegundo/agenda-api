# API de Agenda de Contatos

API REST para gerenciamento de contatos pessoais com autenticação JWT.

## Desenvolvedor

- **Nome**: Marshall Paiva
- **LinkedIn**: [linkedin.com/in/marshallpaiva](https://www.linkedin.com/in/marshallpaiva/)
- **GitHub**: [github.com/mapsegundo](https://github.com/mapsegundo)

## Tecnologias

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Autenticação**: JWT (JSON Web Token)
- **Documentação**: SpringDoc OpenAPI (Swagger)
- **Banco de Dados**: H2 Database (em memória)

## Requisitos

- Java 17
- Maven 3.8+

## Configuração

### Variáveis de Ambiente

Para ambientes de produção, configure as seguintes variáveis de ambiente:

- `JWT_SECRET`: Chave secreta para assinatura dos tokens JWT (deve ter pelo menos 256 bits / 32 caracteres)
- `JWT_EXPIRATION`: Tempo de expiração do token em milissegundos (padrão: 86400000 = 24 horas)

### Banco de Dados

Por padrão, a aplicação utiliza um banco de dados H2 em memória. Para ambientes de produção, configure um banco de dados adequado no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agenda
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

## Executando a Aplicação

```bash
mvn spring-boot:run
```

## Documentação da API

A documentação da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```

## Endpoints Principais

### Autenticação

#### Login

```
POST /api/v1/auth/login
```

Exemplo de requisição:

```json
{
  "email": "admin@example.com",
  "senha": "123456"
}
```

Exemplo de resposta:

```json
{
  "access_token": "eyJhbGciOiJIUzUxMiJ9...",
  "token_type": "Bearer",
  "expires_in": 86400,
  "usuario": {
    "id": 1,
    "nome": "Admin",
    "email": "admin@example.com"
  }
}
```

#### Registro

```
POST /api/v1/auth/registro
```

Exemplo de requisição:

```json
{
  "nome": "Novo Usuário",
  "email": "novo@example.com",
  "senha": "senha123"
}
```

### Contatos

#### Listar todos os contatos do usuário logado

```
GET /api/v1/contatos
```

#### Buscar contato pelo ID

```
GET /api/v1/contatos/{id}
```

#### Criar novo contato

```
POST /api/v1/contatos
```

Exemplo de requisição:

```json
{
  "nome": "João Silva",
  "telefone": "(11) 98765-4321",
  "email": "joao@example.com",
  "endereco": "Rua Exemplo, 123"
}
```

#### Atualizar contato

```
PUT /api/v1/contatos/{id}
```

#### Excluir contato

```
DELETE /api/v1/contatos/{id}
```

## Autenticação

A API utiliza autenticação JWT. Para acessar endpoints protegidos, inclua o token no cabeçalho `Authorization`:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```

## Como testar a API

### 1. Console H2

O banco de dados H2 pode ser acessado em:

```
http://localhost:8080/h2-console
```

Credenciais padrão:

- JDBC URL: `jdbc:h2:mem:agendadb`
- Username: `sa`
- Password: (deixe em branco)

### 2. Usando cURL

Login:

```bash
curl -X POST "http://localhost:8080/api/v1/auth/login" -H "Content-Type: application/json" -d "{\"email\":\"admin@example.com\",\"senha\":\"123456\"}"
```

Listar contatos (substitua TOKEN pelo token obtido no login):

```bash
curl -X GET "http://localhost:8080/api/v1/contatos" -H "Authorization: Bearer TOKEN"
```

### 3. Usando Postman

1. Importe a coleção Postman disponível em `docs/Agenda-API.postman_collection.json`
2. Execute a requisição de login para obter o token
3. O token será automaticamente salvo para as demais requisições

## Segurança

Em ambientes de produção:

1. Use uma chave secreta forte e segura para o JWT
2. Configure HTTPS para todas as comunicações
3. Implemente um mecanismo de rotação de chaves
4. Considere reduzir o tempo de expiração do token

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

- **Controller**: Endpoints da API REST
- **Service**: Lógica de negócio
- **Repository**: Acesso a dados
- **Domain**: Entidades JPA
- **DTO**: Objetos de transferência de dados
- **Mapper**: Conversão entre entidades e DTOs
- **Security**: Configuração de segurança e autenticação JWT
- **Exception**: Tratamento global de exceções
- **Config**: Configurações da aplicação

## Modelo de Dados

### Usuário

- **ID**: Identificador único
- **Nome**: Nome completo do usuário
- **Email**: Email único do usuário (usado para login)
- **Senha**: Senha criptografada

### Contato

- **ID**: Identificador único
- **Nome**: Campo obrigatório
- **Telefone**: Campo obrigatório, formato (99) 99999-9999
- **Email**: Campo obrigatório, formato de email válido
- **Endereço**: Campo opcional
- **Usuário**: Associação com o usuário proprietário do contato

## Dados de Teste

O sistema é pré-carregado com os seguintes dados para teste:

### Usuários

- **Admin**: email: `admin@example.com`, senha: `123456`
- **Usuário Teste**: email: `usuario@example.com`, senha: `123456`

### Contatos

Cada usuário já possui alguns contatos para teste.

## Próximos Passos

- Implementação de paginação para listar contatos
- Suporte para upload de imagens de perfil
- Implementação de níveis de acesso (ROLES)
- Exportação de contatos em formato CSV/PDF
