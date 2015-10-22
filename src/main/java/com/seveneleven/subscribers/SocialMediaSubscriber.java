package com.seveneleven.subscribers;

/**
 * Created by andyccs on 21/10/15.
 */
public class SocialMediaSubscriber implements ISubscriber {

  public static final String SOCIAL_MEDIA_SUBSCRIBER = "social_media_subscriber";

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
    System.out.println("Posting message to Facebook: " + message);
    System.out.println("Posting message to Twitter: " + message);
  }
}
