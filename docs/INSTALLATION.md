# Guia de Instalação - API de Agenda de Contatos

Este documento fornece instruções detalhadas para instalar e configurar a API da Agenda de Contatos em diferentes ambientes.

## Pré-requisitos

- Java JDK 17 ou superior
- Maven 3.8+
- Um IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)
- Git

## Instalação em Ambiente de Desenvolvimento

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/agenda-api.git
cd agenda-api
```

### 2. Configure o ambiente

#### Configuração do Banco de Dados

Por padrão, a aplicação utiliza um banco de dados H2 em memória para desenvolvimento. Para utilizar outro banco de dados:

1. Adicione a dependência do driver JDBC no `pom.xml`
2. Configure as propriedades de conexão no arquivo `application-dev.properties`

Exemplo para MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/agenda?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=sua-senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

#### Configuração de Segurança

Para desenvolvimento, você pode utilizar a configuração padrão. Para personalizar:

1. Crie um arquivo `.env` na raiz do projeto (não comite este arquivo)
2. Adicione as variáveis de ambiente necessárias:

```
JWT_SECRET=sua-chave-secreta-muito-longa-para-desenvolvimento-pelo-menos-64-caracteres-1234567890
JWT_EXPIRATION=86400000
```

### 3. Compile e execute a aplicação

```bash
# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

A aplicação estará disponível em: http://localhost:8080

### 4. Acesse a documentação da API

Após iniciar a aplicação, a documentação Swagger estará disponível em:

```
http://localhost:8080/swagger-ui.html
```

## Instalação em Ambiente de Produção

### 1. Preparação do Servidor

Requisitos mínimos recomendados:

- 1 vCPU
- 2GB RAM
- 10GB de espaço em disco

Instale o Java JDK 17:

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# Verify installation
java -version
```

### 2. Construa o arquivo JAR

```bash
mvn clean package -DskipTests
```

O arquivo JAR será gerado na pasta `target/`.

### 3. Configuração do Ambiente de Produção

Crie um arquivo `application-prod.properties` com as configurações de produção:

```properties
# Datasource
spring.datasource.url=jdbc:mysql://seu-servidor-db:3306/agenda
spring.datasource.username=seu-usuario-producao
spring.datasource.password=sua-senha-producao
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging
logging.level.root=WARN
logging.level.com.agenda=INFO
logging.file.name=/var/log/agenda-api/application.log

# Server
server.port=8080

# Compressão de resposta
server.compression.enabled=true
server.compression.mime-types=application/json,application/xml,text/html,text/plain

# Cache
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=500,expireAfterAccess=600s
```

Configure as variáveis de ambiente necessárias no servidor:

```bash
export JWT_SECRET=sua-chave-segura-para-producao-muito-longa-pelo-menos-64-caracteres-1234567890
export JWT_EXPIRATION=86400000
```

### 4. Execute a aplicação

Transfira o arquivo JAR para o servidor e execute:

```bash
java -jar -Dspring.profiles.active=prod agenda-api.jar
```

### 5. Configuração do Serviço Systemd (Linux)

Para garantir que a aplicação inicie automaticamente e seja gerenciada como um serviço:

1. Crie um arquivo de serviço:

```bash
sudo nano /etc/systemd/system/agenda-api.service
```

2. Adicione a configuração:

```
[Unit]
Description=Agenda API
After=network.target

[Service]
User=seu-usuario
WorkingDirectory=/caminho/para/aplicacao
ExecStart=/usr/bin/java -jar -Dspring.profiles.active=prod agenda-api.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```

3. Habilite e inicie o serviço:

```bash
sudo systemctl daemon-reload
sudo systemctl enable agenda-api
sudo systemctl start agenda-api
```

4. Verifique o status:

```bash
sudo systemctl status agenda-api
```

## Configuração do Proxy Reverso (Nginx)

Para expor a API através de um domínio com HTTPS:

1. Instale o Nginx:

```bash
sudo apt update
sudo apt install nginx
```

2. Configure um site:

```bash
sudo nano /etc/nginx/sites-available/agenda-api
```

3. Adicione a configuração:

```
server {
    listen 80;
    server_name api.seudominio.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

4. Ative o site e reinicie o Nginx:

```bash
sudo ln -s /etc/nginx/sites-available/agenda-api /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl restart nginx
```

5. Configure HTTPS com Certbot:

```bash
sudo apt install certbot python3-certbot-nginx
sudo certbot --nginx -d api.seudominio.com
```

## Solução de Problemas

### Verificação de Logs

```bash
# Ver logs da aplicação
tail -f /var/log/agenda-api/application.log

# Ver logs do serviço
journalctl -u agenda-api.service -f
```

### Problemas Comuns

1. **Erro de conexão com o banco de dados**:

   - Verifique se o banco de dados está rodando
   - Verifique as credenciais e URL de conexão
   - Verifique permissões do usuário no banco de dados

2. **Erro de memória (OutOfMemoryError)**:

   - Ajuste as configurações de heap da JVM:
     ```bash
     java -Xms512m -Xmx1024m -jar agenda-api.jar
     ```

3. **Erro de porta em uso**:
   - Verifique se outro processo está usando a porta 8080:
     ```bash
     lsof -i :8080
     ```
   - Altere a porta na configuração:
     ```properties
     server.port=8081
     ```

## Atualização da Aplicação

Para atualizar para uma nova versão:

1. Faça backup do banco de dados e configurações
2. Pare o serviço: `sudo systemctl stop agenda-api`
3. Substitua o arquivo JAR pela nova versão
4. Inicie o serviço: `sudo systemctl start agenda-api`
5. Verifique os logs para confirmar que a inicialização foi bem-sucedida
