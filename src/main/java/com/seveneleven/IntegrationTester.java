package com.seveneleven;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.APIServer;
import com.seveneleven.subscribers.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

import java.sql.SQLException;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class IntegrationTester {

    public static void main(String[] args)
    {
        Manager manager = new Manager();

        APIServer s;
        try
        {
            // Initialises the instance of the APIServer
            APIServer.initInstance(8887);
            s = APIServer.getInstance();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }

        try
        {
            DBAdaptor.initialise();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        // Add the subscribers
        manager.subscribe(new HazardUpdateComponent(s));
        manager.subscribe(new IncidentUpdateComponent(s));
        manager.subscribe(new SocialMediaSubscriber());

        Publisher con = new APIComponent(s, manager);

        s.start();
        System.out.println("ChatServer started on port: " + s.getPort());

        // Uncomment this if you want to post to twitter
        // Login: +6593510320
        // Password: liur0008
//        manager.callSubscribers(SocialMediaSubscriber.SOCIAL_MEDIA_SUBSCRIBER, "Hello");



        ////// Start static content server

        Server server = new Server(80);

        // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
        // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
        ResourceHandler resource_handler = new ResourceHandler();
        // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
        // In this example it is the current directory but it can be configured to anything that the jvm has access to.
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        resource_handler.setResourceBase(".");

        // Add the ResourceHandler to the server.
        GzipHandler gzip = new GzipHandler();
        server.setHandler(gzip);
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        gzip.setHandler(handlers);

        // Start things up! By using the server.join() the server thread will join with the current thread.
        // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
        //server.start();
        //server.join();

        System.err.println("SERVER NOT STARTED!!!!!!");
    }
}
