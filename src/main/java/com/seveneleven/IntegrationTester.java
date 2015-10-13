package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.subscribers.WriteOutSubscriber;
import com.seveneleven.subscribers.EchoSubscriber;
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
            s = new APIServer(8887);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

        manager.subscribe(new WriteOutSubscriber());
        manager.subscribe(new EchoSubscriber(s));

        Publisher con = new APIComponent(s, manager);

        s.start();
        System.out.println( "ChatServer started on port: " + s.getPort() );



    }
}
