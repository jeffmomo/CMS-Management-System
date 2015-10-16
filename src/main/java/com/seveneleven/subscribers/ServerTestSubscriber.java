package com.seveneleven.subscribers;

import com.seveneleven.servers.APIServer;

/**
 * Created by mdl94 on 16/10/2015.
 */
public class ServerTestSubscriber implements ISubscriber
{
    @Override
    public String getSubscription() {
        return "server_test";
    }

    @Override
    public void onData(Object data)
    {
        double[] doubles = (double[]) data;

        try
        {
            APIServer srv = APIServer.getInstance();
            
            srv.broadcast("add_zone", String.format("{\"lat\": \"%f\", \"long\": \"%f\", \"radius\": \"%f\"}", doubles[0], doubles[1], doubles[2]));
            srv.broadcast("add_location", String.format("{\"lat\": \"%f\", \"long\": \"%f\"}", doubles[0], doubles[1]));
        }
        catch (Exception e)
        {

        }

    }
}
