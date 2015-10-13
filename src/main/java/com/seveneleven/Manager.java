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
    HashMap<String, List<ISubscriber>> map;

    public Manager()
    {
        map = new HashMap<>();
    }

    public void subscribe(ISubscriber subscriber)
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
    public void unsubscribe(ISubscriber subscriber)
    {
        if(map.containsKey(subscriber.getSubscription()))
        {
            List l = map.get(subscriber.getSubscription());
            l.remove(subscriber);

            if(l.isEmpty())
                map.remove(subscriber.getSubscription());
        }
    }

    public void callSubscribers(String subscription, Object data)
    {
        System.out.println("Calling: " + subscription);

        if(map.containsKey(subscription))
        {
            for(ISubscriber s : map.get(subscription))
            {
                s.onData(data);
            }
        }
    }
}
