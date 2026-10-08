/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jbferraz
 */
public class Conexao {

    // Os dados de acesso vêm de variáveis de ambiente, com os valores locais como padrão.
    private static final String URL = env("DB_URL", "jdbc:mysql://localhost:3306/carro");
    private static final String USER = env("DB_USER", "root");
    private static final String PASS = env("DB_PASSWORD", "");

    private static String env(String nome, String padrao) {
        String valor = System.getenv(nome);
        return valor != null ? valor : padrao;
    }

    /**
     * Abre uma nova conexão. Quem chama é responsável por fechá-la,
     * de preferência com try-with-resources.
     */
    public static Connection getConexao() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar!\n" + e.getMessage(), e);
        }
    }
}
