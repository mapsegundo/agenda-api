# Guia de Contribuição - API de Agenda de Contatos

Obrigado pelo interesse em contribuir para a API da Agenda de Contatos! Este guia fornece informações sobre como você pode ajudar a melhorar este projeto.

## Código de Conduta

Por favor, leia e siga nosso Código de Conduta para manter um ambiente respeitoso e colaborativo para todos os contribuidores.

## Como Contribuir

### Reportando Bugs

Se você encontrou um bug, por favor abra uma issue seguindo estas orientações:

1. Use um título claro e descritivo
2. Descreva os passos para reproduzir o problema
3. Descreva o comportamento esperado e o comportamento atual
4. Inclua logs de erro ou capturas de tela, se possível
5. Mencione a versão do Java, Spring Boot e do sistema operacional

### Sugerindo Melhorias

Para sugerir melhorias:

1. Use um título claro e descritivo
2. Forneça uma descrição detalhada da sugestão
3. Explique por que esta melhoria seria útil
4. Inclua exemplos de como esta melhoria funcionaria, se aplicável

### Pull Requests

1. Faça fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nome-da-feature`)
3. Implemente suas mudanças
4. Adicione testes unitários e de integração para suas alterações
5. Certifique-se de que todos os testes passam (`mvn test`)
6. Atualize a documentação conforme necessário
7. Faça commit das suas alterações (`git commit -m 'Adicionar nova feature'`)
8. Envie para a branch (`git push origin feature/nome-da-feature`)
9. Abra um Pull Request

## Estilo de Código

Este projeto segue as seguintes convenções:

- Siga as convenções de código Java padrão
- Use camelCase para nomes de variáveis e métodos
- Use PascalCase para nomes de classes
- Adicione comentários JavaDoc para classes e métodos públicos
- Mantenha os métodos concisos e com responsabilidade única
- Siga os princípios SOLID

## Estrutura do Projeto

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

## Processo de Desenvolvimento

1. Escolha uma issue para trabalhar ou crie uma nova
2. Discuta a abordagem na issue antes de começar a trabalhar
3. Implemente a solução em sua branch
4. Escreva testes automatizados
5. Abra um Pull Request para revisão
6. Participe da discussão e faça as alterações necessárias
7. Aguarde aprovação e merge

## Recursos Adicionais

- [Documentação do Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Documentação do Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Documentação do Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Javadoc](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)

Obrigado por contribuir!
