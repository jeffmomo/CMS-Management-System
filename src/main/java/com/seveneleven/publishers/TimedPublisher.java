package com.seveneleven.publishers;

import com.seveneleven.Manager;
import com.seveneleven.servers.APIServer;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mdl94 on 16/10/2015.
 */
public class TimedPublisher extends Publisher
{

    private APIServer server;

    public TimedPublisher(Manager m, long delay)
    {
        super(m, "server_test");

        try{
            server = APIServer.getInstance();

        } catch (Exception e)
        {
            e.printStackTrace();
        }


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scheduledTask();
            }
        }, 0l, delay);
    }

    public void scheduledTask()
    {
        double la = Math.random() * 100; double lo = Math.random() * 100; double rad = Math.random() * 500000;
        publish(new double[]{la, lo, rad});
    }

}
