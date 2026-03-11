package com.semantyca.jesoos;

import com.semantyca.jesoos.rest.CommandResource;
import io.vertx.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import com.semantyca.jesoos.rest.DebugResource;

@ApplicationScoped
public class JesoosApplication {

    @Inject
    DebugResource debugResource;

    @Inject
    CommandResource commandResource;

    void setupRoutes(@Observes Router router) {
        commandResource.setupRoutes(router);
        debugResource.setupRoutes(router);
    }
}
