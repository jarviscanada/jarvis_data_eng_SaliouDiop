package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TwitterCLI {
    private final Controller controller;
    private final String usage = "Usage: java -jar TwitterCLI.jar [command] [options]" +
            "\n\nCommands:" +
            "\n\t--delete\t\tDelete a tweet" +
            "\n\t--show\t\t\tShow a tweet" +
            "\n\t--post\t\t\tPost a new tweet";

    @Autowired
    public TwitterCLI(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String consumerKey  = dotenv.get("TWITTER_API_KEY");
        String consumerSecret = dotenv.get("TWITTER_API_KEY_SECRET");
        String accessToken = dotenv.get("TWITTER_ACCESS_TOKEN");
        String accessTokenSecret = dotenv.get("TWITTER_ACCESS_TOKEN_SECRET");

        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        CrdDao dao = new TwitterDao(httpHelper);
        TwitterService service = new TwitterService(dao);
        TwitterController controller = new TwitterController(service);
        TwitterCLI cli = new TwitterCLI(controller);
        cli.run(args);

    }

    public void run(String[] args) {
        if (args.length == 0) {
            printHelp();
        } else {
            String command = args[0];
            Tweet tweet = null;
            switch (command) {
                case "post":
                    if (args.length != 3) {
                        throw new IllegalArgumentException(usage);
                    }
                    tweet = controller.postTweet(args);
                    printTweet(tweet);
                    break;
                case "show":
                    if (args.length > 3 || args.length < 2) {
                        throw new IllegalArgumentException("Usage: java -jar TwitterCLI.jar show <tweet_id> [fields]");
                    }
                    tweet = controller.showTweet(args);
                    printTweet(tweet);
                    break;
                case "delete":
                    if (args.length != 2) {
                        throw new IllegalArgumentException("Usage: java -jar TwitterCLI.jar delete <tweet_id>");
                    }
                    List<Tweet> tweets = controller.deleteTweet(args);
                    for (Tweet t : tweets) {
                        printTweet(t);
                    }
                    break;
                default:
                    printHelp();
                    break;
            }
        }
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toJson(tweet, true, false));
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to print tweet", e);
        }
    }
    private void printHelp() {
        System.out.println(usage);
    }
}