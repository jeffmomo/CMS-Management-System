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

    public APIServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public APIServer(InetSocketAddress address) {
        super(address);
    }

    public void registerAction(String key, Consumer<String> func)
    {
        System.out.println("Registering action: " + key);
        if(!_registry.containsKey(key))
            _registry.put(key, func);
    }

    public void broadcast(String apiType, String message)
    {
        synchronized (_connections)
        {
            _connections.forEach((k, sock) ->
            {
                if (apiType.equals(k.tag))
                    sock.send(message);
            });
        }
    }

    public void sendTargeted(String target, String message)
    {
        synchronized (_connections)
        {
            _connections.forEach((k, sock) ->
            {
                if (target.equals(k.identifier))
                    sock.send(message);
            });
        }
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {

    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {

    }

    @Override
    public void onMessage( WebSocket conn, String message )
    {
        JSONObject obj = new JSONObject(message);
        String tag = obj.isNull("tag") ? null : obj.getString("tag"), id = obj.isNull("identifier") ? null : obj.getString("identifier"),
                priv = obj.isNull("privileges") ? null : obj.getString("privileges"), data = obj.isNull("data") ? null : obj.getString("data");

        // If it is a initial message by a recv socket
        if(id != null)
        {
            System.out.println("ReceiveSocket initialising");
            assert tag != null && id != null;
            // Tagging the connection with content of initial message
            // TODO: Json
            // Left: APIType, Right: Identifier
            // Only put if a 3-tuple is given - i.e. 3-tuple implies that it is a receiving socket.
            if(!_connections.contains(conn))
                _connections.put(new SocketTag(tag, id, priv) , conn);
        }
        else
        {
            assert tag != null & data != null;
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