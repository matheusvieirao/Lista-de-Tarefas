package hepta.tasklist.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hepta.tasklist.entity.Task;

public class TaskDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public TaskDAO() {
		
	}
	
	public Task salvar(Task obj) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.persist(obj);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<Task> listar() throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		
		Query query = em.createQuery("from Task t ");
		query.setFirstResult(0);
		query.setMaxResults(50);
		
		return query.getResultList();
	}

	public void excluir(long id) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("delete from Task where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	public void atualizar(Task objeto) throws Exception {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		
		em.merge(objeto);
		
		em.getTransaction().commit();
		em.close();
	}

}
