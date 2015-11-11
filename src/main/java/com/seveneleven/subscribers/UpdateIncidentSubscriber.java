package com.seveneleven.subscribers;

import com.seveneleven.DBAdaptor;
import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.IAPIServer;
import org.json.JSONObject;

/**
 * Created by mdl94 on 11/11/2015.
 */
public class UpdateIncidentSubscriber implements ISubscriber
{

    private IAPIServer server;

    public UpdateIncidentSubscriber(IAPIServer server)
    {
        this.server = server;
    }

    @Override
    public String getSubscription() {
        return Publisher.UPDATED_INCIDENT;
    }

    @Override
    public void onData(Object data) {

        JSONObject jo = new JSONObject((String) data);
        DBAdaptor.deleteIncident(jo.get("id").toString());

        try
        {
            server.broadcast("client_update_marker", (String) data);
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}
