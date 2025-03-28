package com.example.demo;


import static com.example.demo.FuntionProxy.port;
//import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
//import org.json.JSONArray;


public class FuntionService {

    public static void main(String[] args) {
        port(getPort());
        System.out.println("Hola mundo");
        //get("/factors", (req, res) -> processCollatzSequence(req, res));
    }

    private static void get(String s, Object o) {
    }

    /*
    private static Object processCollatzSequence(Request req, Response res) {
        res.type("application/json");
        String numberStr = req.queryParams("number");

        if (numberStr == null || numberStr.isEmpty()) {
            res.status(400);
            return new JSONObject()
                    .put("operation", "funtionSequence")
                    .put("error", "Missing number parameter");
        }

        try {
            int number = Integer.parseInt(numberStr);
            int[] sequence = generateCollatzSequence(number);

            return new JSONObject()
                    .put("operation", "collatzSequence")
                    .put("inputNumber", number)
                    .put("sequenceLength", sequence.length)
                    .put("maxValue", getMaxValue(sequence))
                    .put("sequence", new JSONArray(sequence));

        } catch (NumberFormatException e) {
            res.status(400);
            return new JSONObject()
                    .put("operation", "collatzSequence")
                    .put("error", "Invalid number format");
        }
    }
    */
    private static int[] generateCollatzSequence(int number) {
        List<Integer> sequence = new ArrayList<>();
        while (number != 1) {
            sequence.add(number);
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = 3 * number + 1;
            }
        }
        sequence.add(1);  // Agregar el último número 1
        return sequence.stream().mapToInt(i -> i).toArray();
    }


    private static int getMaxValue(int[] sequence) {
        return Arrays.stream(sequence).max().orElse(0);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}