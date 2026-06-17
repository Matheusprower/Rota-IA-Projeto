# 🧑‍💻✅ Teste de Usabilidade - ROTA IA

---

## 🎯 Objetivo do teste

| Item | Preencher |
|---|---|
| 🎯 Objetivo | Avaliar a facilidade de uso do sistema ROTA IA, verificando se usuários conseguem criar, otimizar e consultar rotas sem dificuldades. |
| ✅ Hipóteses (o que esperamos) | Espera-se que os usuários consigam adicionar endereços, gerar rotas e acessar o histórico de forma intuitiva. |
| 📌 Escopo (telas/funcionalidades) | Tela principal de planejamento, inserção de endereços, botão otimizar, mapa, resumo da rota e histórico de rotas. |

---

# 👥 Participantes

| ID | 👤 Perfil | 🎓 Nível de experiência | 📍 Contexto | Observações |
|---|---|---|---|---|
| P1 - Douglas | Desenvolvedor/usuário técnico | Avançado | Presencial | Já possui experiência com sistemas web e ferramentas digitais |
| P2 - Raiane | Usuária comum | Médio | Presencial | Possui familiaridade com sites e aplicativos |
| P3 - Sueli | Usuária sem experiência técnica | Leigo | Presencial | Primeiro contato com sistemas de planejamento de rotas |

---

# ⚙️ Ambiente e preparação

| Item | Descrição |
|---|---|
| 💻 Dispositivo | Notebook |
| 🌐 Ambiente | Presencial |
| 🧪 Versão testada | Branch de testes do projeto |
| 🎥 Gravação | Não |
| 📎 Evidências | Prints da aplicação e anotações dos resultados |

---

# 🧩 Tarefas (roteiro do teste)

| 🏷️ Tarefa | 📝 Descrição | 🎯 Sucesso | ⏱️ Tempo alvo | ⭐ Prioridade |
|-|-|-|-|-|
| T1 | Acessar o sistema e identificar a tela de criação de rota | Usuário encontra os campos de endereço | 1 min | 🔥 Alta |
| T2 | Inserir origem, parada e destino | Rota criada corretamente | 2 min | 🔥 Alta |
| T3 | Executar otimização da rota | Sistema apresenta rota no mapa | 2 min | ⚠️ Média |
| T4 | Consultar histórico de rotas salvas | Usuário encontra histórico e dados da rota | 2 min | ⚠️ Média |
| T5 | Exportar histórico de rotas | Arquivo gerado corretamente | 1 min | 🟦 Baixa |

---

# 📊 Resultados

Legenda: ✅ Concluiu • ⚠️ Concluiu com ajuda • ❌ Não concluiu

| Participante | Tarefa | Status | Tempo | Erros | Dificuldades | Comentário | Evidência |
|-|-|-|-|-|-|-|-|
| Douglas | T1 | ✅ | 30s | 0 | Nenhuma | Interface clara | Print tela inicial |
| Douglas | T2 | ✅ | 1min | 0 | Nenhuma | Fluxo simples | Print rota |
| Douglas | T3 | ✅ | 40s | 0 | Nenhuma | Otimização rápida | Print mapa |
| Douglas | T4 | ✅ | 50s | 0 | Nenhuma | Histórico fácil de localizar | Print histórico |
| Douglas | T5 | ✅ | 30s | 0 | Nenhuma | Exportação funcionando | Arquivo exportado |

| Participante | Tarefa | Status | Tempo | Erros | Dificuldades | Comentário | Evidência |
|-|-|-|-|-|-|-|-|
| Raiane | T1 | ✅ | 1min | 0 | Demorou para localizar o botão histórico | Interface agradável | Print tela inicial |
| Raiane | T2 | ⚠️ | 3min | 1 | Confundiu parada e destino | Precisou entender o funcionamento | Print rota |
| Raiane | T3 | ✅ | 2min | 0 | Nenhuma | Resultado visual bom | Print mapa |
| Raiane | T4 | ✅ | 2min | 0 | Nenhuma | Encontrou histórico | Print histórico |
| Raiane | T5 | ⚠️ | 2min | 1 | Não sabia o formato do arquivo | Precisou orientação | Print exportação |

| Participante | Tarefa | Status | Tempo | Erros | Dificuldades | Comentário | Evidência |
|-|-|-|-|-|-|-|-|
| Sueli | T1 | ⚠️ | 2min | 1 | Não sabia onde começar | Pediu orientação inicial | Print tela |
| Sueli | T2 | ⚠️ | 5min | 2 | Confundiu campos de endereço | Precisou ajuda | Print formulário |
| Sueli | T3 | ✅ | 3min | 0 | Após explicação conseguiu | Gostou do mapa | Print mapa |
| Sueli | T4 | ⚠️ | 4min | 1 | Dificuldade para achar histórico | Precisou ajuda | Print histórico |
| Sueli | T5 | ❌ | - | 2 | Não entendeu exportação | Não concluiu sozinho | Anotação |

---

# ⭐ Questionário pós-teste

| Participante | Facilidade | Clareza | Velocidade | O que gostou | Melhoraria |
|-|-|-|-|-|-|
| Douglas | 9 | 9 | 9 | Otimização automática da rota | Melhoraria pequenos detalhes visuais |
| Raiane | 8 | 8 | 8 | Visualização no mapa | Explicaria melhor origem/destino |
| Sueli | 6 | 7 | 7 | Mapa mostrando o caminho | Botões mais explicativos |

---

# 🧠 Achados e melhorias

| Achado | Onde ocorreu | Impacto | Frequência | Prioridade | Ação recomendada |
|-|-|-|-|-|-|
| Usuários iniciantes tiveram dúvida sobre origem e destino | T2 | Médio | P2/P3 | ⚠️ Média | Melhorar textos dos campos |
| Histórico não ficou evidente para todos | T4 | Baixo | P2/P3 | 🟦 Baixa | Destacar botão histórico |
| Exportação pouco intuitiva | T5 | Médio | P2/P3 | ⚠️ Média | Adicionar explicação no botão |

---

# ✅ Conclusão e decisão

| Item | Resultado |
|-|-|
| ✅ Principais pontos positivos | Sistema apresentou fluxo funcional e mapa facilitou entendimento da rota |
| ⚠️ Principais dificuldades | Usuários menos experientes tiveram dúvidas nos campos e histórico |
| 🛠️ Top 3 melhorias | 1) Melhorar textos dos campos 2) Destacar histórico 3) Explicar exportação |
| 🟢 Go/No-Go | Go - Sistema funcional, porém com melhorias de usabilidade recomendadas |
| 📎 Evidências | docs/testes/evidencias/ |