package com.seveneleven.subscribers;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.servers.IAPIServer;

/**
 * Created by mdl94 on 22/10/2015.
 */
public class HazardUpdateSubscriber implements ISubscriber
{
    private IAPIServer server;

    public HazardUpdateSubscriber(IAPIServer server)
    {
        this.server = server;
    }

    @Override
    public String getSubscription() {
        return Publisher.ADDED_NEW_HAZARD;
    }

    @Override
    public void onData(Object data) {
        try
        {server.broadcast("client_create_zone", (String) data);}
        catch(Exception e)
        {e.printStackTrace();}
    }
}
