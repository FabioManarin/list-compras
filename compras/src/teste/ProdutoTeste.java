package teste;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import dao.ProdutoDao;
import entity.Produto;

public class ProdutoTeste {
	
	ProdutoDao produtoDao = new ProdutoDao();
	Produto produto = new Produto();
	
	@Ignore
	public void listar() {
		List<Produto> produtos;
		try {
			produtos = produtoDao.ListarTodos();
			for (Produto produto : produtos) {
				System.out.println(produto);
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void TestInserir () {
		try{
			produto.setNome("tenis");
			produto.setQuantidade(1);
			produto.setValorTotal(1.300);
			produto.setValorUnitario(1.300);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
			
		try {
			produtoDao.Inserir(produto);
		} catch (Exception e) {
			System.out.println("Message: " + e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void buscarProCodigo() {
		try {
			produto = produtoDao.PesquisarPorId(1);
			System.out.println(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		try {
			produtoDao.excluir(1);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	@Ignore
	public void editar() {
		try {
			produto.setNome("Mesa");
			produto.setQuantidade(3);
			produto.setValorTotal(30);
			produto.setValorUnitario(10);
			produtoDao.Editar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
