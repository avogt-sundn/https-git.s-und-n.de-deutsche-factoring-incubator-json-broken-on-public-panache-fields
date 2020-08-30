package de.sn_invent.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tellmeyourname")
public class ExampleResource {

    @GET
    @Path("my")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyEntity entity) {
        return entity.name;
    }

    @GET
    @Path("mylombok")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyLombokEntity entity) {
        return entity.name;
    }

    @GET
    @Path("mypublicfieldslombok")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyPublicFieldsLombokEntity entity) {
        return entity.name;
    }
}