package ca.jrvs.apps.twitter.dao.helper;

import  java.net.URI;
import java.net.http.HttpResponse;

public interface HttpHelper {

    /**
     * Execute a HTTP Post call
     * @param uri
     * @return response body
     */
    HttpResponse httpPost(URI uri);

    /**
     * Execute a HTTP Get call
     * @param uri
     * @return response body
     */
    HttpResponse httpGet(URI uri);
}
