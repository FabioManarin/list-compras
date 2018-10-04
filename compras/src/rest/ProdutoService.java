package rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.ProdutoDao;
import entity.Produto;

@Path("/produtos")
public class ProdutoService {

	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	private ProdutoDao produtoDao;
	
	@PostConstruct
	private void init() {
		produtoDao = new ProdutoDao();
	}
	
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listarProduto() {
		List<Produto> lista = null;
		try {
			lista = produtoDao.ListarTodos();
		} catch (Exception e){
			e.printStackTrace();
			e.getMessage();
		}
		
		return lista;
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addProduto(Produto produto) {
		String msg = "";

		try {
			if (produto.getId() > 0)
				produtoDao.Editar(produto);
			else {
				produtoDao.Inserir(produto);
			}
			msg = "Produto add com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao add a Produto!";
			e.printStackTrace();
		}

		return msg;
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscarPorId(@PathParam("id") int idProduto) {
		Produto produto = null;
		try {
			produto = produtoDao.PesquisarPorId(idProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;
	}
	
	@PUT
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarProduto(Produto produto) {
		String msg = "";
		
		//System.out.println(produto.GetModelo());
		
		try {
			produtoDao.Editar(produto);
			
			msg = "Produto editado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar a Produto!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)//Invertida essas duas linhas, verificar
	@Produces(MediaType.TEXT_PLAIN)
	public String removeProduto(@PathParam("id") int idProduto) {
		String msg = "";
		
		try {
			produtoDao.excluir(idProduto);
			
			msg = "Produto removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover o Produto!";
			e.printStackTrace();
		}
		
		return msg;
	}
}
