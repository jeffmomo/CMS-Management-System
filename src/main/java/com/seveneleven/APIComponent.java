package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.subscribers.HazardUpdateSubscriber;
import com.seveneleven.subscribers.IncidentUpdateSubscriber;
import org.json.JSONObject;

/**
 * Created by mdl94 on 12/10/2015.
 */


// This class is a subclass of publisher, and thus has all the publisher methods
public class APIComponent extends Publisher
{
    public APIComponent(APIServer server, Manager manager)
    {
        // Publishes on the TEST_WRITEOUT publication string
        super(manager, Publisher.TEST_WRITEOUT);


        // Registers the actions with the APIServer
        // This means that whenever message with the tags 'dank' or 'echo' is received from the server side,
        // The following functions will then be called on the message received
        server.registerAction("dank", (str) -> this.publish(str));
        server.registerAction("echo", (str) -> this.publish(Publisher.TEST_ECHO, str));

        server.registerAction("hit_test", (socketId) -> {
            server.sendTargeted(socketId, manager.hitTest());
            System.err.println("TEST HIT!");
        });

        server.registerAction("getall_incidents", (socketId) -> server.sendTargeted(socketId, manager.getAllIncidents()));
        server.registerAction("getall_hazards", (socketId) -> server.sendTargeted(socketId, manager.getAllHazards()));

        server.registerAction("srv_add_hazard", (msg) ->
        {
            //JSONObject dataObj = new JSONObject(msg);
            publish(new HazardUpdateSubscriber().getSubscription(), msg);

        });
        server.registerAction("srv_add_incident", (msg) ->
        {
            //JSONObject dataObj = new JSONObject(msg);
            publish(new IncidentUpdateSubscriber().getSubscription(), msg);

        });
    }
}
