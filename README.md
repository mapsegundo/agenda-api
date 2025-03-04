# API de Agenda de Contatos

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.x-6DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=java)](https://www.oracle.com/java/)
[![JWT](https://img.shields.io/badge/JWT-Auth-black?logo=json-web-tokens)](https://jwt.io/)
[![Swagger](https://img.shields.io/badge/Swagger-API_Docs-85EA2D?logo=swagger)](https://swagger.io/)

API REST para gerenciamento de contatos pessoais com autenticação JWT.

## 👨‍💻 Desenvolvedor

- **Nome**: Marshall Paiva
- **LinkedIn**: [linkedin.com/in/marshallpaiva](https://www.linkedin.com/in/marshallpaiva/)
- **GitHub**: [github.com/mapsegundo](https://github.com/mapsegundo)

## 🚀 Tecnologias

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Autenticação**: JWT (JSON Web Token)
- **Documentação**: SpringDoc OpenAPI (Swagger)
- **Banco de Dados**: H2 Database (em memória)

## 📋 Requisitos

- Java 17
- Maven 3.8+

## 🔧 Configuração

### Variáveis de Ambiente

Para ambientes de produção, configure as seguintes variáveis de ambiente:

- `JWT_SECRET`: Chave secreta para assinatura dos tokens JWT (deve ter pelo menos 512 bits / 64 caracteres para o algoritmo HS512)
- `JWT_EXPIRATION`: Tempo de expiração do token em milissegundos (padrão: 86400000 = 24 horas)

### Banco de Dados

Por padrão, a aplicação utiliza um banco de dados H2 em memória. Para ambientes de produção, configure um banco de dados adequado no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agenda
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

## ▶️ Executando a Aplicação

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/agenda-api.git
cd agenda-api

# Execute a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

## 📖 Documentação da API

A documentação da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 🔗 Endpoints Principais

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
  "senha": "123456"
}
```

### Contatos

#### Listar todos os contatos

```
GET /api/v1/contatos
```

#### Obter um contato

```
GET /api/v1/contatos/{id}
```

#### Criar um contato

```
POST /api/v1/contatos
```

Exemplo de requisição:

```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "telefone": "11987654321",
  "endereco": "Rua Exemplo, 123"
}
```

#### Atualizar um contato

```
PUT /api/v1/contatos/{id}
```

#### Excluir um contato

```
DELETE /api/v1/contatos/{id}
```

## 🔒 Segurança

### Autenticação

A API utiliza autenticação baseada em JWT (JSON Web Token). Para acessar endpoints protegidos, é necessário incluir o token no cabeçalho de autorização:

```
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9...
```

### Proteção de Endpoints

Todos os endpoints relacionados a contatos (`/api/v1/contatos/**`) são protegidos e requerem autenticação.

## 🧪 Testes

Para executar os testes automatizados:

```bash
mvn test
```

## 📁 Estrutura do Projeto

```
agenda-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── agenda/
│   │   │           ├── config/        # Configurações do Spring Boot
│   │   │           ├── controller/    # Controllers REST
│   │   │           ├── dto/           # Data Transfer Objects
│   │   │           ├── exception/     # Exceções personalizadas
│   │   │           ├── model/         # Entidades de domínio
│   │   │           ├── repository/    # Repositórios JPA
│   │   │           ├── security/      # Configurações de segurança e JWT
│   │   │           └── service/       # Serviços de negócio
│   │   └── resources/
│   │       ├── application.properties # Configurações da aplicação
│   │       └── data.sql               # Script de inicialização (opcional)
│   └── test/                          # Testes unitários e de integração
├── pom.xml                            # Configuração do Maven
└── README.md                          # Documentação principal
```

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 🤝 Contribuindo

Contribuições são bem-vindas! Consulte [CONTRIBUTING.md](CONTRIBUTING.md) para obter detalhes sobre como contribuir para este projeto.
