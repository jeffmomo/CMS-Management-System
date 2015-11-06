package com.seveneleven;

/**
 * Created by mdl94 on 5/11/2015.
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;

// Performs a load test, taking on the role of a client
class ListenClient extends WebSocketClient
{
    public ListenClient(URI serverURI)
    {
        super(serverURI);
    }
        @Override
        public void onOpen(ServerHandshake serverHandshake) {
        // Takes on role of a listen-socket, and listens to the "client_create_zone" tag
        this.send("{\"tag\":\"client_create_zone\",\"identifier\":\"0.5153393794316798\",\"privileges\":\"read\"}");
    }

        @Override
        public void onError(Exception e) {

    }

        @Override
        public void onMessage(String message) {
        System.out.println("Delay (milliseconds): " + ((System.nanoTime() / 1000000) - Integer.parseInt(message.substring(1))));
    }


        @Override
        public void onClose(int code, String reason, boolean remote) {
    }
}


public class LoadTester extends WebSocketClient {

    public LoadTester(URI serverUri, Draft draft)
    {
        super(serverUri, draft);
    }

    public LoadTester(URI serverURI)
    {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        // Now the socket is open and ready to send
        WebSocketClient self = this;

        // Tests the CallcentreAPI adding a hazard 10 times per second
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++)
                    self.send("{\"tag\":\"srv_add_hazard\",\"data\":" + "z" + (System.nanoTime() / 1000000) + "}");
            }
        }, 0l, 100l);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        // The codecodes are documented in class org.java_websocket.framing.CloseFrame
        System.out.println("Connection closed by " + (remote ? "remote peer" : "us"));
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }

    public static void main(String[] args) throws URISyntaxException {
        LoadTester c = new LoadTester(new URI("ws://localhost:8887"), new Draft_10()); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        c.connect();

        for (int i = 0; i < 100; i++) {
            WebSocketClient cc = new ListenClient(new URI("ws://localhost:8887"));

            cc.connect();

        }
    }

}

