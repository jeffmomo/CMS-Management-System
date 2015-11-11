package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import org.json.JSONObject;

/**
 * Created by mdl94 on 12/10/2015.
 */


// This class is a subclass of publisher, and thus has all the publisher methods
public class APIComponent extends Publisher
{
    public APIComponent(APIServer server, Manager manager)
    {
        // Publishes on the default publication string
        super(manager, Publisher.DEFAULT);

        // Respond to when web client requests getting all incident/hazards
        server.registerAction("srv_getall_incidents", (socketId) ->
        {
            JSONObject data = new JSONObject();
            data.put("apiType", "client_all_incidents");
            data.put("allIncidents", manager.getAllIncidents());

            server.sendTargeted(socketId, data.toString());
        });

        server.registerAction("srv_getall_hazards", (socketId) ->
        {
            JSONObject data = new JSONObject();
            data.put("apiType", "client_all_hazards");
            data.put("allHazards", manager.getAllHazards());

            server.sendTargeted(socketId, data.toString());
        });

        server.registerAction("srv_update_hazard", (msg) ->
        {
            publish(Publisher.UPDATED_HAZARD, msg);
        });
        server.registerAction("srv_update_incident", (msg) ->
        {
            publish(Publisher.UPDATED_INCIDENT, msg);
        });


        // Registers the action to publish new hazard, when messages with the tag "srv_add_hazard" is received
        server.registerAction("srv_add_hazard", (msg) ->
        {
            publish(Publisher.ADDED_NEW_HAZARD, msg);
        });
        server.registerAction("srv_add_incident", (msg) ->
        {
            publish(Publisher.ADDED_NEW_INCIDENT, msg);
        });
    }
}
