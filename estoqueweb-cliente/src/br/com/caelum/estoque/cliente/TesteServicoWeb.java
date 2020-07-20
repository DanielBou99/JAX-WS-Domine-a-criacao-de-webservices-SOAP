package br.com.caelum.estoque.cliente;

public class TesteServicoWeb {

	public static void main(String[] args) {
		
		EstoqueWS cliente = new EstoqueWS_Service().getEstoqueWSImplPort(); // Objeto Proxy para estabelecer conexão
		
		
		
		Filtros filtros = new Filtros();
		Filtro filtro = new Filtro();
		filtro.setNome("Iphone");
		filtro.setTipo(TipoItem.CELULAR);
		filtros.getFiltro().add(filtro);
		
		ListaItens itens = cliente.todosOsItens(filtros);
		
		System.out.println(itens);
		
	}

}
