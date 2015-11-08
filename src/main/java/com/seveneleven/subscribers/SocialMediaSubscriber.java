package com.seveneleven.subscribers;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SocialMediaSubscriber implements ISubscriber {

  public static final String SOCIAL_MEDIA_SUBSCRIBER = "social_media_subscriber";

  private String latestStatus;

  public SocialMediaSubscriber() {
  }

  @Override
  public String getSubscription() {
    return SOCIAL_MEDIA_SUBSCRIBER;
  }

  /**
   * @param data The string data. This data will be posted to all social media.
   */
  @Override
  public void onData(Object data) {
    if (data == null || !(data instanceof String)) {
      return;
    }

    String message = (String) data;

    // The factory instance is re-useable and thread safe.
    Twitter twitter = TwitterFactory.getSingleton();
    try {
      Status status = twitter.updateStatus(message);
      System.out.println("Successfully updated the status to [" + status.getText() + "].");
      latestStatus = status.getText();
    } catch (TwitterException e) {
      e.printStackTrace();
    }
  }

  public String getLatestStatus() {
    return latestStatus;
  }
}
