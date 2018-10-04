package dao;

import java.util.List;

import javax.persistence.EntityManager;

import bean.ResourceBean;
import entity.Produto;

public class ProdutoDao {

	public void Inserir(Produto produto)throws Exception {
		EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        }finally {
            em.close();
        }
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> ListarTodos() throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();
        List<Produto> produtos = null;

        try {
        	produtos = em.createQuery("from Produto").getResultList();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            em.close();
        }
        return produtos;
    }
	
	public void Editar(Produto produto) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }
	
	public void excluir(int id) throws Exception {
        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, id);
            em.remove(produto);
            em.getTransaction().commit();
        } catch (Exception e)  {
            em.getTransaction().rollback();

            throw new Exception(e);
        } finally {
            em.close();
        }
    }
	
	public Produto PesquisarPorId(int id) throws Exception {
    	Produto produto;

        EntityManager em = ResourceBean.getEntityManagerFactory().createEntityManager();

        try {
        	produto = em.find(Produto.class, id);
        } finally {
            em.close();
        }
        return produto;
    }
}
