package com.iesvi.RecuperarContraseña;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SMS {

    public static void recuperarContraseña(String telefono) {

        //Se fija el tiempo máximo de espera para conectar con el servidor (5000)
        //Se fija el tiempo máximo de espera de la respuesta del servidor (60000)
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(60000)
                .build();

        //Se inicia el objeto HTTP
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();

        //Se fija la URL sobre la que enviar la petición POST
        HttpPost post = new HttpPost("http://www.altiria.net/api/http");

        //Se crea la lista de parámetros a enviar en la petición POST
        List<NameValuePair> parametersList = new ArrayList<>();
        parametersList.add(new BasicNameValuePair("cmd", "sendsms"));
        parametersList.add(new BasicNameValuePair("login", "samuel.condelopez@iesvalleinclan.es"));
        parametersList.add(new BasicNameValuePair("passwd", "qyp7vsry"));
        parametersList.add(new BasicNameValuePair("dest", "34" + telefono));
        parametersList.add(new BasicNameValuePair("msg", "Tu contraseña es: "));

        try {
            //Se fija la codificacion de caracteres de la peticion POST
            post.setEntity(new UrlEncodedFormEntity(parametersList, "UTF-8"));
        } catch (UnsupportedEncodingException uex) {
            System.out.println("ERROR: codificación de caracteres no soportada");
        }

        CloseableHttpResponse response = null;

        try {
            System.out.println("Enviando petici�n");
            //Se envía la petición
            response = httpClient.execute(post);
            //Se consigue la respuesta
            String resp = EntityUtils.toString(response.getEntity());

            //Error en la respuesta del servidor
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("ERROR: C�digo de error HTTP:  " + response.getStatusLine().getStatusCode());
                System.out.println("Compruebe que ha configurado correctamente la direccion/url ");
                System.out.println("suministrada por Altiria");
                return;
            } else {
                //Se procesa la respuesta capturada en la cadena 'response'
                if (resp.startsWith("ERROR")) {
                    System.out.println(resp);
                    System.out.println("Código de error de Altiria. Compruebe las especificaciones");
                } else
                    System.out.println(resp);
            }
        } catch (Exception e) {
            System.out.println("Excepci�n");
            e.printStackTrace();
            return;
        } finally {
            //En cualquier caso se cierra la conexión
            post.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    System.out.println("ERROR cerrando recursos");
                }
            }
        }
    }

}
