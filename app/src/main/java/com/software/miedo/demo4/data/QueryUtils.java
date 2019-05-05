package com.software.miedo.demo4.data;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.datatype.DatatypeConfigurationException;

public class QueryUtils {


    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String URL_INICIO = "http://169.62.31.213:8082" +
            "/alfresco/api/-default-/public/alfresco/versions/1/nodes/";

    private static final String URL_CIERRE = "/comments";

    private QueryUtils() {

    }

    private static Integer makeHttpRequest(URL url, String contenido) throws IOException {
        Integer jsonResponseCode = -1;

        // Si el URL es null regresará el string vacío
        if (url == null) {
            Log.e(LOG_TAG, "El URL es null, no se puede proceder con la petición http");
            return -1;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");


            String body = "{" +
                    "  \"content\":\"" + contenido + "\"" +
                    "}";
            byte[] outputInBytes = body.getBytes("UTF-8");
            OutputStream os = urlConnection.getOutputStream();
            os.write(outputInBytes);
            os.close();

            urlConnection.connect();

            // el codigo de retorno
            jsonResponseCode = urlConnection.getResponseCode();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 201) {
                inputStream = urlConnection.getInputStream();
                String jsonResponse = readFromStream(inputStream);
                Log.e(LOG_TAG, "Comentario agregado correctamente");
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema obteniendo el resultado :" + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponseCode;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    public static Integer doPost(String id, String contenido) {
        URL urlConsulta = createUrl(URL_INICIO + id + URL_CIERRE);


        // creamos la respuesta que será mostrada en forma de String
        Integer jsonResponseCode = -1;
        try {
            jsonResponseCode = makeHttpRequest(urlConsulta, contenido);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema realizando la petición HTTP", e);
        }

        Log.e(LOG_TAG, "Respuesta extraída final: " + jsonResponseCode);

        return jsonResponseCode;
    }


    /**
     * JSON response de ejemplo
     */
    private static final String JSON_RESPONSE_SAMPLE = "{\n" +
            "    \"entry\": {\n" +
            "        \"createdAt\": \"2018-11-08T19:41:58.146+0000\",\n" +
            "        \"createdBy\": {\n" +
            "            \"enabled\": true,\n" +
            "            \"firstName\": \"Administrator\",\n" +
            "            \"email\": \"admin@alfresco.com\",\n" +
            "            \"emailNotificationsEnabled\": true,\n" +
            "            \"company\": {},\n" +
            "            \"id\": \"admin\"\n" +
            "        },\n" +
            "        \"edited\": false,\n" +
            "        \"modifiedAt\": \"2018-11-08T19:41:58.146+0000\",\n" +
            "        \"canEdit\": true,\n" +
            "        \"modifiedBy\": {\n" +
            "            \"enabled\": true,\n" +
            "            \"firstName\": \"Administrator\",\n" +
            "            \"email\": \"admin@alfresco.com\",\n" +
            "            \"emailNotificationsEnabled\": true,\n" +
            "            \"company\": {},\n" +
            "            \"id\": \"admin\"\n" +
            "        },\n" +
            "        \"canDelete\": true,\n" +
            "        \"id\": \"68cc6a60-7046-4db8-8ecf-afc280043c53\",\n" +
            "        \"content\": \"la kzm no funciona esta mierdaaaaaaaaa\"\n" +
            "    }\n" +
            "}";
}
