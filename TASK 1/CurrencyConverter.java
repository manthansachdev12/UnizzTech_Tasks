import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "cbbb99e87122ae8abcb12b0c"; // API key

        System.out.println("Welcome to the Currency Converter!");

        try {
            System.out.print("Enter the currency you want to convert from (e.g., USD): ");
            String fromCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the currency you want to convert to (e.g., INR): ");
            String toCurrency = scanner.nextLine().toUpperCase();

            System.out.print("Enter the amount you want to convert: ");
            double amount = scanner.nextDouble();

            // Build API URL
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + fromCurrency;

            // Create URL and open connection
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                // Check if the result is "success" (string)
                String result = jsonResponse.getString("result");
                if ("success".equals(result)) {
                    double exchangeRate = jsonResponse.getJSONObject("conversion_rates").getDouble(toCurrency);
                    double convertedAmount = amount * exchangeRate;

                    // Use DecimalFormat to format the output to 2 decimal places
                    DecimalFormat df = new DecimalFormat("#.##");
                    System.out.println("Exchange rate (" + fromCurrency + " to " + toCurrency + "): " + exchangeRate);
                    System.out.println("Converted amount: " + df.format(convertedAmount) + " " + toCurrency);
                } else {
                    System.out.println("Error: " + jsonResponse.getJSONObject("error").getString("message"));
                }
            } else {
                System.out.println("Error: Unable to fetch exchange rate. Response code: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
