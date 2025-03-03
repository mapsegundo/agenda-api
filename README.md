# Agenda API

API REST para gerenciamento de agenda de contatos desenvolvida com Spring Boot.

## Desenvolvedor

**Marshall Paiva**  
LinkedIn: [https://www.linkedin.com/in/marshallpaiva/](https://www.linkedin.com/in/marshallpaiva/)

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Validation
- H2 Database (banco de dados em memória)
- Lombok
- SpringDoc OpenAPI (Swagger)

## Funcionalidades

- **CRUD de Contatos**: Operações completas de criação, leitura, atualização e exclusão
- **Validação de Dados**: Validação de campos obrigatórios e formatos (email, telefone)
- **Documentação API**: Interface Swagger para teste e documentação
- **Banco de Dados**: Persistência em banco H2 (em memória)

## Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.6 ou superior

## Configuração e Execução

1. Clone o repositório
2. Navegue até a pasta do projeto:
   ```
   cd agenda-api
   ```
3. Execute a aplicação com Maven:
   ```
   mvn spring-boot:run
   ```
4. A API estará disponível em: `http://localhost:8080`
5. A documentação Swagger estará disponível em: `http://localhost:8080/swagger-ui.html`
6. Console do H2 (banco de dados): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:agendadb`
   - Usuário: `sa`
   - Senha: (vazio)

## Estrutura do Projeto

```
agenda-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── agenda/
│   │   │           ├── config/         # Configurações do Spring
│   │   │           ├── controller/     # Controladores REST
│   │   │           ├── domain/         # Entidades JPA
│   │   │           ├── dto/            # Objetos de Transferência de Dados
│   │   │           ├── exception/      # Tratamento de exceções
│   │   │           ├── mapper/         # Conversores entre entidades e DTOs
│   │   │           ├── model/          # Modelos adicionais
│   │   │           ├── repository/     # Repositórios JPA
│   │   │           ├── service/        # Camada de serviço
│   │   │           └── AgendaApplication.java  # Classe principal
│   │   └── resources/
│   │       └── application.properties  # Configurações da aplicação
│   └── test/                           # Testes automatizados
└── pom.xml                             # Dependências Maven
```

## Endpoints da API

### Contatos

| Método | URL                   | Descrição               |
| ------ | --------------------- | ----------------------- |
| GET    | /api/v1/contatos      | Lista todos os contatos |
| GET    | /api/v1/contatos/{id} | Busca contato por ID    |
| POST   | /api/v1/contatos      | Cria um novo contato    |
| PUT    | /api/v1/contatos/{id} | Atualiza um contato     |
| DELETE | /api/v1/contatos/{id} | Remove um contato       |

## Modelo de Dados

### Contato

```json
{
  "id": 1,
  "nome": "Nome do Contato",
  "telefone": "(99) 99999-9999",
  "email": "email@exemplo.com",
  "endereco": "Endereço do contato (opcional)"
}
```

## Validações

- **Nome**: Campo obrigatório
- **Telefone**: Campo obrigatório, formato (99) 99999-9999
- **Email**: Campo obrigatório, formato de email válido
- **Endereço**: Campo opcional

## Documentação da API

A documentação completa da API está disponível através do Swagger UI:

- URL: `http://localhost:8080/swagger-ui.html`

## Banco de Dados

O projeto utiliza o H2 Database, um banco de dados em memória para facilitar o desenvolvimento e testes.

- Console: `http://localhost:8080/h2-console`
- Configurações no arquivo: `src/main/resources/application.properties`

## Desenvolvimento

Para executar o projeto em modo de desenvolvimento:

```
mvn spring-boot:run
```

Para compilar o projeto:

```
mvn clean package
```

Para executar o JAR gerado:

```
java -jar target/agenda-api-1.0.0.jar
```

## Licença

Este projeto está licenciado sob a licença MIT.

---

Desenvolvido por Marshall Paiva - [LinkedIn](https://www.linkedin.com/in/marshallpaiva/)
