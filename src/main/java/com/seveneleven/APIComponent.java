package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class APIComponent extends Publisher
{
    public APIComponent(APIServer server, Manager manager)
    {
        super(manager, Publisher.TEST_WRITEOUT);

        server.registerAction("dank", (str) -> this.publish(str));

        server.registerAction("echo", (str) -> this.publish(Publisher.TEST_ECHO, str));

    }
}
