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

  @Override
  public void onData(Object data) {
    // TODO: what data will I receive?
  }
}
