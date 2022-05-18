package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TwitterDaoIntTest {
    private TwitterDao dao;

    @Before
    public void setUp() throws Exception {
        HttpHelper httpHelper = new TwitterHttpHelper();
        dao = new TwitterDao(httpHelper);
    }
    @Test
    public void create() throws IOException {
        String hashtag = "#test";
        String text = "test tweet " + hashtag + " " + System.currentTimeMillis();
        double latitude = 41.89;
        double longitude = -87.6;
        Tweet postTweet = TweetUtil.buildTweet(text, latitude, longitude);
        Tweet createdTweet = dao.create(postTweet);

        assertEquals(text, createdTweet.getText());
        assertEquals(latitude, createdTweet.getCoordinates().getCoordinates()[0], 0.01);
        assertEquals(longitude, createdTweet.getCoordinates().getCoordinates()[1], 0.01);

    }

    @Test
    public void findById() {
        String id = "1228393702244134912";
        Tweet tweet = dao.findById(id);
        assertNotNull(tweet);
        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    }

    @Test
    public void delete() {
        String id = "1228393702244134912";
        Tweet tweet = dao.delete(id);
        assertNotNull(tweet);
        assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    }
}