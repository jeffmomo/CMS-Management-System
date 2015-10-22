package com.seveneleven.servers;

/**
 * Created by mdl94 on 12/10/2015.
 */
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.function.Consumer;


import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class APIServer extends WebSocketServer implements IAPIServer {

    private final Hashtable<SocketTag, WebSocket> _connections = new Hashtable<>();
    private final Hashtable<String, Consumer<String>> _registry = new Hashtable<>();
    private static APIServer _instance;

    private APIServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }
    private APIServer(InetSocketAddress address) {
        super(address);
    }


    /**
     * Gets the singleton instance of the APIServer
     * @return
     * @throws Exception
     */
    public static APIServer getInstance() throws Exception
    {
        if(_instance != null)
            return _instance;
        else
        {
            throw new Exception("API Server not yet initialised. Call initInstance first");
        }
    }

    /**
     * Manual initialisation of the APIServer
     * @param port
     * @throws UnknownHostException
     */
    public static void initInstance(int port) throws UnknownHostException
    {
        _instance = new APIServer(port);
    }
    public static void initInstance(InetSocketAddress address)
    {
        _instance = new APIServer(address);
    }



    public void registerAction(String key, Consumer<String> func)
    {
        System.out.println("Registering action: " + key);
        if(!_registry.containsKey(key))
            _registry.put(key, func);
    }

    public synchronized void broadcast(String tag, String message)
    {
        System.out.println("Broadcasting: " + message + " _TO_ " + tag);
        synchronized (_connections)
        {
            _connections.forEach((k, sock) ->
            {
                if (tag.equals(k.tag))
                    sock.send(message);
            });
        }
    }

    public void sendTargeted(final String identifier, String message)
    {
        synchronized (_connections)
        {
            _connections.forEach((k, sock) ->
            {
                if (identifier.equals(k.identifier))
                    sock.send(message);
            });
        }
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {

    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote )
    {
        System.out.println("Socket disconnecting");
        synchronized (_connections)
        {
            _connections.values().remove(conn);
        }
    }

    @Override
    public void onMessage( WebSocket conn, String message )
    {
        JSONObject obj = new JSONObject(message);
        final String tag = obj.isNull("tag") ? null : obj.getString("tag"), id = obj.isNull("identifier") ? null : obj.getString("identifier"),
                priv = obj.isNull("privileges") ? null : obj.getString("privileges"), data = obj.isNull("data") ? null : obj.getString("data");

        // If it is a initial message by a recv socket
        if(id != null)
        {
            System.out.println("ReceiveSocket initialising");
            assert tag != null && id != null;
            synchronized (_connections)
            {
                if(!_connections.contains(conn))
                    _connections.put(new SocketTag(tag, priv, id) , conn);
            }
        }
        else
        {
            assert(tag != null & data != null);
            // Every sent message should contain a tag

            System.out.println("Data Send: " + tag + ", " + data);
            synchronized (_registry)
            {
                _registry.forEach((str, func) ->
                {
                    if (tag.equals(str)) {
                        func.accept(data);
                    }
                });
            }
        }

        System.out.println( conn + ": " + message );
    }



    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }

    /**
     * Sends <var>text</var> to all currently connected WebSocket clients.
     *
     * @param text
     *            The String to send across the network.
     * @throws InterruptedException
     *             When socket related I/O errors occur.
     */
    public void sendToAll( String text ) {
        Collection<WebSocket> con = connections();
        synchronized ( con ) {
            for( WebSocket c : con ) {
                c.send( text );
            }
        }
    }
}