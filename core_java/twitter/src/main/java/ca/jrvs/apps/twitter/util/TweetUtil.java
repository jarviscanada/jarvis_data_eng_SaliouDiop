package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.Coordinates;

public class TweetUtil {
    public static Tweet buildTweet(String text, double latitude, double longitude) {
        Tweet tweet = new Tweet();
        if (text != null) {
            tweet.setText(text);
        } else {
            throw new IllegalArgumentException("Tweet text cannot be null");
        }

        Coordinates coordinates = new Coordinates();
        if (latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180) {
            double[] coordinatesArray = {latitude, longitude};
            coordinates.setCoordinates(coordinatesArray);

            tweet.setCoordinates(coordinates);

            return tweet;
        } else {
            throw new IllegalArgumentException("Invalid latitude or longitude");
        }
    }
}
