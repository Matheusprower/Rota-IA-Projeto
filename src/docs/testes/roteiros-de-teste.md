## RT-01 - Otimização de rota

| Campo | Descrição |
|---|---|
| Prioridade | Alta |
| Objetivo | Validar cálculo e exibição da rota otimizada |
| Pré-condição | Sistema aberto, internet e API funcionando |
| Dados | Origem: Contagem/MG <br> Parada: Belo Horizonte/MG <br> Destino: Betim/MG |
| Passos | 1. Inserir origem <br> 2. Adicionar parada <br> 3. Inserir destino <br> 4. Clicar em otimizar |
| Resultado esperado | Rota exibida no mapa com distância e tempo |
| Resultado obtido | Rota exibida corretamente com dados calculados |
| Status | 🟢 Executado |
| Evidência | print-rt01.png |


## RT-02 - Salvamento e visualização do histórico de rotas

| Campo | Descrição |
|---|---|
| Prioridade | Alta |
| Objetivo | Validar se o sistema salva corretamente uma rota calculada e permite visualizar os dados no histórico |
| Pré-condição | Sistema aberto, API funcionando e rota válida criada |
| Dados | Origem: Contagem/MG <br> Parada: Belo Horizonte/MG <br> Destino: Betim/MG |
| Passos | 1. Inserir origem <br> 2. Adicionar ponto intermediário <br> 3. Informar destino final <br> 4. Clicar em "Otimizar" <br> 5. Aguardar cálculo da rota <br> 6. Abrir "Ver Meu Histórico de Rotas" |
| Resultado esperado | Sistema deve salvar a rota automaticamente e exibir no histórico contendo data, origem, paradas, distância e tempo estimado |
| Resultado obtido | Rota salva corretamente. Histórico exibiu data, origem, parada, distância e tempo calculado |
| Status | 🟢 Executado |
| Evidência | print-rt02.png |


## RT-03 - Validação de campos obrigatórios

| Campo | Descrição |
|---|---|
| Prioridade | Média |
| Objetivo | Validar se o sistema impede a criação de uma rota quando os campos obrigatórios não estão preenchidos corretamente |
| Pré-condição | Sistema aberto e mapa carregado corretamente |
| Dados | Origem: Contagem/MG <br> Destino: vazio |
| Passos | 1. Abrir o sistema ROTA IA <br> 2. Informar apenas o endereço de origem <br> 3. Deixar o destino vazio <br> 4. Clicar no botão "Otimizar" |
| Resultado esperado | Sistema deve bloquear o cálculo da rota e apresentar uma mensagem informando que é necessário preencher origem e destino |
| Resultado obtido | Sistema identificou que faltava um endereço e exibiu alerta solicitando pelo menis uma origem e um destino |
| Status | 🟢 Executado |
| Evidência | print-rt03.png |


## RT-04 - Tratamento de endereço inválido

| Campo | Descrição |
|---|---|
| Prioridade | Média |
| Objetivo | Validar se o sistema identifica endereços inválidos e impede a criação de uma rota incorreta |
| Pré-condição | Sistema aberto, mapa carregado e conexão com a API Google Maps funcionando |
| Dados | Origem: EndereçoInexistente999999 <br> Destino: Belo Horizonte/MG |
| Passos | 1. Abrir o sistema ROTA IA <br> 2. Informar um endereço inválido como origem <br> 3. Informar um destino válido <br> 4. Clicar no botão "Otimizar" |
| Resultado esperado | Sistema deve informar que não foi possível calcular a rota devido ao endereço inválido e não exibir um trajeto incorreto |
| Resultado obtido | Sistema retornou erro da API e exibiu mensagem informando falha no cálculo da rota |
| Status | 🟢 Executado |
| Evidência | print-rt04.png |


## RT-05 - Teste de limite de pontos na rota

| Campo | Descrição |
|---|---|
| Prioridade | Baixa |
| Objetivo | Validar o comportamento do sistema ao receber uma rota com grande quantidade de pontos intermediários |
| Pré-condição | Sistema aberto, mapa carregado e API Google Maps funcionando |
| Dados | Origem: Contagem/MG <br> Paradas: Belo Horizonte/MG, Betim/MG, Nova Lima/MG, Sabará/MG, Santa Luzia/MG, Ibirité/MG, Ribeirão das Neves/MG, Caeté/MG <br> Destino: Vespasiano/MG |
| Passos | 1. Abrir o sistema ROTA IA <br> 2. Adicionar vários campos de endereço utilizando o botão "Adicionar" <br> 3. Preencher todos os pontos com endereços válidos <br> 4. Informar o destino final <br> 5. Clicar em "Otimizar" |
| Resultado esperado | Sistema deve calcular a rota corretamente ou informar uma limitação caso a quantidade de pontos ultrapasse o permitido pela API |
| Resultado obtido | Sistema processou os pontos adicionados e gerou a rota otimizada corretamente |
| Status | 🟢 Executado |
| Evidência | print-rt05.png |