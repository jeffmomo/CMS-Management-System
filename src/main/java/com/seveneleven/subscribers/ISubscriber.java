package com.seveneleven.subscribers;

/**
 * Created by mdl94 on 21/09/2015.
 */
public interface ISubscriber
{
    /**
     * The actions to be executed when data corresponding to the subscriber's subscription is received
     * @param data The data
     */
    public void onData(Object data);

    /**
     * Gets the subscription string
     * @return
     */
    public String getSubscription();
}
