package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.example.demo.FuntionService;
import static java.lang.reflect.Array.get;
import com.example.demo.FuntionServer;
public class FuntionProxy {
    private static String[] SERVICES = {
            System.getenv("SERVICE1_URL") != null ? System.getenv("SERVICE1_URL") : "http://localhost:4567",
            System.getenv("SERVICE2_URL") != null ? System.getenv("SERVICE2_URL") : "http://localhost:4568"
    };

    private static int currentService = 0;


    public static void main(String[] args) {
        port(getPort());
        //get("/funtionsequence", (req, res) -> proxyRequest(req, res));
    }

    static void port(int port) {
    }

    /*

    private static Object proxyRequest(Request req, Response res) {
        try {
            String serviceUrl = SERVICES[currentService];
            currentService = (currentService + 1) % SERVICES.length;

            String number = req.queryParams("number");
            URL url = new URL(serviceUrl + "/funtionsequence?number=" + number);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            res.type("application/json");
            return response.toString();

        } catch (Exception e) {
            res.status(500);
            return "{\"error\": \"Proxy error: " + e.getMessage() + "\"}";
        }
    }


     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}
