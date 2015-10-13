package com.seveneleven.subscribers;

/**
 * Created by mdl94 on 21/09/2015.
 */
public interface ISubscriber
{
    public void onData(Object data);
    public String getSubscription();
}
