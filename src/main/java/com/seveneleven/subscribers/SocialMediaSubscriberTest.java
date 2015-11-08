package com.seveneleven.subscribers;

import junit.framework.TestCase;


public class SocialMediaSubscriberTest extends TestCase {
  public void testGetSubscription() {
    SocialMediaSubscriber subscriber = new SocialMediaSubscriber();
    assertEquals(SocialMediaSubscriber.SOCIAL_MEDIA_SUBSCRIBER, subscriber.getSubscription());
  }

  public void testOnDataNullString() {
    SocialMediaSubscriber subscriber = new SocialMediaSubscriber();
    subscriber.onData(null);
    assertEquals(null, subscriber.getLatestStatus());
    // Expect no exception
  }

  public void testOnDataEmptyString() {
    SocialMediaSubscriber subscriber = new SocialMediaSubscriber();
    subscriber.onData("");
    assertEquals(null, subscriber.getLatestStatus());
    // Expect no exception
  }

  public void testOnData() {
    SocialMediaSubscriber subscriber = new SocialMediaSubscriber();
    subscriber.onData("Hello World");
    assertEquals("Hello World", subscriber.getLatestStatus());
  }
}
