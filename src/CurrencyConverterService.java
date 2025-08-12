
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterService {
    private final String apiKey;
    private final String baseUrl;
    private final HttpClient httpClient;
    private final Gson gson = new Gson();

    public CurrencyConverterService(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length()-1) : baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    public double convert(String from, String to, double amount) throws IOException, InterruptedException {
        double rate = fetchConversionRate(from, to);
        return amount * rate;
    }

    private double fetchConversionRate(String from, String to) throws IOException, InterruptedException {
        // v6 ExchangeRate-API: https://v6.exchangerate-api.com/v6/{API_KEY}/pair/{FROM}/{TO}
        String url = String.format("%s/%s/pair/%s/%s", baseUrl, apiKey, from, to);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Falha HTTP: " + response.statusCode());
        }

        CurrencyResponse data = gson.fromJson(response.body(), CurrencyResponse.class);

        // Esperado: {"result":"success","base_code":"USD","target_code":"BRL","conversion_rate":...}
        if (data == null || !"success".equalsIgnoreCase(data.result())) {
            // tenta extrair mensagem de erro se tiver
            JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
            String msg = (obj != null && obj.has("error-type")) ? obj.get("error-type").getAsString()
                    : "Resposta inválida da API.";
            throw new IllegalStateException(msg);
        }

        if (data.conversion_rate() == null) {
            throw new IllegalStateException("Campo 'conversion_rate' ausente na resposta.");
        }

        return data.conversion_rate();
    }
}
