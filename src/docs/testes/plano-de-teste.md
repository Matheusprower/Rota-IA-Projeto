# 🧪 Plano de Teste - ROTA IA


# 🆔📖 Identificação e contexto

| Campo | Preencher |
|---|---|
| 🧩 Nome do projeto | ROTA IA |
| 📝 Objetivo do sistema (resumo) | Sistema web para criação e otimização de rotas. O usuário informa origem, pontos intermediários e destino final, e o sistema calcula o melhor trajeto utilizando API de mapas. |
| 🎯 Público-alvo | Usuários que precisam planejar deslocamentos, entregas ou trajetos com múltiplos pontos. |
| 💻 Plataforma/Tipo | Aplicação Web > link web: http://rota-ia.freeddns.org:8080/cadastro |
| 🔗 Repositório | Link do GitHub: https://github.com/Matheusprower/Rota-IA-Projeto.git|
| 👥 Time/Grupo | Aparício Amaral - 42414535 / Arthur Maestri - 423192582 / Danielly Teixeira - 42415112 / Laura Fagundes - 42413265 / Matheus Santos - 42420613 / Samuel Batista - 42521948 |


---


# 🎯🧪 Objetivo do teste

| Item | Descrição |
|---|---|
| ✅ Objetivo geral | Garantir que as funcionalidades principais do ROTA IA funcionem corretamente, validando cálculo de rotas, otimização, histórico e tratamento de erros. |
| 📊 Metas de cobertura | Cobrir funcionalidades principais, validações de entrada, integração com API externa e armazenamento do histórico. |


---


# 📦📌 Escopo

| Categoria | ✅ Em escopo | 🚫 Fora de escopo |
|---|---|---|
| 🧩 Funcionalidades | Login, criação de rotas, otimização, histórico de rotas | Funcionalidades futuras |
| 🧠 Regras de negócio | Usuário autenticado, salvar rotas vinculadas ao usuário, validação de dados | Regras não implementadas |
| 🔌 Integrações | Google Maps API, Backend, Banco via Docker | Serviços externos não utilizados |
| 🗃️ Dados | Cadastro de usuário, armazenamento e consulta do histórico de rotas | Migração de dados externos |
| 🧑‍💻 Não-funcionais | Usabilidade, desempenho, persistência dos dados | Testes avançados de segurança |


---


# 🧰🖥️ Ambiente e ferramentas

| Item | Especificação |
|---|---|
| 🖥️ SO | Windows |
| ☕ Linguagem/Runtime | Java + JavaScript + HTML + CSS |
| 🧑‍💻 IDE | Visual Studio Code |
| 🧱 Build | Maven + Spring Boot |
| ✅ Framework de testes unitários | JUnit |
| 🥒 BDD (se houver) | Cucumber 7.15.0 utilizando Gherkin |
| 🤖 CI (se houver) | Não utilizado |
| 🗄️ Banco/Dados | Banco principal da aplicação via Docker + Banco H2 para testes automatizados |


---


# 🧪🧱 Estratégia de testes

| Tipo de teste | 🎯 Objetivo | 📌 Escopo | 🛠️ Ferramenta | 👤 Responsável | 📎 Saída/Evidência |
|---|---|---|---|---|---|
| ✅ Unitário | Validar componentes isolados e regras do sistema | Serviços e validações | JUnit | Equipe | Relatório dos testes |
| 🌐 Sistema/End-to-End | Validar fluxo completo da aplicação | Login, rotas e histórico | Navegador | Equipe | Prints da aplicação |
| 🥒 BDD | Validar comportamento do sistema através de cenários de usuário | Cadastro, otimização e histórico | Cucumber + Gherkin | Equipe | Relatório Cucumber |
| 🧑‍💻 Usabilidade | Avaliar facilidade de uso da interface | Telas do sistema | Teste manual | Equipe | Evidências visuais |

---


# 🧷🧭 Rastreabilidade

| ID Req | Requisito/Funcionalidade | Prioridade | Fonte | IDs de testes | Status |
|---|---|---|---|---|---|
| RF-01 | Otimizar lista de endereços | Alta | Código/API | BDD-01 / RT-01 | 🟢 Executado |
| RF-02 | Cadastro de usuário | Alta | UsuárioRepository | BDD-02 | 🟢 Executado |
| RF-03 | Consultar histórico de rotas | Alta | RotaRepository | BDD-03 / RT-02 | 🟢 Executado |
| RF-04 | Salvar rota vinculada ao usuário | Alta | Banco de dados | BDD-04 | 🟢 Executado |
| RF-05 | Ordenar histórico por data | Média | Banco de dados | BDD-05 | 🟢 Executado |
| RF-06 | Validar entradas inválidas | Média | Código | RT-03 / RT-04 | 🟢 Executado |

---


# 🧾🧪 Casos de teste planejados

| ID | Tipo | Título | Pré-condição | Entrada | Resultado esperado | Prioridade | Automatizado? |
|---|---|---|---|---|---|---|---|
| BDD-01 | 🥒 BDD | Otimizar rota com endereços válidos | Sistema iniciado | 3 endereços | Retornar sucesso na otimização | Alta | Sim |
| BDD-02 | 🥒 BDD | Cadastrar usuário | Aplicação conectada ao banco | Nome e senha | Usuário recebe ID | Alta | Sim |
| BDD-03 | 🥒 BDD | Buscar histórico inexistente | Usuário não cadastrado | Username inválido | Retornar lista vazia | Média | Sim |
| BDD-04 | 🥒 BDD | Salvar rota do usuário | Usuário existente | Origem + parada | Histórico atualizado | Alta | Sim |
| BDD-05 | 🥒 BDD | Ordenar histórico | Usuário possui rotas | Rotas com datas diferentes | Mostrar mais recente primeiro | Média | Sim |
| RT-01 | 📝 Manual | Otimização visual da rota | Mapa carregado | Endereços válidos | Rota aparece no mapa | Alta | Não |


---


# 🗃️ Dados de teste

| ID | 🧺 Conjunto | 📝 Descrição | 🧪 Como criar | 📍 Onde armazenar | 💡 Observações |
|---|---|---|---|---|---|
| DT-01 | Usuário teste | Usuário para cenários BDD | Criado pelo Cucumber | Banco H2 | Usado nos testes automatizados |
| DT-02 | Rotas teste | Rotas vinculadas ao usuário | Criadas pelos Steps | Banco H2 | Usado no histórico |
| DT-03 | Endereços | Lista de locais para otimização | Inseridos no cenário Gherkin | Teste automatizado | Usado no cálculo de rota |