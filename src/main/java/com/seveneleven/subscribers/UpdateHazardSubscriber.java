package com.seveneleven.subscribers;

import com.seveneleven.DBAdaptor;
import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.IAPIServer;
import org.json.JSONObject;

/**
 * Created by mdl94 on 11/11/2015.
 */
public class UpdateHazardSubscriber implements ISubscriber
{
    private IAPIServer server;

    public UpdateHazardSubscriber(IAPIServer server)
    {
        this.server = server;
    }

    @Override
    public String getSubscription() {
        return Publisher.UPDATED_HAZARD;
    }

    @Override
    public void onData(Object data)
    {
        JSONObject jo = new JSONObject((String) data);
        DBAdaptor.deleteHazard(jo.get("id").toString());

        try
        {
            server.broadcast("client_update_zone", (String) data);
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}
