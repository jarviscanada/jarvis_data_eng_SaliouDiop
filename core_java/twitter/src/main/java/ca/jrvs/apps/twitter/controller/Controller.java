package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;

import java.util.List;

public interface Controller {

    /**
     * Post a tweet
     * @param args
     * @return a posted tweet
     * @throws IllegalArgumentException if the tweet is empty
     * @throws IllegalArgumentException if the tweet is longer than 140 characters
     */

    Tweet postTweet(String[] args);

    /**
     * Retrieve a tweet
     * @param args
     * @return a tweet
     * @throws IllegalArgumentException if the tweet id is not a number
     */
    Tweet showTweet(String[] args);

    /**
     * Delete a tweets
     * @param args
     * @return list of deleted tweets
     * @throws IllegalArgumentException if the tweet id is not a number
     */
    List<Tweet> deleteTweet(String[] args);
}
