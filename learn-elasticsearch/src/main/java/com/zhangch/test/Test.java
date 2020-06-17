package com.zhangch.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;

public class Test {
    private static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 500;
    private static int CONNECT_TIMEOUT_MILLIS            = 1000;
    private static int SOCKET_TIMEOUT_MILLIS             = 30000;
    private static int MAX_CONN_PER_ROUTE                = 10;
    private static int MAX_CONN_TOTAL                    = 30;
    private static RestHighLevelClient restHighLevelClient;

    public static void init(){
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        builder.setRequestConfigCallback(
                requestConfigBuilder -> {
                    requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
                    requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
                    requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
                    return requestConfigBuilder;
                });
        builder.setHttpClientConfigCallback(
                httpClientBuilder -> {
                    httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
                    httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
                    return httpClientBuilder;
                });
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    public static void createData() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("name", "jack");
            builder.field("age", 27);
            builder.field("position", "technique");
            builder.field("country", "china");
            builder.field("join_date", "2017-01-01");
            builder.field("salary", 10000);
        }
        builder.endObject();
        IndexRequest request = new IndexRequest("employee").id("1").source(builder);
        IndexResponse indexResponse =  restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    public static void main(String[] args) throws IOException {
        init();
        GetRequest request = new GetRequest("employee", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

}
