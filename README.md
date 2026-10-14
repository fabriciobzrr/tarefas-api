# 📋 Tarefas API — Backend RESTful com OAuth2 Customizado

![Java](https://img.shields.io/badge/Java-26-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-OAuth2_Authorization_Server-blue?style=for-the-badge&logo=springsecurity)
![Build](https://img.shields.io/badge/Maven-3.9.16-red?style=for-the-badge&logo=apachemaven)

A **Tarefas API** é uma aplicação backend RESTful desenvolvida em **Java 26** e **Spring Boot 4.1.0** para gerenciamento de tarefas (CRUD com busca paginada e filtros). O projeto destaca-se por incorporar um **Servidor de Autorização OAuth2 próprio** utilizando a nova especificação do Spring Authorization Server com a implementação de um *Custom Password Grant*.

---

## 🚀 Tecnologias e Dependências

- **Linguagem:** Java 26
- **Framework Core:** Spring Boot 4.1.0
- **Segurança & Autenticação:**
  - Spring Security
  - Spring Authorization Server (com implementação customizada do protocolo *Password Grant*)
  - OAuth2 Resource Server & emissão de Tokens JWT com assinatura RSA
- **Persistência de Dados:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database em memória (`profile: test`)
- **Validação:** Jakarta Bean Validation
- **Utilitários:** Lombok, Spring Boot Actuator, Dotenv (`.env`)

---

## 🎯 Destaques Arquiteturais e Decisões Técnicas

- **Arquitetura em Camadas:** Organização clara e desacoplada em `controllers`, `services`, `repositories`, `entities` e `dtos`, utilizando injeção de dependências por construtor com Lombok (`@RequiredArgsConstructor`).
- **Servidor OAuth2 com Custom Password Grant:** Como o Spring Authorization Server não fornece por padrão a concessão via *password*, foi implementada uma estrutura customizada (composta por `Converter`, `Provider`, `Token` e `CustomUserAuthorities`) para habilitar o fluxo clássico de autenticação por usuário e senha.
- **Assinatura RSA de JWT:** O par de chaves RSA de 2048 bits é gerado em memória na inicialização, gravando as *claims* de `authorities` e `username` diretamente no payload do token.
- **Tratamento Global de Exceções:** Manipulador centralizado com `@ControllerAdvice` (`ResourceExceptionHandler`), padronizando erros de validação (`422 Unprocessable Entity`), recurso não encontrado (`404 Not Found`) e erros de banco (`400 Bad Request`).
- **Consultas Otimizadas:** Uso de JPQL com `LOWER(...) LIKE` para busca insensível a maiúsculas/minúsculas no `TarefaRepository`, além de consultas nativas otimizadas via *Projections* no `UsuarioRepository`.
- **Configuração Externa Resiliente:** Suporte ao carregamento de arquivo `.env` com valores *fallback* nativos em todos os parâmetros principais.

---

## 🛠️ Endpoints da API

### 🔑 Autenticação (OAuth2 Authorization Server)
| Método | Endpoint | Descrição | Parâmetros (Form URL Encoded) |
| :--- | :--- | :--- | :--- |
| `POST` | `/oauth2/token` | Emite o Token JWT | `grant_type=password`, `username`, `password`, `client_id`, `client_secret` |

### 📋 Gerenciamento de Tarefas (`/tarefas`)
> **Nota:** O servidor está configurado como Resource Server pronto para validação de JWT. A proteção por rotas específicas pode ser habilitada via anotações nas controllers.

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/tarefas` | Retorna lista paginada de tarefas (aceita parâmetro de busca `titulo`) |
| `GET` | `/tarefas/{id}` | Retorna os detalhes de uma tarefa específica por ID |
| `POST` | `/tarefas` | Cria uma nova tarefa (retorna `201 Created` com header `Location`) |
| `PUT` | `/tarefas/{id}` | Atualiza os dados de uma tarefa existente |
| `DELETE` | `/tarefas/{id}` | Remove uma tarefa existente (retorna `204 No Content`) |

---

## 🧪 Regras de Validação de Dados

- **Título:** Campo obrigatório (`@NotBlank`), tamanho mínimo de **4** e máximo de **80** caracteres.
- **Descrição:** Campo obrigatório (`@NotBlank`), tamanho mínimo de **10** caracteres.
- **Erros de Validação:** Emitem resposta HTTP `422 Unprocessable Entity` com a lista detalhada dos campos inválidos.

---

## ⚡ Como Executar o Projeto Localmente

### Pré-requisitos
- **Java 26** instalado e configurado no ambiente.
- **Git** instalado.

### Passo a passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/fabriciobzrr/tarefas-api.git
   cd tarefas-api
   ```

2. **Configurar Variáveis de Ambiente (Opcional):**
   Você pode criar um arquivo `.env` na raiz do projeto tomando como base o `.env.example`:
   ```properties
   ACTIVE_PROFILE=test
   CLIENT_ID=myclientid
   CLIENT_SECRET=myclientsecret
   JWT_DURATION=86400
   ```

3. **Executar a aplicação via Maven Wrapper:**
   - Linux / macOS / WSL:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Windows (PowerShell / CMD):
     ```cmd
     .\mvnw.cmd spring-boot:run
     ```

4. **Acessar o H2 Console:**
   Com o profile `test` ativo, o console do banco de dados estará acessível em:
   - **URL:** `http://localhost:8080/h2-console`
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User:** `sa`
   - **Password:** *(em branco)*

---

## 👨‍💻 Autor

Desenvolvido por **Fabricio Bezerra**.

- **GitHub:** [@fabriciobzrr](https://github.com/fabriciobzrr)
- **LinkedIn:** [Fabricio Bezerra](https://www.linkedin.com/in/fabriciobzrr)