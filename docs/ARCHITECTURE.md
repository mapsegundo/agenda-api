# Arquitetura da API de Agenda de Contatos

Este documento descreve a arquitetura da API de Agenda de Contatos, fornecendo uma visão geral técnica do sistema.

## Visão Geral

A API de Agenda de Contatos é uma aplicação Spring Boot que segue uma arquitetura multicamada, com separação clara de responsabilidades entre os componentes. A aplicação foi desenvolvida seguindo os princípios SOLID, Design Patterns e boas práticas de desenvolvimento de software.

## Diagrama de Arquitetura

```
┌─────────────────────────────┐
│       Cliente (Frontend)    │
└───────────────┬─────────────┘
                │
                ▼
┌─────────────────────────────┐
│         API Gateway         │
└───────────────┬─────────────┘
                │
                ▼
┌─────────────────────────────┐      ┌─────────────────────────┐
│      API de Agenda (REST)   │◄────►│   Banco de Dados (H2)   │
└───────────────┬─────────────┘      └─────────────────────────┘
                │
                ▼
┌─────────────────────────────┐
│  Serviço de Autenticação   │
│          (JWT)             │
└─────────────────────────────┘
```

## Componentes da Arquitetura

### 1. Controllers (Camada de Apresentação)

Os controllers são responsáveis por receber as requisições HTTP, validar os dados de entrada, delegar o processamento para a camada de serviço e retornar as respostas apropriadas.

```java
@RestController
@RequestMapping("/api/v1/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    // Métodos para operações CRUD
}
```

Principais características:

- Definição de endpoints REST
- Mapeamento de verbos HTTP para operações CRUD
- Validação básica de requisições
- Gestão de códigos de status HTTP
- Documentação via anotações Swagger

### 2. Services (Camada de Negócio)

A camada de serviço implementa a lógica de negócio da aplicação, aplica regras de validação mais complexas e coordena operações entre múltiplas entidades ou repositórios.

```java
@Service
public class ContatoServiceImpl implements ContatoService {
    private final ContatoRepository contatoRepository;
    private final UsuarioService usuarioService;

    // Implementação dos métodos de negócio
}
```

Principais características:

- Implementação de regras de negócio
- Validação complexa
- Transações
- Mapeamento entre entidades e DTOs
- Gestão de exceções de domínio

### 3. Repositories (Camada de Persistência)

Os repositories são interfaces que estendem `JpaRepository` do Spring Data JPA, fornecendo operações básicas de CRUD para as entidades do domínio.

```java
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByUsuarioId(Long usuarioId);
    Optional<Contato> findByIdAndUsuarioId(Long id, Long usuarioId);
}
```

Principais características:

- Operações CRUD padrão
- Consultas derivadas
- Consultas personalizadas com JPQL ou SQL nativo
- Paginação e ordenação

### 4. Entities (Modelo de Domínio)

As entidades representam os objetos de domínio da aplicação e são mapeadas para tabelas no banco de dados.

```java
@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Outros campos e métodos
}
```

Principais características:

- Mapeamento objeto-relacional (ORM)
- Validação com Bean Validation
- Relacionamentos entre entidades
- Auditoria (criado_em, atualizado_em)

### 5. DTOs (Data Transfer Objects)

Os DTOs são objetos que transportam dados entre as camadas da aplicação, evitando a exposição direta das entidades e permitindo validações específicas para cada caso de uso.

```java
public class ContatoDTO {
    private Long id;

    @NotBlank
    private String nome;

    @Email
    private String email;

    // Outros campos e métodos
}
```

Principais características:

- Transferência de dados entre camadas
- Validação específica
- Proteção de dados sensíveis
- Versionamento de API

### 6. Segurança (JWT)

A segurança da API é implementada usando Spring Security e JWT para autenticação e autorização.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Configuração de segurança
}
```

Principais características:

- Autenticação baseada em JWT
- Autorização baseada em roles
- Proteção contra CSRF, XSS e outros ataques
- Gerenciamento de sessão

## Fluxo de Dados

### Autenticação

1. O cliente envia credenciais (email e senha) para `/api/v1/auth/login`
2. O `AuthController` valida as credenciais e gera um token JWT
3. O token JWT é retornado ao cliente
4. O cliente armazena o token e o inclui no cabeçalho `Authorization` de todas as requisições subsequentes

### Operações CRUD

1. O cliente envia uma requisição HTTP com o token JWT no cabeçalho `Authorization`
2. O `JwtAuthenticationFilter` valida o token e configura o contexto de segurança
3. A requisição é encaminhada para o controller apropriado
4. O controller valida os dados de entrada e delega o processamento para o service
5. O service aplica a lógica de negócio e interage com o repository
6. O repository executa operações no banco de dados
7. O resultado é convertido para DTO e retornado ao cliente

## Tratamento de Exceções

A aplicação implementa um tratamento global de exceções para fornecer respostas consistentes em caso de erro.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Tratamento da exceção
    }

    // Outros métodos de tratamento de exceções
}
```

## Validação

A aplicação utiliza Bean Validation para validar os dados de entrada:

```java
@PostMapping
public ResponseEntity<ContatoDTO> criarContato(@Valid @RequestBody ContatoDTO contatoDTO) {
    // Implementação
}
```

## Transações

As transações são gerenciadas pelo Spring utilizando a anotação `@Transactional`:

```java
@Service
public class ContatoServiceImpl implements ContatoService {
    @Transactional
    public ContatoDTO criar(ContatoDTO contatoDTO, Long usuarioId) {
        // Implementação
    }
}
```

## Logging

A aplicação utiliza SLF4J com Logback para logging:

```java
private static final Logger logger = LoggerFactory.getLogger(ContatoService.class);

public List<ContatoDTO> listarTodos(Long usuarioId) {
    logger.info("Listando contatos para o usuário {}", usuarioId);
    // Implementação
}
```

## Documentação da API

A documentação da API é gerada automaticamente usando SpringDoc OpenAPI (Swagger):

```java
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Agenda de Contatos")
                        .version("1.0")
                        .description("API para gerenciamento de contatos pessoais com autenticação JWT"));
    }
}
```

## Considerações sobre Performance

- **Paginação**: A API suporta paginação para evitar carregar grandes volumes de dados
- **Cache**: Resultados frequentemente acessados são cacheados para melhorar o desempenho
- **Lazy Loading**: Relacionamentos JPA são configurados com lazy loading para evitar carregamento desnecessário
- **Índices**: O banco de dados possui índices apropriados para melhorar o desempenho das consultas

## Considerações sobre Segurança

- **Autenticação JWT**: Tokens com tempo de expiração
- **Senha Criptografada**: Senhas são armazenadas utilizando BCrypt
- **Validação de Entrada**: Todos os dados de entrada são validados para prevenir injeções
- **CORS**: Configuração adequada para permitir apenas origens confiáveis
- **HTTPS**: Em ambiente de produção, todas as comunicações devem ser feitas via HTTPS

## Escalabilidade

A aplicação foi projetada para ser escalável horizontalmente:

- Código stateless que permite múltiplas instâncias
- Uso de banco de dados externo
- Configuração externalizada
- Cache distribuído (opcional)
