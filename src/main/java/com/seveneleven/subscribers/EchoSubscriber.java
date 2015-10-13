package com.seveneleven.subscribers;

import com.seveneleven.servers.APIServer;
import com.seveneleven.publishers.Publisher;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class EchoSubscriber implements ISubscriber
{
    private APIServer _server;

    public EchoSubscriber(APIServer server)
    {
        _server = server;
    }


    @Override
    public String getSubscription() {
        return Publisher.TEST_ECHO;
    }

    @Override
    public void onData(Object data) {
        System.err.println("Echoing...");
        _server.broadcast("echo", data.toString());
    }
}
