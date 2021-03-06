package com.seveneleven.subscribers;

import com.seveneleven.publishers.Publisher;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SocialMediaSubscriber implements ISubscriber {

  private String latestStatus;

  public SocialMediaSubscriber() {
  }

  @Override
  public String getSubscription() {
    return Publisher.ADDED_NEW_HAZARD;
  }

  /**
   * Post the message to Twitter
   * @param data The string data. This data will be posted to Twitter
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

  /**
   * Get latest tweet.
   * @return the latest tweet. It returns null if you never tweet. `
   */
  public String getLatestStatus() {
    return latestStatus;
  }
}
