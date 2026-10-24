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

import java.util.UUID;
import java.util.logging.Logger;

import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.payload.InitPayload;
import de.jamilo.hexagon.server.domain.session.UserSession;
import de.jamilo.hexagon.server.persistence.service.UserService;

@Path("/init")
public class InitResource {
    private static final Logger LOG = Logger.getLogger(InitResource.class.getName());
    private static final String DEVICE_TOKEN = "device-token";

    private static int userCount = 0;
    @Inject
    UserSessionService userSessionService;

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response init(@Context HttpHeaders headers) {
        var tokenCookie = headers.getCookies().get(DEVICE_TOKEN);
        UserSession session;
        UUID deviceToken;
        if (tokenCookie != null && tokenCookie.getValue() != null) {
            deviceToken = UUID.fromString(tokenCookie.getValue());
        } else {
            deviceToken = userSessionService.createUserGetDeviceToken();
        }
        session = userSessionService.getOrCreateUserSessionForDeviceToken(deviceToken);
        if (session != null) {
            InitPayload payload = new InitPayload();
            payload.deviceToken = deviceToken;
            payload.sessionToken = session.getSessionId();
            payload.userId = session.getUserId();
            payload.userDisplay = session.getUserDisplay();
            return Response.ok(payload).build();
        }
        return Response.status(401).build();
    }

    @POST
    @Path("/logout")
    public Response logout() {
        return Response.noContent()
                .cookie(new NewCookie.Builder("device-token")
                        .value("")
                        .path("/")
                        .maxAge(0)
                        .httpOnly(true)
                        .build())
                .build();
    }
}
