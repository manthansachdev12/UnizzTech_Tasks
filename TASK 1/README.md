# Currency Converter Program

This **Java Currency Converter** program allows users to convert an amount from one currency to another by fetching real-time exchange rates using the **ExchangeRate-API**. The program prompts the user to input the source currency, target currency, and the amount to be converted, then retrieves the exchange rate, calculates the converted amount, and displays the result.

## Key Features:

- **Real-Time Exchange Rates**  
  Fetches live exchange rates using the **ExchangeRate-API**.
  
- **User Input**  
  Allows users to specify the source currency (e.g., USD), target currency (e.g., INR), and the amount to convert.

- **Error Handling**  
  Handles various errors including API connection issues, invalid currency codes, and unexpected inputs.

- **Formatted Output**  
  Displays the exchange rate and converted amount with proper formatting for clarity.

## Technologies Used:
- **Language**: Java
- **Libraries**: 
  - `org.json.JSONObject` for parsing JSON responses.
  - `BufferedReader` for reading input.
  - `HttpURLConnection` for making HTTP requests.

## Example Flow:

1. The program asks the user for the source and target currencies.
2. It fetches the current exchange rate from the API.
3. The user provides an amount to convert.
4. The program displays the converted amount based on the current exchange rate.

## How to Run:

1. Download the project files.
2. Make sure you have **Java** installed.
3. Compile and run the program using your favorite IDE or the command line.

```bash
javac CurrencyConverter.java
java CurrencyConverter
