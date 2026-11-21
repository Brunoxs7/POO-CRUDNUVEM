package POOCRUDNUVEM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Conexão JDBC com MySQL local ou hospedado no Railway. */
public final class Conexao {
    private Conexao() { }

    public static Connection abrir() throws SQLException {
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        // Alternativa: variáveis padrão do serviço MySQL no Railway.
        if (url == null || url.isBlank()) {
            String host = System.getenv("MYSQLHOST");
            String porta = System.getenv("MYSQLPORT");
            String banco = System.getenv("MYSQLDATABASE");
            if (host != null && porta != null && banco != null) {
                url = "jdbc:mysql://" + host + ":" + porta + "/" + banco;
            }
        }
        if (usuario == null || usuario.isBlank()) usuario = System.getenv("MYSQLUSER");
        if (senha == null) senha = System.getenv("MYSQLPASSWORD");

        if (url == null || url.isBlank() || usuario == null || usuario.isBlank() || senha == null) {
            throw new SQLException("Configure DB_URL, DB_USER e DB_PASSWORD (ou MYSQLHOST, MYSQLPORT, MYSQLDATABASE, MYSQLUSER e MYSQLPASSWORD).");
        }
        return DriverManager.getConnection(url, usuario, senha);
    }
}
