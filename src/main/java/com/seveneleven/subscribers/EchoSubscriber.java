package com.seveneleven.subscribers;

import com.seveneleven.servers.APIServer;
import com.seveneleven.publishers.Publisher;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class EchoSubscriber implements ISubscriber
{
    @Override
    public String getSubscription() {
        return Publisher.TEST_ECHO;
    }

    @Override
    public void onData(Object data) {
        System.err.println("Echoing...");
        try
        {
            APIServer.getInstance().broadcast("echo", data.toString());
        }
        catch (Exception e)
        {

        }
    }
}
