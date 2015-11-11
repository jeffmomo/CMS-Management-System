package com.seveneleven.subscribers;

import com.seveneleven.publishers.Publisher;
import com.seveneleven.servers.IAPIServer;
import junit.framework.TestCase;

import java.util.function.Consumer;

/**
 * Created by mdl94 on 4/11/2015.
 */
public class NewHazardComponentTest extends TestCase {

    DummyAPIServer dummyServer;

    public void setUp() throws Exception {
        super.setUp();
        dummyServer = new DummyAPIServer();
    }

    private class DummyAPIServer implements IAPIServer
    {
        public String tagString, dataString;

        @Override
        public void registerAction(String key, Consumer<String> func) {

        }

        @Override
        public void broadcast(String tag, String message) {
            tagString = tag;
            dataString = message;
        }

        @Override
        public void sendTargeted(String identifier, String message) {

        }
    }

    public void testGetSubscription() throws Exception {
        NewHazardComponent underTest = new NewHazardComponent(dummyServer);
        assertEquals(underTest.getSubscription(), Publisher.ADDED_NEW_HAZARD);
    }

    public void testOnData() throws Exception {
        NewHazardComponent underTest = new NewHazardComponent(dummyServer);
        underTest.onData("abc");

        assertEquals(dummyServer.tagString, Publisher.ADDED_NEW_HAZARD);
        assertEquals(dummyServer.dataString, "abc");

    }
}