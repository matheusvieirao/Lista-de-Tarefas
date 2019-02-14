package hepta.tasklist.service;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hepta.tasklist.entity.Task;

@Path("/task")
public class TaskListService {

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
		
		objeto.setId(new Random().nextLong()); //bug: eventualmente dois ids iguais podem ser gerados
		objeto.setFeito(false);
		objeto.setTarefa(nomeTarefa);
		objeto.setEditando(false);
		
		return objeto;
	}
}
