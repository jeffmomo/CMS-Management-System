package com.seveneleven.subscribers;

import com.seveneleven.publishers.Publisher;

/**
 * Created by mdl94 on 12/10/2015.
 */
public class WriteOutSubscriber implements ISubscriber {

    @Override
    public String getSubscription() {
        return Publisher.TEST_WRITEOUT;
    }

    @Override
    public void onData(Object data) {
        System.err.println(data);
    }
}
