/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siigo.azure.api;

import com.siigo.azure.api.OAuth.SiigoOAuth;
import com.siigo.azure.api.util.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author mobiltech
 */
public class Accounts_GetAll {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /**
         *
         * Para correr este ejemplo debe configurar el archivo constantes con
         * los datos que siigo le da para consumir el Api
         *
         */
        try {

            String accessToken = new SiigoOAuth().GetTokenUser(Constantes.USERNAME, Constantes.PASSWORD);

            // Dirección URL de la solicitud: https://siigoapi.azure-api.net/nube/api/{Version}/Accounts/GetAll
            URIBuilder builder = new URIBuilder("https://siigoapi.azure-api.net/nube/api/" + Constantes.APIVERSION + "/Accounts/GetAll");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", Constantes.SUSCRIPTIONKEY);
            request.setHeader("Authorization", accessToken);

            HttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {

                ObjectMapper mapper = new ObjectMapper();
                Object json = mapper.readValue(EntityUtils.toString(entity), Object.class);

                String respuesta = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

                System.out.println("Respuesta:\n" + respuesta);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String toStringJson(String jsonObj) {
        String dtoJsonString = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            dtoJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
        } catch (Exception e) {
        }
        return dtoJsonString;
    }
}
