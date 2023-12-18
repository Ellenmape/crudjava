package br.com.agenda.factory;

import java.sql.Connection;

import java.sql.DriverManager;

public class connectionfactory {
    // Nome do usuário no MySQL
    private static final String USERNAME = "root";
    // Senha do MySQL
    private static final String PASSWORD = "1234";
    // Caminho para acessar o banco de dados
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";


    // Conexão com MySQL
    public static Connection createConnectionToMySQL() throws Exception {
        // Faz com que a classe seja carregada pela JVM
    	Class.forName("com.mysql.cj.jdbc.Driver");
        // Cria a conexão com o banco de dados
    	Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);


        return connection;
    }

    public static void main(String[] args) throws Exception {
    	Connection con = createConnectionToMySQL();
            // Testar se a conexão não é nula
            if (con != null) {
                System.out.println("Conexão obtida com sucesso!");
                con.close();
            }
        }
}