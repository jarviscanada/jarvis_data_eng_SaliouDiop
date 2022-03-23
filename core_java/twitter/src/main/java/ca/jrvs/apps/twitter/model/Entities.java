package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entities {

  private HashTag[] hashtags;
  private UserMention[] user_mentions;

  public HashTag[] getHashtags() {
    return hashtags;
  }

  public void setHashtags(HashTag[] hashtags) {
    this.hashtags = hashtags;
  }

  public UserMention[] getUser_mentions() {
    return user_mentions;
  }

  public void setUser_mentions(UserMention[] user_mentions) {
    this.user_mentions = user_mentions;
  }
}
