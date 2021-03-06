package com.seveneleven.subscribers;

import com.seveneleven.DBAdaptor;
import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.servers.IAPIServer;
import org.json.JSONObject;

/**
 * Created by mdl94 on 22/10/2015.
 */
public class NewHazardComponent implements ISubscriber
{
    private IAPIServer server;

    public NewHazardComponent(IAPIServer server)
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
        int id = DBAdaptor.addHazard((String)jo.get("description"), (String)jo.get("location"), (String)jo.get("status"));
        jo.put("hash", id);

        try
        {
            server.broadcast("client_create_zone", jo.toString());
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}
