package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	
	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName = "TodosOsItens")
	@WebResult(name = "itens")
	public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
		List<Filtro> lista = filtros.getLista();
		
		return dao.todosItens(lista);
	}
	
	@WebMethod(operationName = "CadastrarItem")
	@WebResult(name = "item")
	public Item cadastrarItem(
			@WebParam(name="tokenUsuario", header = true) TokenUsuario token, 
			@WebParam(name="item") Item item) throws AutorizacaoException {
		
		boolean valido = new TokenDao().ehValido(token);
		
		if (!valido) {
			throw new AutorizacaoException("Autorização falhou");
		}
		
		new ItemValidador(item).validate();
		
		this.dao.cadastrar(item);
		return item;
	}
	
}
