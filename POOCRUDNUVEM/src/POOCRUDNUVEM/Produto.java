package POOCRUDNUVEM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * CRUD acadêmico de produtos em MySQL (Railway).
 * Aluno: Bruno de Souza Ribeiro | RA: 0024186
 */
public class Produto {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("Aluno: Bruno de Souza Ribeiro");
        System.out.println("RA: 0024186");
        System.out.println("Atividade: POO - CRUD na Nuvem");
        System.out.println("====================================");

        try (Connection conexao = Conexao.abrir();
             Scanner teclado = new Scanner(System.in)) {

            criarTabela(conexao);
            System.out.println("Conexão MySQL estabelecida!");

            int opcao;
            do {
                System.out.println("\n1 - Cadastrar produto");
                System.out.println("2 - Listar produtos");
                System.out.println("3 - Buscar produto por ID");
                System.out.println("4 - Atualizar produto");
                System.out.println("5 - Excluir produto");
                System.out.println("0 - Sair");
                opcao = lerInteiro(teclado, "Opção: ");

                try {
                    switch (opcao) {
                        case 1 -> cadastrar(conexao, teclado);
                        case 2 -> listar(conexao);
                        case 3 -> buscar(conexao, teclado);
                        case 4 -> atualizar(conexao, teclado);
                        case 5 -> excluir(conexao, teclado);
                        case 0 -> System.out.println("Programa encerrado.");
                        default -> System.out.println("Opção inválida.");
                    }
                } catch (SQLException e) {
                    System.out.println("Erro na operação SQL: " + e.getMessage());
                }
            } while (opcao != 0);

        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            System.out.println("Configure DB_URL, DB_USER e DB_PASSWORD conforme README.");
        }
    }

    private static void criarTabela(Connection conexao) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS produtos ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "nome VARCHAR(120) NOT NULL, "
                + "preco DECIMAL(10,2) NOT NULL)";
        try (Statement comando = conexao.createStatement()) {
            comando.executeUpdate(sql);
        }
    }

    private static int lerInteiro(Scanner teclado, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException erro) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    private static String lerNome(Scanner teclado) {
        while (true) {
            System.out.print("Nome do produto: ");
            String nome = teclado.nextLine().trim();
            if (!nome.isEmpty() && nome.length() <= 120) return nome;
            System.out.println("Informe um nome de 1 a 120 caracteres.");
        }
    }

    private static BigDecimal lerPreco(Scanner teclado) {
        while (true) {
            System.out.print("Preço (ex.: 29,90): ");
            try {
                BigDecimal preco = new BigDecimal(teclado.nextLine().trim().replace(',', '.'));
                if (preco.signum() >= 0 && preco.scale() <= 2
                        && preco.compareTo(new BigDecimal("99999999.99")) <= 0) {
                    return preco;
                }
            } catch (NumberFormatException erro) {
                // Solicita novamente.
            }
            System.out.println("Preço inválido. Use no máximo duas casas decimais.");
        }
    }

    private static void cadastrar(Connection conexao, Scanner teclado) throws SQLException {
        String nome = lerNome(teclado);
        BigDecimal preco = lerPreco(teclado);
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, nome);
            comando.setBigDecimal(2, preco);
            comando.executeUpdate();
            System.out.println("Produto cadastrado!");
        }
    }

    private static void mostrar(ResultSet resultado) throws SQLException {
        System.out.printf("ID: %d | Nome: %s | Preço: R$ %s%n",
                resultado.getInt("id"),
                resultado.getString("nome"),
                resultado.getBigDecimal("preco").toPlainString());
    }

    private static void listar(Connection conexao) throws SQLException {
        String sql = "SELECT id, nome, preco FROM produtos ORDER BY id";
        try (PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {
            boolean encontrou = false;
            while (resultado.next()) {
                mostrar(resultado);
                encontrou = true;
            }
            if (!encontrou) System.out.println("Nenhum produto cadastrado.");
        }
    }

    private static void buscar(Connection conexao, Scanner teclado) throws SQLException {
        int id = lerInteiro(teclado, "ID do produto: ");
        String sql = "SELECT id, nome, preco FROM produtos WHERE id = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) mostrar(resultado);
                else System.out.println("Produto não encontrado.");
            }
        }
    }

    private static void atualizar(Connection conexao, Scanner teclado) throws SQLException {
        int id = lerInteiro(teclado, "ID do produto a atualizar: ");
        String nome = lerNome(teclado);
        BigDecimal preco = lerPreco(teclado);
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, nome);
            comando.setBigDecimal(2, preco);
            comando.setInt(3, id);
            int alterados = comando.executeUpdate();
            System.out.println(alterados > 0 ? "Produto atualizado!" : "Produto não encontrado.");
        }
    }

    private static void excluir(Connection conexao, Scanner teclado) throws SQLException {
        int id = lerInteiro(teclado, "ID do produto a excluir: ");
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, id);
            int excluidos = comando.executeUpdate();
            System.out.println(excluidos > 0 ? "Produto excluído!" : "Produto não encontrado.");
        }
    }
}
