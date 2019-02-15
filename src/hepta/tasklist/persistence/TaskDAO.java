package hepta.tasklist.persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;

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

}
