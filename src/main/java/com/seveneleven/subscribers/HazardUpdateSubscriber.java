package com.seveneleven.subscribers;

import com.seveneleven.DBAdaptor;
import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.servers.IAPIServer;
import org.json.JSONObject;

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

        JSONObject jo = new JSONObject((String) data);
        DBAdaptor.addHazard((String)jo.get("description"), (String)jo.get("location"), (String)jo.get("status"));

        try
        {
            server.broadcast("client_create_zone", (String) data);
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}
