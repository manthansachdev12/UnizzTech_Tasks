# Java Developer Internship (JD-INT004) at UnizzTech  

## Overview  
This repository contains all the tasks and projects completed during my 1-month **Java Developer Internship** (21/12/2024 to 20/01/2025) at **UnizzTech**. The internship focused on gaining hands-on experience in Java development, covering various aspects of programming and web development.  

## Internship Details  
- **Internship Duration**: 1 Month (21/12/2024 to 20/01/2025)  
- **Domain**: Java Development  
- **Tasks Completed**:  
  - Week 1: Currency Converter  

## Week 1: Currency Converter  

### Task Description  
The **Currency Converter** is a Java application that converts an amount from one currency to another using real-time exchange rates from the **ExchangeRate-API**.  

### Features  
1. Fetches live exchange rates from the API.  
2. Converts a specified amount from a source currency to a target currency.  
3. Displays the exchange rate and converted amount with proper formatting.  
4. Handles errors gracefully, including invalid inputs and API issues.  

### Tools and Technologies  
- **Language**: Java  
- **Libraries**:  
  - `org.json.JSONObject` for JSON parsing.  
  - `HttpURLConnection` for API requests.  
  - `DecimalFormat` for formatted output.  
- **API**: ExchangeRate-API

### Sample Output  
```text
Welcome to the Currency Converter!  
Enter the currency you want to convert from (e.g., USD): USD  
Enter the currency you want to convert to (e.g., INR): INR  
Enter the amount you want to convert: 100  
Exchange rate (USD to INR): 83.45  
Converted amount: 8345.00 INR  
