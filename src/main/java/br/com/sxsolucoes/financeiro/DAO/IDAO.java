package br.com.sxsolucoes.financeiro.DAO;

import java.util.ArrayList;
import java.util.List;



public interface IDAO<Tipo> {
	

    /*Gravar*/
    public void gravar(Tipo xxxxx) throws Exception;
    
    /*apagar*/
    public void alterar(Tipo xxxxx) throws Exception;

    
    /*apagar*/
    public void apagar(Tipo xxxxx) throws Exception;
    
    /*listar Todos*/
    public List<Tipo> listarTodos() throws Exception;
    
    /*Burcar por Codigo/ID*/
    public Tipo buscar(Tipo xxxx) throws Exception;
    
    
}