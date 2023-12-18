package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import br.com.agenda.factory.connectionfactory;
import br.com.agenda.model.Contato;

public class contatoDAO {
    public void save(Contato contato) throws SQLException {
        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = connectionfactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, (java.sql.Date) new Date(contato.getDataCadastro().getTime()));

            pstm.execute();

            System.out.println("Contato salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? " +
                "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            // Criar conexão com banco de dados
            conn = connectionfactory.createConnectionToMySQL();
            
            // Criar a classe para executar a query
            pstm = conn.prepareStatement(sql);
            
            // Adicionar os valores a serem atualizados
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade()); // Corrigido o índice do parâmetro
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            
            // Qual id do registro que deseja atualizar
            pstm.setInt(4, contato.getId());
            
            // Executar a query
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }   
    
    public void deleteByID(int id) {
    	String sql = "DELETE FROM contatos WHERE id = ?";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
    	try {
    		conn = connectionfactory.createConnectionToMySQL();
    		
    		pstm = conn.prepareStatement(sql);
    		pstm.setInt(3, id);
    		pstm.execute();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(pstm != null) {
    				pstm.close();
    			}
    			if(conn != null) {
    				conn.close();
    			}
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
       
    public List<Contato> getContatos() throws SQLException {
        String sql = "SELECT * FROM contatos";
        List<Contato> contatos = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = connectionfactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Contato contato = new Contato();
                contato.setId(rset.getInt("id"));
                contato.setNome(rset.getString("nome"));
                contato.setIdade(rset.getInt("idade"));
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return contatos;
    }
}