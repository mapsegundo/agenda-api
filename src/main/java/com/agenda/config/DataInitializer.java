package com.agenda.config;

import com.agenda.domain.Contato;
import com.agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ContatoService contatoService;

    @Override
    public void run(String... args) {
        // Criando alguns contatos de exemplo
        Contato contato1 = new Contato();
        contato1.setNome("João Silva");
        contato1.setTelefone("(11) 98765-4321");
        contato1.setEmail("joao.silva@email.com");
        contato1.setEndereco("Rua das Flores, 123 - São Paulo");

        Contato contato2 = new Contato();
        contato2.setNome("Maria Santos");
        contato2.setTelefone("(11) 97654-3210");
        contato2.setEmail("maria.santos@email.com");
        contato2.setEndereco("Av. Paulista, 1000 - São Paulo");

        Contato contato3 = new Contato();
        contato3.setNome("Pedro Oliveira");
        contato3.setTelefone("(11) 96543-2109");
        contato3.setEmail("pedro.oliveira@email.com");
        contato3.setEndereco("Rua Augusta, 500 - São Paulo");

        // Salvando os contatos no banco de dados
        contatoService.salvar(contato1);
        contatoService.salvar(contato2);
        contatoService.salvar(contato3);
    }
} 