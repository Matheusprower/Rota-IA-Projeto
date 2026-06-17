<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
  <img src="https://img.shields.io/badge/Google_Maps-API-4285F4?style=for-the-badge&logo=google-maps&logoColor=white" alt="Google Maps"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License"/>
</p>

<h1 align="center">🚗 Rota IA — Sistema Inteligente de Otimização de Rotas</h1>

<p align="center">
  <b>Aplicação web para otimização logística e gestão de paradas, integrando Google Maps API com um backend robusto em Spring Boot.</b>
</p>

---

## 📋 Sumário

- [Visão Geral](#-visão-geral)
- [Funcionalidades](#-funcionalidades)
- [Arquitetura do Sistema](#-arquitetura-do-sistema)
- [Stack Tecnológica](#-stack-tecnológica)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Modelo de Dados](#-modelo-de-dados)
- [Endpoints da API](#-endpoints-da-api)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação e Execução](#-instalação-e-execução)
- [Execução com Docker](#-execução-com-docker)
- [Testes Automatizados](#-testes-automatizados)
- [Segurança](#-segurança)
- [Capturas de Tela](#-capturas-de-tela)
- [Equipe de Desenvolvimento](#-equipe-de-desenvolvimento)
- [Licença](#-licença)

---

## 🎯 Visão Geral

O **Rota IA** é um sistema inteligente desenvolvido para **otimização logística e gestão de paradas**. O objetivo principal é solucionar problemas de roteirização complexos, organizando pontos de embarque de forma estratégica para garantir a máxima eficiência operacional.

A aplicação permite que usuários autenticados insiram múltiplos endereços e, através da integração com a **API do Google Maps**, calcula automaticamente a rota mais eficiente (com reordenação otimizada de waypoints). Todo o histórico de rotas é persistido em banco de dados e pode ser exportado/importado via arquivos `.json`.

### Problema Resolvido

Em cenários logísticos reais, a ordem de visitação dos pontos impacta diretamente o custo operacional (combustível, tempo e desgaste). O Rota IA aplica o algoritmo de otimização do Google Directions API (`optimizeWaypoints: true`) para reordenar automaticamente as paradas intermediárias, entregando ao usuário o trajeto de menor custo.

---

## ✨ Funcionalidades

| Funcionalidade | Descrição |
|---|---|
| **Autenticação Segura** | Login e cadastro com senhas criptografadas via BCrypt |
| **Otimização de Trajetos** | Integração com Google Maps Directions API para cálculo da rota mais eficiente entre múltiplos pontos |
| **Dashboard SPA** | Interface Single Page Application — o mapa e os dados não são perdidos durante a navegação |
| **Histórico Inteligente** | Salvamento automático de rotas otimizadas no banco de dados, vinculadas ao usuário logado |
| **Exportação de Dados** | Download do histórico de rotas em formato `.json` |
| **Importação de Dados** | Upload de histórico de rotas a partir de arquivo `.json` de outra máquina |
| **Camada de Tráfego** | Visualização em tempo real das condições de tráfego sobre o mapa |
| **Multi-Paradas** | Adição dinâmica de pontos intermediários (waypoints) ilimitados |

---

## 🏗 Arquitetura do Sistema

A aplicação segue o padrão arquitetural **MVC (Model-View-Controller)** com Spring Boot, organizada em camadas bem definidas:

```
┌─────────────────────────────────────────────────────────────┐
│                      FRONTEND (Browser)                     │
│         HTML5 + CSS3 + JavaScript + Thymeleaf               │
│              Google Maps JavaScript API                     │
└──────────────────────┬──────────────────────────────────────┘
                       │ HTTP/REST
┌──────────────────────▼──────────────────────────────────────┐
│                    SPRING BOOT 3.2.4                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────┐  │
│  │  Controllers │  │   Services   │  │ Security (BCrypt)│  │
│  │  - Api       │  │  - Api       │  │ - Filter Chain   │  │
│  │  - Cadastro  │  │  - Usuario   │  │ - UserDetails    │  │
│  │  - Historico │  │  - CustomUD  │  │ - PasswordEncoder│  │
│  │  - Tela      │  │              │  │                  │  │
│  └──────┬───────┘  └──────┬───────┘  └──────────────────┘  │
│         │                 │                                  │
│  ┌──────▼─────────────────▼──────────────────────────────┐  │
│  │              Repositories (Spring Data JPA)            │  │
│  │   - RotaRepository   - UsuarioRepository               │  │
│  └──────────────────────┬────────────────────────────────┘  │
└─────────────────────────┼───────────────────────────────────┘
                          │ JDBC
┌─────────────────────────▼───────────────────────────────────┐
│              MySQL 8.0 (Docker Container)                    │
│         Tabelas: usuarios, rotas                             │
└─────────────────────────────────────────────────────────────┘
```

### Fluxo Principal

1. O usuário se autentica via formulário de login (Spring Security)
2. Após login, é redirecionado à tela do mapa (`/mapa`)
3. Insere endereços (origem + N paradas) nos campos dinâmicos
4. Clica em "Otimizar" → JavaScript envia requisição ao Google Directions API
5. O mapa renderiza a rota otimizada e exibe resumo (distância + tempo)
6. A rota é salva automaticamente via `POST /api/rotas/salvar` (backend)
7. O histórico pode ser consultado, exportado ou importado a qualquer momento

---

## 🛠 Stack Tecnológica

### Backend & Estrutura
| Tecnologia | Versão | Função |
|---|---|---|
| Java (OpenJDK) | 21 | Linguagem principal |
| Spring Boot | 3.2.4 | Framework principal |
| Spring Security | 6.x | Autenticação e autorização |
| Spring Data JPA | 3.x | Persistência de dados (ORM) |
| Spring WebFlux | 3.x | Cliente HTTP reativo (WebClient) |
| Thymeleaf | 3.x | Template engine (server-side rendering) |
| Lombok | — | Redução de boilerplate (annotations) |
| Maven | 3.9.6 | Gerenciamento de dependências e build |

### Frontend
| Tecnologia | Função |
|---|---|
| HTML5 | Estrutura das páginas |
| CSS3 | Estilização (login, mapa) |
| JavaScript (Vanilla) | Lógica do mapa, histórico, SPA |
| Google Maps JavaScript API | Renderização do mapa e cálculo de rotas |

### Banco de Dados & Infraestrutura
| Tecnologia | Versão | Função |
|---|---|---|
| MySQL | 8.0 | Banco de dados de produção |
| H2 Database | — | Banco em memória para testes |
| Docker & Docker Compose | — | Conteinerização do MySQL e da aplicação |

### Testes
| Tecnologia | Versão | Função |
|---|---|---|
| JUnit 5 | — | Framework de testes unitários |
| Mockito | — | Mocking para testes isolados |
| Cucumber | 7.15.0 | Testes BDD (Behavior-Driven Development) |

---

## 📁 Estrutura do Projeto

```
Rota-IA-Projeto/
├── Dockerfile                          # Build multi-stage da aplicação
├── docker-compose.yml                  # Orquestração: app + MySQL
├── pom.xml                             # Dependências Maven
├── mvnw / mvnw.cmd                     # Maven Wrapper
│
├── src/main/java/com/rota/app/
│   ├── AppApplication.java             # Classe principal (Spring Boot)
│   ├── IndexController.java            # Controller raiz
│   │
│   ├── config/
│   │   ├── SecurityConfig.java         # Configuração Spring Security
│   │   └── WebClientConfig.java        # Configuração WebClient
│   │
│   ├── controller/
│   │   ├── ApiController.java          # Endpoints REST (/teste, /otimizar)
│   │   ├── CadastroController.java     # Registro de novos usuários
│   │   ├── HistoricoController.java    # CRUD de rotas + Export/Import
│   │   └── TelaController.java         # Redirecionamento de telas
│   │
│   ├── model/
│   │   ├── Rota.java                   # Entidade JPA — Rota otimizada
│   │   └── Usuario.java                # Entidade JPA — Usuário do sistema
│   │
│   ├── repository/
│   │   ├── RotaRepository.java         # Spring Data JPA — Rotas
│   │   └── UsuarioRepository.java      # Spring Data JPA — Usuários
│   │
│   └── service/
│       ├── ApiService.java             # Serviço de integração com APIs
│       ├── CustomUserDetailsService.java # Autenticação via banco de dados
│       └── UsuarioService.java         # Lógica de negócio de usuários
│
├── src/main/resources/
│   ├── application.properties          # Configurações (datasource, JPA)
│   ├── static/
│   │   ├── css/
│   │   │   ├── login.css               # Estilos da tela de login
│   │   │   └── mapa.css                # Estilos da tela do mapa
│   │   ├── js/
│   │   │   └── script.js               # Scripts auxiliares
│   │   └── index.css                   # Estilos gerais
│   └── templates/
│       ├── login.html                  # Tela de login
│       ├── cadastro.html               # Tela de cadastro
│       ├── index.html                  # Página inicial
│       └── mapa.html                   # Dashboard principal (mapa + histórico)
│
├── src/test/java/com/rota/app/
│   ├── bdd/
│   │   ├── CucumberTestRunner.java     # Runner dos testes Cucumber
│   │   └── RotasEUsuariosSteps.java    # Step definitions BDD
│   ├── controller/
│   │   └── ApiControllerTest.java      # Testes do ApiController
│   ├── model/
│   │   ├── RotaTest.java               # Testes da entidade Rota
│   │   └── UsuarioTest.java            # Testes da entidade Usuario
│   ├── repository/
│   │   └── DatabaseAndHistoryTest.java # Testes de integração (H2)
│   └── service/
│       └── ApiServiceTest.java         # Testes do ApiService
│
└── src/test/resources/
    ├── application.properties          # Configurações de teste (H2)
    └── features/
        └── rotas_e_usuarios.feature    # Cenários BDD em Gherkin (PT-BR)
```

---

## 💾 Modelo de Dados

### Entidade `Usuario`

| Campo | Tipo | Restrições | Descrição |
|---|---|---|---|
| `id` | `Long` | PK, Auto-increment | Identificador único |
| `username` | `String` | UNIQUE, NOT NULL | Nome de usuário (login) |
| `password` | `String` | NOT NULL | Senha criptografada (BCrypt) |
| `nome` | `String` | — | Nome completo do usuário |

### Entidade `Rota`

| Campo | Tipo | Restrições | Descrição |
|---|---|---|---|
| `id` | `Long` | PK, Auto-increment | Identificador único |
| `origem` | `String` | — | Endereço de partida |
| `paradas` | `TEXT` | — | Paradas intermediárias (separadas por ➔) |
| `distanciaTotal` | `String` | — | Distância total calculada (ex: "45.3 km") |
| `tempoTotal` | `String` | — | Tempo estimado (ex: "38 min") |
| `dataCriacao` | `LocalDateTime` | — | Timestamp de criação (auto-preenchido) |
| `usuario_id` | `Long` | FK → `usuarios.id`, NOT NULL | Vínculo com o usuário |

### Relacionamento

```
Usuario (1) ────────── (*) Rota
         um usuário possui N rotas
```

---

## 🔌 Endpoints da API

### Páginas (Controllers MVC)

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/` | Redireciona para `/login` |
| `GET` | `/login` | Tela de autenticação |
| `GET` | `/cadastro` | Tela de registro de novo usuário |
| `POST` | `/cadastro` | Processa registro → redireciona a `/login?success` |
| `GET` | `/mapa` | Dashboard principal (requer autenticação) |

### API REST

| Método | Rota | Descrição | Autenticação |
|---|---|---|---|
| `GET` | `/api/rotas` | Lista rotas do usuário logado (ordem decrescente) | ✅ |
| `POST` | `/api/rotas/salvar` | Salva nova rota para o usuário logado | ✅ |
| `GET` | `/api/rotas/exportar` | Exporta histórico como arquivo `.json` | ✅ |
| `POST` | `/api/rotas/importar` | Importa histórico via upload de `.json` | ✅ |
| `GET` | `/teste` | Teste de conectividade com API externa | ✅ |
| `POST` | `/otimizar` | Recebe lista de endereços para otimização | ✅ |

---

## 📌 Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- [**Java 21+**](https://adoptium.net/) (OpenJDK ou Oracle JDK)
- [**Maven 3.9+**](https://maven.apache.org/download.cgi) (ou use o Maven Wrapper incluso: `./mvnw`)
- [**Docker Desktop**](https://www.docker.com/products/docker-desktop/) (para o banco MySQL)
- **Chave de API do Google Maps** (já configurada no projeto para desenvolvimento)

---

## 🚀 Instalação e Execução

### 1. Clonar o repositório

```bash
git clone https://github.com/Matheusprower/Rota-IA-Projeto.git
cd Rota-IA-Projeto
```

### 2. Subir o banco de dados MySQL (Docker)

```bash
docker-compose up -d mysql
```

> Isso criará um container MySQL 8 com o banco `db_mydatabase` na porta `3306`.

### 3. Compilar e executar a aplicação

```bash
# Com Maven instalado
mvn spring-boot:run

# Ou com o Maven Wrapper (sem Maven instalado)
./mvnw spring-boot:run
```

### 4. Acessar a aplicação

Abra o navegador e acesse:

```
http://localhost:8080
```

Você será redirecionado à tela de login. Crie uma conta na tela de cadastro e faça login para acessar o dashboard do mapa.

---

## 🐳 Execução com Docker

Para executar **toda a stack** (aplicação + banco) via Docker Compose:

```bash
# Build e execução de todos os serviços
docker-compose up --build

# Execução em segundo plano
docker-compose up --build -d
```

### Detalhes do Docker

**Dockerfile** (multi-stage build):
1. **Stage 1 (build):** Usa `maven:3.9.6-eclipse-temurin-21` para compilar o projeto
2. **Stage 2 (runtime):** Usa `eclipse-temurin:21-jre-alpine` para executar o JAR (~imagem leve)

**docker-compose.yml:**

| Serviço | Imagem | Porta | Descrição |
|---|---|---|---|
| `mysql` | `mysql:8` | `3306:3306` | Banco de dados |
| `app` | Build local | `8080:8080` | Aplicação Spring Boot |

### Variáveis de Ambiente

| Variável | Valor Padrão | Descrição |
|---|---|---|
| `MYSQL_USER` | `myuser` | Usuário do MySQL |
| `MYSQL_PASSWORD` | `password` | Senha do MySQL |
| `MYSQL_DATABASE` | `db_mydatabase` | Nome do banco |
| `MYSQL_ROOT_PASSWORD` | `root` | Senha root do MySQL |
| `SPRING_DATASOURCE_URL` | `jdbc:mysql://mysql:3306/db_mydatabase...` | URL JDBC |

---

## 🧪 Testes Automatizados

O projeto possui testes unitários, de integração e BDD:

### Executar todos os testes

```bash
mvn test
```

### Tipos de Testes

| Tipo | Arquivo | Descrição |
|---|---|---|
| **Unitário** | `RotaTest.java` | Testes da entidade Rota (getters/setters) |
| **Unitário** | `UsuarioTest.java` | Testes da entidade Usuario |
| **Unitário** | `ApiServiceTest.java` | Testes do serviço de API |
| **Unitário** | `ApiControllerTest.java` | Testes do controller de API |
| **Integração** | `DatabaseAndHistoryTest.java` | Testes com banco H2 em memória |
| **BDD** | `rotas_e_usuarios.feature` | Cenários Gherkin em português |

### Cenários BDD (Cucumber)

Os testes BDD estão escritos em **Gherkin (PT-BR)** e cobrem os seguintes cenários:

1. ✅ Otimizar uma lista de endereços válida
2. ✅ Cadastrar um novo usuário com sucesso
3. ✅ Impedir busca de histórico para usuário inexistente
4. ✅ Salvar uma nova rota para um usuário existente
5. ✅ Listar histórico ordenado do mais recente ao mais antigo

### Banco de Testes

Os testes utilizam **H2 Database** em memória, configurado em `src/test/resources/application.properties`, eliminando dependência do MySQL para execução dos testes.

---

## 🔐 Segurança

A aplicação utiliza **Spring Security 6** com as seguintes configurações:

- **Criptografia de senhas:** BCrypt (hash unidirecional)
- **Autenticação:** Baseada em formulário com `CustomUserDetailsService` (consulta ao banco)
- **Rotas públicas:** `/login`, `/cadastro`, `/css/**`, `/js/**`, `/images/**`
- **Rotas protegidas:** Todas as demais requerem autenticação
- **Pós-login:** Redirecionamento automático para `/mapa`
- **CSRF:** Desabilitado (API REST)

---

## 📸 Capturas de Tela

### Testes Automatizados
<img width="1313" height="674" alt="Execução de testes automatizados" src="https://github.com/user-attachments/assets/ba660534-885b-425a-98cf-4815967fc919" />

### Resultados de Testes
<img width="1917" height="982" alt="Resultados dos testes na IDE" src="https://github.com/Matheusprower/Rota-IA-Projeto/blob/main/imagens/image.png" />
<img width="1572" height="971" alt="Cobertura de testes" src="https://github.com/Matheusprower/Rota-IA-Projeto/blob/main/imagens/Code_bQyvxtLNSv.png" />

---

## 👥 Equipe de Desenvolvimento

### 🛠️ Responsabilidades

| Membro | Área | Descrição |
|---|---|---|
| **Aparício Virgínio** | 🖥️ Interface | Experiência do usuário e UI/UX |
| **Arthur Maestri** | 🗄️ Dados | Estruturação e integridade do banco de dados |
| **Danielly Silva** | 📊 Gestão | Controle de prazos e padrões de qualidade |
| **Laura Freitas** | 🎨 Design & Docs | Refino visual e documentação técnica |
| **Matheus Henrique** | ☕ Backend | Lógica central e arquitetura em Java |
| **Samuel Fellipe** | 🧪 QA | Validação de APIs e testes de funcionamento |

### Perfis

<table style="width: 100%; text-align: center;">
<tr>
<td align="center">
<a href="https://github.com/Apariicio">
<img src="https://github.com/Apariicio.png" width="100px;" alt="Foto Aparício"/>

<sub><b>Aparício Virgínio 42414535</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/ArthurMaes">
<img src="https://github.com/ArthurMaes.png" width="100px;" alt="Foto Arthur"/>

<sub><b>Arthur Maestri 423192582</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/daniellyteixeira872-droid">
<img src="https://github.com/daniellyteixeira872-droid.png" width="100px;" alt="Foto Danielly"/>

<sub><b>Danielly Silva 42415112</b></sub>
</a>
</td>
</tr>
<tr>
<td align="center">
<a href="https://github.com/lauryz0">
<img src="https://github.com/lauryz0.png" width="100px;" alt="Foto Laura"/>

<sub><b>Laura Freitas 42413265</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/Matheusprower">
<img src="https://github.com/Matheusprower.png" width="100px;" alt="Foto Matheus"/>

<sub><b>Matheus Henrique 42410613</b></sub>
</a>
</td>
<td align="center">
<a href="https://github.com/Samuca02">
<img src="https://github.com/Samuca02.png" width="100px;" alt="Foto Samuel"/>


<sub><b>Samuel Fellipe 42521948</b></sub>
</a>
</td>
</tr>
</table>

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.

---

<p align="center">
  <b>Desenvolvido com ☕ e dedicação pela equipe Rota IA</b>
</p>
