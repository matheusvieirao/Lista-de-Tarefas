package hepta.tasklist.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hepta.tasklist.entity.Task;
import hepta.tasklist.persistence.TaskDAO;

@Path("/task")
public class TaskListService {
	
	private TaskDAO dao;
	
	public TaskListService() {
		dao = new TaskDAO();
	}
	
	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Task inserirPessoa(String nomeTarefa) throws Exception {
		Task objeto = new Task();
		
		if(nomeTarefa != "") {
			objeto.setFeito(false);
			objeto.setTarefa(nomeTarefa);
			objeto = dao.salvar(objeto);
		}
		return objeto;
	}
	
	@DELETE
	@Path("/deletar/{id}")
	public void delete(@PathParam("id")long id) throws Exception {
		dao.excluir(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar")
	public Response listarPessoas() throws Exception {
		
		List<Task> modelos = new ArrayList<Task>();
		
		modelos = dao.listar();
		
		GenericEntity<List<Task>> entity = new GenericEntity<List<Task>>(modelos) {
			
		};
		
		return Response.ok().entity(entity).build();
	}
}
