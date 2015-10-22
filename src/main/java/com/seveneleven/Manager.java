package com.seveneleven;

import com.seveneleven.subscribers.ISubscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mdl94 on 21/09/2015.
 */
public class Manager
{
    // Used for know which subscriber to invoke
    HashMap<String, List<ISubscriber>> map;

    /**
     * Creates a new manager instance
     */
    public Manager()
    {
        map = new HashMap<>();
    }

    /**
     * Subscribers the given subscriber to all relevant invocations from publishers
     * @param subscriber The subscriber
     */
    public void subscribe(ISubscriber subscriber)
    {
        synchronized (map)
        {
            if(!map.containsKey(subscriber.getSubscription()))
            {
                List temp = new ArrayList<ISubscriber>();
                temp.add(subscriber);

                map.put(subscriber.getSubscription(), temp);
            }
            else
            {
                List temp = map.get(subscriber.getSubscription());
                temp.add(subscriber);

                //map.replace(subscription, temp);
            }
        }
    }

    /**
     * Unsubscribes the given subscriber
     * @param subscriber The subscriber
     */
    public void unsubscribe(ISubscriber subscriber)
    {
        synchronized (map)
        {
            if(map.containsKey(subscriber.getSubscription()))
            {
                List l = map.get(subscriber.getSubscription());
                l.remove(subscriber);

                if(l.isEmpty())
                    map.remove(subscriber.getSubscription());
            }
        }
    }

    /**
     * Calls all subscribers with the given subscription string
     * @param subscription The subscription string
     * @param data The data to pass to subscribers
     */
    public void callSubscribers(String subscription, Object data)
    {
        //System.out.println("Calling: " + subscription);
        synchronized (map)
        {
            if(map.containsKey(subscription))
            {
                for(ISubscriber s : map.get(subscription))
                {
                    s.onData(data);
                }
            }
        }
    }

    public String getAllIncidents()
    {
        return "Getting incidents: Not implemented yet";
    }

    public String getAllHazards()
    {
        return "Getting hazards: Not yet implemented";
    }

    public String hitTest()
    {
        return "this is the hit test message. Will be replaced by actual data later";
    }
}
