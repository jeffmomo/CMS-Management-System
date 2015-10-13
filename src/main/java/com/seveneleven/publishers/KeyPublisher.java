package com.seveneleven.publishers;

import com.seveneleven.Manager;

/**
 * Created by mdl94 on 22/09/2015.
 */
public class KeyPublisher extends Publisher
{

    public KeyPublisher(Manager man)
    {
        super(man, "key");
    }

    public void trigger(Object s)
    {
        publish(s);
    }

    public void testt(String pub, Object data)
    {
        publish(pub,data);
    }
}
