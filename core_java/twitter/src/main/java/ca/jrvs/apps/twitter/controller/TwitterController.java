package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterController implements Controller{
    private final Service service;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }
    @Override
    public Tweet postTweet(String[] args) {
        if(args.length != 3) {
            throw new IllegalArgumentException("Missing arguments. Usage: TwitterApp post <text> <latitude:longitude>");
        }
        String text = args[1];
        String[] coordinates = args[2].split(":");
        if(coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid coordinates. Usage: TwitterApp post <text> <latitude:longitude>");
        }
        Tweet tweet;
        try {
            float latitude = Float.parseFloat(coordinates[0]);
            float longitude = Float.parseFloat(coordinates[1]);
            tweet = TweetUtil.buildTweet(text, latitude, longitude);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coordinates. Usage: TwitterApp post <text> <latitude:longitude>");
        }
        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Missing arguments. Usage: TwitterApp show <id>");
        }
        String id = args[1];
        return service.showTweet(id, null);
    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Missing arguments. Usage: TwitterApp delete <id>");
        }
        String[] ids = args[1].split(",");
        return service.deleteTweet(ids);
    }
}
