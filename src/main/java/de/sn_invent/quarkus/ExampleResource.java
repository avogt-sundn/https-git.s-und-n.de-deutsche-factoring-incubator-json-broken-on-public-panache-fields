package de.sn_invent.quarkus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tellmeyourname")
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @POST
    @Path("MyEntity")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyEntity entity) {

        if (null == entity.name) {
            throw new IllegalArgumentException("name must not be null");
        }
        return entity.name;
    }

    @POST
    @Path("MyLombokEntity")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyLombokEntity entity) {

        if (null == entity.name) {
            throw new IllegalArgumentException("name must not be null");
        }
        return entity.name;
    }


    @POST
    @Path("MyPublicFieldsLombokEntity")
    @Produces(MediaType.TEXT_PLAIN)
    public String tellMeYourName(MyPublicFieldsLombokEntity entity) {

        if (null == entity.name) {
            throw new IllegalArgumentException("name must not be null");
        }
        return entity.name;
    }

    @POST
    @Path("testgetter")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyPublicFieldsLombokEntity checkGetter(MyPublicFieldsLombokEntity entity) {
        // accessing the field via getter produced by quarkus
        String readName = entity.name;
        return entity;
    }

}