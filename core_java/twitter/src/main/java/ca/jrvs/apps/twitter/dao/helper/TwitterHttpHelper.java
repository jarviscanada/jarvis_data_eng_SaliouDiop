package ca.jrvs.apps.twitter.dao.helper;

import io.github.cdimascio.dotenv.Dotenv;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpUriRequest;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {
    private final OAuthConsumer consumer;
    private final HttpClient httpClient;

    /**
     * Constructor
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     * @param accessToken access token
     * @param accessTokenSecret access token secret
     */
    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        this.httpClient = new DefaultHttpClient();
    }

    public TwitterHttpHelper() {
        Dotenv dotenv = Dotenv.load();
        String consumerKey = dotenv.get("TWITTER_API_KEY");
        String consumerSecret = dotenv.get("TWITTER_API_KEY_SECRET");
        String accessToken = dotenv.get("TWITTER_ACCESS_TOKEN");
        String accessTokenSecret = dotenv.get("TWITTER_ACCESS_TOKEN_SECRET");
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        this.httpClient = new DefaultHttpClient();
    }

    @Override
    public HttpResponse httpGet(URI uri) throws IOException {
        HttpGet request = new HttpGet(uri);
        return executeHttpRequest(request);
    }

    @Override
    public HttpResponse httpPost(URI uri) {
        HttpPost request = new HttpPost(uri);
        return executeHttpRequest(request);
    }

    private HttpResponse executeHttpRequest(HttpUriRequest request) {
        HttpResponse response = null;
        try {
            consumer.sign(request);
            response = httpClient.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}