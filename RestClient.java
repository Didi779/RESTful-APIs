
package restful.apis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users/";

    public static void main(String[] args) {
        try {
            // Prompt user for input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter user ID: ");
            String userIdInput = reader.readLine();
            int userId = Integer.parseInt(userIdInput);

            // Make a GET request to the API
            URL url = new URL(API_URL + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the request was successful (status code 200)
            if (connection.getResponseCode() == 200) {
                // Read and display the response
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = responseReader.readLine()) != null) {
                    response.append(line);
                }
                responseReader.close();

                // Parse and display user information
                System.out.println("User Information:");
                System.out.println(parseUserInformation(response.toString()));
            } else {
                System.out.println("Error: Unable to fetch user information. HTTP status code: " + connection.getResponseCode());
            }

            // Close the connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parseUserInformation(String jsonResponse) {
        // Here, you would use a JSON library (e.g., Jackson, Gson) to parse the JSON response
        // For simplicity, we'll just print the raw response
        return jsonResponse;
    }
}
