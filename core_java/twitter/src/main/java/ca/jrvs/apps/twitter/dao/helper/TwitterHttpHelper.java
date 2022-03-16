package ca.jrvs.apps.twitter.dao.helper;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
        this.httpClient = HttpClient.newHttpClient();
    }

    private HttpResponse executeHttpResquest(HttpUriRequest request) {
        HttpResponse response = null;
        try {
            consumer.sign(request);
            response = httpClient.send((HttpRequest) request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException | OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    @Override
    public HttpResponse httpPost(URI uri) {
        HttpPost request = new HttpPost(uri);
        return executeHttpResquest(request);
    }

    @Override
    public HttpResponse httpGet(URI uri) {
        HttpUriRequest request = new HttpPost(uri);
        return executeHttpResquest(request);
    }
}