package com.example.vehicle_reservation_project.util;

import com.example.vehicle_reservation_project.config.AsgardeoConfig;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    @Autowired
    private AsgardeoConfig asgardeoConfig;

    public boolean validateAccessToken(String accessToken) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(asgardeoConfig.getAsgardeoEndpoint());

            String introspectionRequest = "token=" + accessToken;
            httpPost.setEntity(new StringEntity(introspectionRequest));
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + asgardeoConfig.getClientId());
            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = new JSONObject(responseBody);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
