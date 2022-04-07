package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.model.Tweet;

import java.io.IOException;
import java.util.List;

public interface Service {

    /**
     * Post a tweet
     *
     * @param tweet the tweet to post
     * @return the posted tweet
     */
    Tweet postTweet(Tweet tweet);

    /**
     * Show a tweet
     *
     * @param id the id of the tweet to show
     * @return the tweet
     * @throws IllegalArgumentException if the tweet does not exist
     */

    Tweet showTweet(String id, String[] fields);

    /*
     * Delete a tweet
     * @param id the id of the tweet to delete
     * @return A list of deleted tweets
     * @throws IllegalArgumentException if the tweet does not exist
     */

    List<Tweet> deleteTweet(String[] id);

}
