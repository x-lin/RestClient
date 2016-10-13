package com.jamasoftware.services.restclient;

import com.jamasoftware.services.restclient.httpconnection.ApacheHttpClient;
import com.jamasoftware.services.restclient.httpconnection.HttpClient;
import com.jamasoftware.services.restclient.json.Json;
import com.jamasoftware.services.restclient.json.SimpleJson;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class JamaConfig {
    private String baseUrl;
    private String username;
    private String password;
    private Json json;
    private HttpClient httpClient;

    public JamaConfig() {
        json = new SimpleJson();
        try {
            httpClient = new ApacheHttpClient();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public JamaConfig(boolean loadFromPropertiesFile) {
        InputStream input = null;
        try {
            Properties properties = new Properties();
            input = new FileInputStream("jama.properties");
            properties.load(input);
            baseUrl = properties.getProperty("baseUrl");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            json = new SimpleJson();
            httpClient = new ApacheHttpClient();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Json getJson() {
        return json;
    }

    public void setJson(Json json) {
        this.json = json;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}