package com.seveneleven.publishers;

import com.seveneleven.Manager;


public class SocialMediaPublisher extends Publisher {

  public static final String SOCIAL_MEDIA_PUBLICATION = "social_media";

  public SocialMediaPublisher(Manager manager) {
    super(manager, SOCIAL_MEDIA_PUBLICATION);
  }

  // TODO: what data should I published?
}
