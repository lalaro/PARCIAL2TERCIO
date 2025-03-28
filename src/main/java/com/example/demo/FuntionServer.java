package com.example.demo;

public class FuntionServer {
    /*
    public static void main(String[] args) {
        // Configurar puerto desde variable de entorno o usar puerto por defecto
        port(getPort());

        // Configurar ruta para calcular secuencia de Collatz
        get("/funtionsequence", (req, res) -> {
            res.type("application/json");
            String numberStr = req.queryParams("number");

            if (numberStr == null || numberStr.isEmpty()) {
                res.status(400);
                return "{\"error\": \"Número no proporcionado\"}";
            }

            try {
                int number = Integer.parseInt(numberStr);
                int[] sequence = generateFuntionSequence(number);

                // Construir respuesta JSON
                StringBuilder jsonResponse = new StringBuilder();
                jsonResponse.append("{")
                        .append("\"operation\": \"factors\",")
                        .append("\"inputNumber\": ").append(number).append(",")
                        .append("\"output\": [");

                for (int i = 0; i < sequence.length; i++) {
                    jsonResponse.append(sequence[i]);
                    if (i < sequence.length - 1) {
                        jsonResponse.append(",");
                    }
                }

                jsonResponse.append("]}");

                return jsonResponse.toString();

            } catch (NumberFormatException e) {
                res.status(400);
                return "{\"error\": \"Formato de número inválido\"}";
            }
        });
    }*/

    private static int[] generateFuntionSequence(int n) {
        if (n <= 0) throw new IllegalArgumentException("El número debe ser positivo");

        java.util.List<Integer> sequence = new java.util.ArrayList<>();
        sequence.add(n);

        while (n != 1) {
            n = (n % 2 == 0) ? n / 2 : 3 * n + 1;
            sequence.add(n);
        }

        // Convertir lista a array
        return sequence.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getMaxValue(int[] sequence) {
        return java.util.Arrays.stream(sequence).max().orElse(0);
    }

    private static int getPort() {
        // Obtener puerto de variable de entorno, por defecto 4567
        return Integer.parseInt(
                System.getenv().getOrDefault("PORT", "4567")
        );
    }
}
