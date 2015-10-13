package com.seveneleven.publishers;

import com.seveneleven.Manager;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class PassthroughPublisher extends Publisher {

    public PassthroughPublisher(Manager man)
    {
        super(man, "default");
    }

    public void trigger(String pub, String message)
    {
        publish(pub, message);
    }


}
