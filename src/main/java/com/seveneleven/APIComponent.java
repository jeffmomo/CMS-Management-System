package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;

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

    }
}
