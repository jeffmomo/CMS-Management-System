package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.publishers.TimedPublisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.subscribers.*;
import org.java_websocket.WebSocketImpl;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class IntegrationTester {

    public static void main(String[] args) throws Exception
    {
        Manager manager = new Manager();
        //WebSocketImpl.DEBUG = true;

        APIServer s;
        try
        {
            APIServer.initInstance(8887);
            s = APIServer.getInstance();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

        manager.subscribe(new WriteOutSubscriber());
        manager.subscribe(new EchoSubscriber());
        //manager.subscribe(new ServerTestSubscriber());
        manager.subscribe(new HazardUpdateSubscriber());
        manager.subscribe(new IncidentUpdateSubscriber());
        manager.subscribe(new SocialMediaSubscriber());

        Publisher con = new APIComponent(s, manager);
//        TimedPublisher tp = new TimedPublisher(manager, 500);

        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        manager.callSubscribers(SocialMediaSubscriber.SOCIAL_MEDIA_SUBSCRIBER, "Hello");
    }
}
