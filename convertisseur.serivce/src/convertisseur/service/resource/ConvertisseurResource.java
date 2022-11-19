package convertisseur.service.resource;

import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import convertisseur.service.data.*; 
import convertisseur.service.service.*;

@Path("/money")
public class ConvertisseurResource {
	ConvertisseurService service = new ConvertisseurService();
	
	@Context 
	UriInfo uriInfo;
//	
	@POST 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.APPLICATION_XML) 
	public Response addMoney(Money s) {
		Money money = service.addMoney(s); 
		if(money == null) {
			return Response.status(Response.Status.BAD_REQUEST).build(); }
		URI uri = uriInfo.getRequestUri();
		String newUri = uri.getPath() + "/" + money.getfromCode();   //获取返回结果
		return Response.status(Response.Status.CREATED)
				.entity(money) 
				.contentLocation(uri.resolve(newUri)) 
				.build();
	}
//	
	@DELETE
	@Path("/{fromcode}") 
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteMoney(@PathParam("fromCode") String fromCode) {
		if(service.deleteMoney(fromCode) == false) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).build(); 
		}

@GET
@Path("/{fromcode}") 
@Produces(MediaType.APPLICATION_XML)
	public Response getMoney(@PathParam("fromCode") String fromCode) {
		Money money = service.getMoney(fromCode); 
		System.out.println(fromCode);
		if(money == null) {
			return Response.status(Response.Status.NOT_FOUND).build(); }
		System.out.println("获取成功");
		Link link = Link.fromUri(uriInfo.getRequestUri())
				.rel("self") .type("application/xml") .build();
		return Response.status(Response.Status.OK) 
				.entity(money)
				.links(link)
				.build(); 
		}
	}

//	}
