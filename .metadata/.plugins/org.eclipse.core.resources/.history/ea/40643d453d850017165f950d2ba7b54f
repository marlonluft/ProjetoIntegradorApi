package com.freetipscentral;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/helloWorld")
public class HelloWorldRestService {
 
 @GET
 @Produces(MediaType.TEXT_PLAIN)
 @Path("/nome/{name}")
 public String getCelcius(@Context HttpHeaders headers, @PathParam("name") String name){
 return "Hello "+name;
 }
 
 
 @POST
 @Path("/post")
 @Produces(MediaType.APPLICATION_JSON)
 @Consumes(MediaType.APPLICATION_JSON)
 public parametros post(parametros requestBody){       
          //return "{\"result\": \"Hello " + requestBody.nome + " world\"}";
	 parametros retorno = new parametros();
	 retorno.nome = requestBody.nome + " é o caralho, meu nome agora é zé pequeno";
	 
	 	return retorno;
     }

}