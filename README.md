# POO — CRUD na Nuvem

**Aluno:** Bruno de Souza Ribeiro  
**RA:** 0024186  
**Disciplina:** Programação Orientada a Objetos

## Projeto
Sistema de cadastro de produtos desenvolvido em **Java 17**, com banco de dados **MySQL** e conexão preparada para o **Railway**.

**Funcionalidades:** cadastrar, listar, buscar, atualizar e excluir produtos.

## Arquivos principais
- `Produto.java` — menu e operações de cadastro, consulta, atualização e exclusão.
- `Conexao.java` — conexão com o banco MySQL.
- `pom.xml` — dependência do driver JDBC MySQL.

## Executar
Importe a pasta `POOCRUDNUVEM` no Eclipse como **Existing Maven Projects** e utilize o Java 17.

Configure as variáveis de ambiente na execução:

```text
DB_URL=jdbc:mysql://HOST:PORTA/railway
DB_USER=root
DB_PASSWORD=SUA_SENHA
```

Ao executar pelo computador, use o endereço **público** e a porta TCP fornecidos pelo Railway. As credenciais verdadeiras devem ficar fora do GitHub.

Classe principal: `POOCRUDNUVEM.Produto`

Pelo terminal, na pasta que contém o `pom.xml`:

```bash
mvn compile exec:java
```

**Observação:** para realizar operações no banco, é necessário ter uma instância MySQL ativa e configurar as credenciais de acesso.
