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
| 🥒 BDD | Não utilizado |
| 🤖 CI | Não utilizado |
| 🗄️ Banco/Dados | Banco de dados executado via Docker para armazenamento de usuários, autenticação e histórico de rotas |


---


# 🧪🧱 Estratégia de testes

| Tipo de teste | 🎯 Objetivo | 📌 Escopo | 🛠️ Ferramenta | 👤 Responsável | 📎 Saída/Evidência |
|---|---|---|---|---|---|
| ✅ Unitário | Validar regras isoladas do sistema | Serviços, validações e cálculos | JUnit | Equipe | Logs dos testes |
| 🌐 Sistema/E2E | Validar fluxo completo do usuário | Login, rota e histórico | Navegador | Equipe | Prints da aplicação |
| 🥒 BDD | Não utilizado | - | - | - | - |
| 🧑‍💻 Usabilidade | Validar interface e experiência | Tela do sistema | Teste manual | Equipe | Evidências visuais |


---


# 🧷🧭 Rastreabilidade

| ID Req | Requisito/Funcionalidade | Prioridade | Fonte | Testes | Status |
|---|---|---|---|---|---|
| RF-01 | Cadastro/login do usuário | Alta | Código | RT-01 | Executado |
| RF-02 | Criar e otimizar rotas | Alta | Código/API | RT-02 | Executado |
| RF-03 | Salvar histórico de rotas no banco | Alta | Banco/Docker | RT-03 | Executado |
| RF-04 | Validar campos obrigatórios | Média | Código | RT-04 | Executado |
| RF-05 | Tratar endereços inválidos | Média | Google Maps API | RT-05 | Executado |

---


# 🧾🧪 Casos de teste planejados

| ID | Tipo | Título | Pré-condição | Entrada | Resultado esperado | Prioridade | Automatizado |
|---|---|---|---|---|---|---|---|
| UT-01 | Unitário | Validar cálculo da rota | Sistema iniciado | Endereços válidos | Retornar rota correta | Alta | Sim |
| RT-01 | Manual | Otimização de rota | Mapa carregado | Origem + pontos + destino | Mostrar rota otimizada | Alta | Não |
| RT-02 | Manual | Histórico de rotas | Rota criada | Dados da rota | Salvar e exibir histórico | Alta | Não |
| RT-03 | Manual | Campos obrigatórios | Tela inicial | Dados incompletos | Bloquear operação | Média | Não |


---


# 🗃️ Dados de teste

| ID | 🧺 Conjunto | 📝 Descrição | 🧪 Como criar | 📍 Onde armazenar | 💡 Observações |
|---|---|---|---|---|---|
| DT-01 | Usuário válido | Conta cadastrada para acesso ao sistema | Criar usuário pelo cadastro | Banco Docker | Usado nos testes de login |
| DT-02 | Rotas válidas | Origem, paradas e destino existentes | Inserir rota pelo sistema | Banco Docker | Usado nos testes de histórico |
| DT-03 | Dados inválidos | Endereço inexistente ou campos vazios | Inserção manual | Aplicação | Usado nos testes de validação |