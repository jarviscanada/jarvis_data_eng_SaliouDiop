package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;


import java.io.IOException;
import java.net.URI;

public class TwitterDao implements CrdDao<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String API_VERSION = "1.1";
  private static final String POST_PATH = API_VERSION + "/statuses/update.json";
  private static final String GET_PATH = API_VERSION + "/statuses/show.json";
  private static final String DELETE_PATH = API_VERSION + "/statuses/destroy.json";

  private static final int HTTP_RESPONSE_OK = 200;

  private final HttpHelper httpHelper;

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  @Override
  public Tweet create(Tweet entity) {
    URI uri = getPostUri(entity);
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponse(response, HTTP_RESPONSE_OK);
  }
  @Override
  public Tweet findById(String id) {
    String path = API_BASE_URI + GET_PATH + "?id=" + id;
    URI uri = getUri(path);
    try {
      HttpResponse response = httpHelper.httpGet(uri);
      return parseResponse(response, HTTP_RESPONSE_OK);
    } catch (IOException e) {
      throw new RuntimeException("Failed to get tweet", e);
    }
  }

  @Override
  public Tweet delete(String id) {
    String path = API_BASE_URI + DELETE_PATH + "?id=" + id;
    URI uri = getUri(path);
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponse(response, HTTP_RESPONSE_OK);
  }

  public Tweet parseResponse(HttpResponse response, int expectedStatusCode) {
    int statusCode = response.getStatusLine().getStatusCode();
    if (statusCode != expectedStatusCode) {
      throw new RuntimeException("Failed to create tweet");
    }
    Tweet entity = null;
    try {
      String json = EntityUtils.toString(response.getEntity());
      entity = JsonUtil.fromJson(json, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to parse json", e);
    }
    return entity;
  }

  private URI getUri(String path) {
    return URI.create(API_BASE_URI + GET_PATH + "?id=" + path);
  }

  private URI getPostUri(Tweet tweet) {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    double[] coordinates = tweet.getCoordinates().getCoordinates();
    String text = tweet.getText();

    return URI.create(API_BASE_URI + POST_PATH + "?status=" + percentEscaper.escape(text) + "&lat=" + coordinates[0] + "&long=" + coordinates[1]);
  }

}