import kong.unirest.*;

public class CachetStatusFetcher {

    public static void main(String[ args) {
        try {
            HttpResponse<String> response = Unirest.get("https://v3.cachethq.io/status")
                .header("accept", "application/json")  // Explicitly request JSON (if Cachet returns JSON)
                .asString();

            int statusCode = response.getStatus();
            String body = response.getBody();

            System.out.println("Status Code: " + statusCode);
            System.out.println("Response Body: " + body);

            // Process the response body (e.g., parse JSON if it is JSON)
            if (statusCode == 200) {
                // Successful request - parse the JSON response
                // (Requires a JSON parsing library like Jackson or Gson)
                // Example (using Jackson):
                // ObjectMapper mapper = new ObjectMapper();
                // JsonNode root = mapper.readTree(body);
                // ... process the JsonNode to extract the status data ...
            } else {
                System.err.println("Error fetching status: " + statusCode);
            }

        } catch (UnirestException e) {
            System.err.println("Unirest exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                Unirest.shutDown();  // Important:  Shut down Unirest to release resources
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}