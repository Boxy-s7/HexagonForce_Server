package de.jamilo.hexagon.server.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.logging.Logger;

import de.jamilo.hexagon.server.application.user.UserService;

@Path("/init")
public class InitResource {
    private static final Logger LOG = Logger.getLogger(InitResource.class.getName());

    private static int userCount = 0;
    @Inject
    UserService userService;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response init(@Context HttpHeaders headers) {
        var tokenCookie = headers.getCookies().get("user-token");

        if (tokenCookie != null) {
            String token = tokenCookie.getValue();
            /* logic */LOG.info("login token: " + token);
            String resp = """
            { "id": "YYY", "state": "login", "token: "XXX" }
            """.replace("XXX", token).replace("YYY", "known");
            return Response.ok(resp).build();
        }

        // Erstellt später einen neuen Spieler und liefert dessen Token zurück.
        String token = userService.createPlayer();
            String resp = """
            { "id": "user_YYY", "state": "login", "token: "XXX" }
            """.replace("XXX", token).replace("YYY", String.valueOf(++userCount));
        return Response.ok(resp)
                .cookie(new NewCookie.Builder("user-token")
                        .value(token)
                        .path("/")
                        .httpOnly(true)
                        .build())
                .build();
    }

    @POST
    @Path("/logout")
    public Response logout() {
        return Response.noContent()
                .cookie(new NewCookie.Builder("user-token")
                        .value("")
                        .path("/")
                        .maxAge(0)
                        .httpOnly(true)
                        .build())
                .build();
    }

    private NewCookie buildUserCookie(String token) {
    return new NewCookie.Builder("user-token")
            .value(token)
            .path("/")
            .maxAge(60 * 60 * 24 * 365)
            .httpOnly(true)
            .secure(true) // Produktion über HTTPS
            .sameSite(NewCookie.SameSite.LAX)
            .build();
}
}
