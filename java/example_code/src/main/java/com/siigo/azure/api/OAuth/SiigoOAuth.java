/*
 * SiigoOAuth.java
 *
 * Proyecto: Integrador de eCommerce
 * Cliente: SIIGO
 * Copyright 2017 by Mobiltech SAS 
 * All rights reserved
 */
package com.siigo.azure.api.OAuth;

import com.siigo.azure.api.util.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Administrator
 */
public class SiigoOAuth {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public String GetTokenUser(String username, String password) {

        String accessToken = "";

        try {

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpPost httpPost = new HttpPost(Constantes.AUTHORITY);

                String bodyPayload = String.format("grant_type=%s&resource=%s&client_id=%s&client_secret=%s=&username=%s&password=%s", Constantes.GRANT_TYPE_PASSWORD, Constantes.RESOURCE, Constantes.CLIENT_ID, Constantes.CLIENT_SECRET, username, password);

                StringEntity entity = new StringEntity(bodyPayload);
                httpPost.setEntity(entity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

                CloseableHttpResponse response = client.execute(httpPost);

                if (response.getStatusLine().getStatusCode() == 200) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    String inputLine;
                    StringBuffer sbResponse = new StringBuffer();

                    while ((inputLine = reader.readLine()) != null) {
                        sbResponse.append(inputLine);
                    }
                    reader.close();

                    SiigoOAuthTokenDTO respuestaGetToken = new ObjectMapper().readValue(sbResponse.toString(), SiigoOAuthTokenDTO.class);

                    accessToken = respuestaGetToken.getAccessToken();

                    System.out.println("Access Token  Generado:\n" + accessToken);

                }
            }

        } catch (IOException | UnsupportedOperationException e) {
            e.printStackTrace();
        }

        return accessToken;

    }

}
