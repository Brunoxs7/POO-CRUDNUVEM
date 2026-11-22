# POO — CRUD na Nuvem

**Aluno:** Bruno de Souza Ribeiro  
**RA:** 0024186  
**Disciplina:** Programação Orientada a Objetos (POO)

## Sobre a atividade

Neste trabalho, desenvolvi um programa em Java para realizar o cadastro e o gerenciamento de produtos. A ideia foi colocar em prática o conteúdo estudado em sala e entender melhor como um sistema pode guardar e consultar informações em um banco de dados.

O projeto utiliza o MySQL e foi preparado para se conectar a um banco hospedado no Railway. A interação é feita pelo console, com um menu simples para escolher as operações disponíveis.

## O que o programa faz

O sistema permite:
- Cadastrar um produto, informando seu nome e preço;
- Consultar a lista de produtos cadastrados ou pesquisar pelo ID;
- Alterar os dados de um produto;
- Excluir um produto pelo ID.

Essas são as quatro operações básicas de um CRUD: **Create, Read, Update e Delete**. Os dados ficam na tabela `produtos`, que o próprio programa cria caso ainda não exista.

## Tecnologias utilizadas

- **Java 17:** desenvolvimento do programa;
- **MySQL:** armazenamento dos produtos;
- **Railway:** serviço previsto para hospedar o banco na nuvem;
- **JDBC:** comunicação entre o Java e o banco;
- **Maven:** gerenciamento da dependência do driver MySQL;
- **Eclipse:** ambiente de desenvolvimento.

## Como executar

Para abrir o projeto no Eclipse:

1. Baixe o repositório e extraia o arquivo ZIP.
2. Entre em **File > Import > Maven > Existing Maven Projects**.
3. Selecione a pasta `POOCRUDNUVEM`, onde está o arquivo `pom.xml`.
4. Aguarde o Maven carregar a dependência do MySQL.
5. Configure os dados de acesso ao banco nas variáveis de ambiente da execução.
6. Execute a classe `Produto.java`.

As variáveis usadas são:

| Variável | Informação |
|---|---|
| `DB_URL` | Endereço JDBC do MySQL |
| `DB_USER` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |

No Railway, também é possível usar `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER` e `MYSQLPASSWORD`. Para testar pelo Eclipse no próprio computador, é preciso usar o endereço e a porta públicos da conexão MySQL no Railway.

As senhas não foram incluídas no GitHub por segurança. O arquivo `.env.example` mostra apenas um exemplo de configuração.

Também é possível executar na pasta do projeto pelo terminal, com Maven instalado:

```bash
mvn compile exec:java
```

## Organização do código

A classe `Produto.java` contém o menu e as operações de cadastro, consulta, alteração e exclusão. A classe `Conexao.java` é responsável por abrir a conexão com o MySQL.

## Considerações finais

Com essa atividade, pude trabalhar na prática com comandos SQL e integração de um programa Java com banco de dados. A proposta também ajuda a compreender como um sistema pode ser preparado para utilizar informações armazenadas na nuvem.

**Observação:** o código já está preparado para usar o MySQL no Railway, mas a conexão real ainda precisa ser configurada e testada com as credenciais de um banco ativo.
