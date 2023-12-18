package br.com.agenda.aplicacao;

import java.sql.SQLException;
import java.util.Date;
import br.com.agenda.dao.contatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) throws SQLException {
	    contatoDAO contatoDao = new contatoDAO();
	    
	    Contato contato = new Contato();
	    contato.setNome("Lucas");
	    contato.setIdade(28);
	    contato.setDataCadastro(new Date());
	    
		try { contatoDao.save(contato); } catch (SQLException e) {
		 e.printStackTrace(); // ou trate de acordo com a necessidade do seu aplicativo 
		 }
	    
	    //Atualizar contato
	    Contato c1 = new Contato();
        c1.setNome("Lucas de Souza");
        c1.setIdade(29);
        c1.setDataCadastro(new Date());
        c1.setId(2);
	    
	    contatoDao.update(c1);
        
        //Deletar contato pelo número de ID
        
        contatoDao.deleteByID(3);
		
	    for (Contato c : contatoDao.getContatos()) {
            System.out.println("Contato: " + c.getNome());
		}
	    
	}

}
