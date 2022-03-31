package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpUriRequest;

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
        this.httpClient = HttpClientBuilder.create().build();
    }

    public TwitterHttpHelper() {
        String consumerKey = System.getenv("TWITTER_CONSUMER_KEY");
        String consumerSecret = System.getenv("TWITTER_CONSUMER_SECRET");
        String accessToken = System.getenv("TWITTER_ACCESS_TOKEN");
        String accessTokenSecret = System.getenv("TWITTER_ACCESS_TOKEN_SECRET");
        this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        this.consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        this.httpClient = HttpClientBuilder.create().build();
    }

    @Override
    public HttpResponse httpGet(URI uri) throws IOException {
        HttpGet request = new HttpGet(uri);
        return executeHttpRequest(request);
    }

    @Override
    public HttpResponse httpPost(URI uri) throws IOException {
        HttpPost request = new HttpPost(uri);
        return executeHttpRequest(request);
    }

    private HttpResponse executeHttpRequest(HttpUriRequest request) throws IOException {
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