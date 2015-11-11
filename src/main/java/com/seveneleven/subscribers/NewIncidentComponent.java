package com.seveneleven.subscribers;

import com.seveneleven.DBAdaptor;
import com.seveneleven.composer.CSMS;
import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.servers.IAPIServer;
import com.seveneleven.sms.SMSFactory;
import com.seveneleven.sms.SMSSenderInterface;
import org.json.JSONObject;

/**
 * Created by mdl94 on 22/10/2015.
 */
public class NewIncidentComponent implements ISubscriber
{
    private IAPIServer server;

    public NewIncidentComponent(IAPIServer server)
    {
        this.server = server;
    }

    @Override
    public String getSubscription() {
        return Publisher.ADDED_NEW_INCIDENT;
    }

    @Override
    public void onData(Object data)
    {
        // Parses the data, and adds it to database
        JSONObject jo = new JSONObject((String) data);
        int id = DBAdaptor.addIncident((String) jo.get("description"), (String) jo.get("location"), (String) jo.get("status"));
        jo.put("hash", id);

        // Sends SMS to all relevant agencies
        CSMS c = new CSMS("+6581117649", "New Hazard: " + jo.get("description") + " ### Status: " + jo.get("status"));
        final SMSSenderInterface SMSSender = SMSFactory.getSMSSender();
        SMSSender.sendSMS(c);

        try
        {
            // Broadcasts it to web via APIManager
            server.broadcast("client_create_marker", jo.toString());
        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}
