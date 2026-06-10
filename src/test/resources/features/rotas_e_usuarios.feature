# language: pt
Funcionalidade: Gerenciamento de Rotas e Usuários
  Como um usuário do sistema
  Eu quero poder me cadastrar, otimizar rotas e consultar meu histórico
  Para que eu possa gerenciar minhas viagens de forma eficiente

  Cenário: 1. Otimizar uma lista de endereços válida
    Dado que eu tenho uma lista de 3 endereços para visitar
    Quando eu solicito a otimização dessa rota
    Então o sistema deve retornar uma mensagem de sucesso confirmando o recebimento dos 3 endereços

  Cenário: 2. Cadastrar um novo usuário com sucesso
    Dado que eu informo os dados do usuário "joaosilva" com a senha "123456" e nome "João Silva"
    Quando eu salvo o usuário no sistema
    Então o sistema deve gerar um ID válido para esse usuário

  Cenário: 3. Impedir busca de histórico para um usuário inexistente
    Dado que eu procuro o histórico de rotas de um usuário inexistente "fantasma"
    Quando eu solicito as rotas salvas
    Então o sistema deve retornar uma lista vazia de rotas

  Cenário: 4. Salvar uma nova rota para um usuário existente
    Dado que existe um usuário "mariaclara" salvo no sistema
    E eu crio uma rota com origem "São Paulo" e paradas "Campinas"
    Quando eu vinculo a rota a esse usuário e salvo
    Então o histórico do usuário "mariaclara" deve conter 1 rota salva

  Cenário: 5. Listar o histórico de rotas de um usuário do mais recente para o mais antigo
    Dado que existe um usuário "carlos_motorista" salvo no sistema
    E esse usuário possui uma rota antiga de "Rio de Janeiro" e uma nova de "Curitiba"
    Quando eu busco o histórico de rotas ordenado
    Então a primeira rota da lista deve ser a de "Curitiba"
