package rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.xml.bind.JAXBElement;

@Path("/invoice")
public class InvoiceResource {
	private static Map<Integer, Invoice> invoices = new HashMap<Integer, Invoice>();

	@Context
	UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Invoice> getInvoices() {
		return invoices.values().stream().toList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{invoiceId}")
	public Response postInvoice(
		@PathParam("invoiceId") String invoiceId,
		JAXBElement<Invoice> invoiceElement
		) {
		final var invoice = invoiceElement.getValue();
		int parsedInvoiceId;
		try {
			parsedInvoiceId = Integer.valueOf(invoiceId);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		if (parsedInvoiceId != invoice.getId() || invoices.containsKey(invoice.getId())) {
			return Response.status(Response.Status.CONFLICT).build();
		}

		invoices.put(invoice.getId(), invoice);

		return Response.created(uriInfo.getAbsolutePath()).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Path("{invoiceId}")
	public Response putInvoice(
			@PathParam("invoiceId") String invoiceId,
			JAXBElement<Invoice> invoiceElement) {
		final var newInvoice = invoiceElement.getValue();

		int parsedInvoiceId;
		try {
			parsedInvoiceId = Integer.valueOf(invoiceId);
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		if (newInvoice.getId() != parsedInvoiceId)
			return Response.status(Response.Status.CONFLICT).build();

		if (!invoices.containsKey(newInvoice.getId()))
			return Response.status(Response.Status.NOT_FOUND).build();

		invoices.put(newInvoice.getId(), newInvoice);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@DELETE
	@Path("{invoiceId}")
	public Response deleteInvoice(
			@PathParam("invoiceId") String invoiceId) {

		int parsedInvoiceId;
		try {
			parsedInvoiceId = Integer.valueOf(invoiceId);
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		invoices.remove(parsedInvoiceId);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
