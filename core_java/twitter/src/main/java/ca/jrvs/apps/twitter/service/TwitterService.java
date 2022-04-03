package ca.jrvs.apps.twitter.service;


import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterService implements Service {
    private final CrdDao<Tweet, String> dao;

    public TwitterService(CrdDao dao) {
        this.dao = dao;
    }
    @Override
    public Tweet postTweet(Tweet tweet) throws IOException {
        validatePostTweet(tweet);
        return dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {
        return null;
    }

    @Override
    public List<Tweet> deleteTweet(String[] id) {
        return null;
    }

    private void validateShowTweet(String id, String[] fields) {
        String[] validFields = {
                "created_at",
                "id",
                "id_str",
                "text",
                "entities",
                "coordinates",
                "retweet_count",
                "favorite_count",
                "favorited",
                "retweeted",
        };
        List<String> validFieldsList = new ArrayList<>(Arrays.asList(validFields));

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be null or empty");
        }
        if(!id.matches("[0-9]+")) {
            throw new IllegalArgumentException("id must be an integer");
        }

        if (fields == null || fields.length == 0) {
            throw new IllegalArgumentException("fields cannot be null or empty");
        }

        for (String field : fields) {
            if (!validFieldsList.contains(field)) {
                throw new IllegalArgumentException("field " + field + " is not valid");
            }
        }

    }

    private void validatePostTweet(Tweet tweet) {
        if (tweet == null) {
            throw new IllegalArgumentException("tweet cannot be null");
        }
        if (tweet.getText() == null || tweet.getText().isEmpty()) {
            throw new IllegalArgumentException("tweet text cannot be null or empty");
        }
        if (tweet.getText().length() > 280) {
            throw new IllegalArgumentException("tweet text cannot be longer than 280 characters");
        }

    }
}
