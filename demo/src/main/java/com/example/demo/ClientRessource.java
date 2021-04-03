package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("client")

public class ClientRessource {
		@Autowired
		private ClientRepository ClientRepository;
		@Autowired
		private ProduitRepository ProduitRepository;

		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Client createClient(Client c) {
			return ClientRepository.save(c);
		}

		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Client> getAllClient() {
			List<Client> clients = new ArrayList<>();
			ClientRepository.findAll().forEach(clients::add);
			return clients;
		}

		@PUT
		@Path("{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Client updateTotalyClient(@PathParam("id") Long id, Client c) {
			c.setId(id);
			return ClientRepository.save(c);
		}

		@DELETE
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteClient(@PathParam("id") Long id) {
			if (ClientRepository.findById(id).isPresent()) {
				ClientRepository.deleteById(id);
			}
			return Response.noContent().build();
		}

		@GET
		@Path("{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getclientById(@PathParam("id") Long id) {
			Optional<Client> c = ClientRepository.findById(id);
			if (c.isPresent()) {
				return Response.ok(c.get()).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		}

		@PATCH
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("{id}")
		// PATCH /personnes/{id}
		public Response updateAge(@PathParam("id") Long id, Client c) {
			int age = c.getAge();
			Optional<Client> optional = ClientRepository.findById(id);

			if (optional.isPresent()) {
				Client cBDD = optional.get();
				cBDD.setAge(age);
				ClientRepository.save(cBDD);
				return Response.ok(cBDD).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		}

		@GET
		@Path("{id}/produits")
		@Produces(MediaType.APPLICATION_JSON)
		// GET /personnes/{id}/livres
		public List<Produit> listerProduit(@PathParam("id") Long id) {
			return ClientRepository.findById(id).get().getProduit();
		}

}
		
