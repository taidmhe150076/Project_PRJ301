package Common;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenQrCode {

    public static String genQr(float amount) {
        try {
            // Define the API endpoint URL
            String apiUrl = "https://api.vietqr.io/v1/generate";

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Set request headers
            connection.setRequestProperty("x-api-key", "we-l0v3-v1et-qr");
            connection.setRequestProperty("Content-Type", "application/json");
            // Enable input/output streams
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Define the JSON request body
            String requestBody = "{\n"
                    + "    \"accountNo\": \"0010169616666\",\n"
                    + "    \"accountName\": \"Doan Manh Tai\",\n"
                    + "    \"acqId\": \"970422\",\n"
                    + "    \"addInfo\": \"Thanh Toán Order eshop\",\n"
                    + "    \"amount\":" + "\"" + (int) amount * 1000 + "\",\n"
                    + "    \"format\": \"vietqr_net\"\n"
                    + "}";

            // Write the request body to the output stream
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            JsonObject jsonResponseObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            String qrDataURL = jsonResponseObject.getAsJsonObject("data").get("qrDataURL").getAsString();

            connection.disconnect();
            return qrDataURL;
            // Close the connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
