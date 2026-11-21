# POO — CRUD na Nuvem (Java 17 + Railway MySQL)

**Aluno:** Bruno de Souza Ribeiro — **RA:** 0024186

Projeto acadêmico com menu no console e operações **CREATE, READ, UPDATE e DELETE** sobre produtos em banco de dados MySQL.

## Abrir no Eclipse
1. Instale Java 17 e Maven (ou use Maven integrado do Eclipse).
2. No Eclipse: **File > Import > Maven > Existing Maven Projects** e selecione a pasta `POOCRUDNUVEM` (que contém o `pom.xml`).
3. O Maven baixa automaticamente o driver JDBC MySQL.
4. Configure variáveis de ambiente na execução: **Run > Run Configurations > Java Application > Environment**.

| Variável | Exemplo |
|---|---|
| `DB_URL` | `jdbc:mysql://HOST_PUBLICO:PORTA_PUBLICA/railway` |
| `DB_USER` | `root` |
| `DB_PASSWORD` | senha do serviço MySQL no Railway |

Pode usar alternativamente `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER` e `MYSQLPASSWORD`. Para executar no computador, utilize **endereço TCP público** do Railway, e não o endereço privado interno.

**Nunca publique credenciais reais no GitHub.** O arquivo `.env.example` contém apenas valores ilustrativos.

## Executar
No diretório `POOCRUDNUVEM`:

```bash
mvn compile exec:java
```

Ou execute a classe `POOCRUDNUVEM.Produto` pelo Eclipse, após importar o projeto Maven e configurar as variáveis.

A tabela `produtos` é criada automaticamente na primeira execução. O menu permite cadastrar, listar, pesquisar, atualizar e excluir produtos.

## Status
O código está configurado para usar o Railway, mas uma conexão real depende de um MySQL ativo na conta, rede habilitada e credenciais configuradas localmente. Não há credenciais no repositório.
