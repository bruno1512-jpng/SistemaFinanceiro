package br.com.sxsolucoes.financeiro.teste;





import java.util.Date;

import br.com.sxsolucoes.financeiro.DAO.UsuarioDAO;
import br.com.sxsolucoes.financeiro.entity.Usuario;


public class TestaConexao {

	public static void main(String[] args) {
		
		Date data = new Date();
		System.out.println("Data Agora: "+data);
		
		Usuario usuario = new Usuario();
	
			
		usuario.setNome("SERGIO SALOMAO");
		usuario.setEmail("salomao.leite@hotmail.com");
		usuario.setLogin("salomao.leite");
		usuario.setSenha("210981");
		usuario.setPermirirAcesso("SIM");
		usuario.setTipo("Administrador");
		
		UsuarioDAO dao = new UsuarioDAO();

		try {
			dao.gravar(usuario);
			System.out.println("Dados Salvos");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
