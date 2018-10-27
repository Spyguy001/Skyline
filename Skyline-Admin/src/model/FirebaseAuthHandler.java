package model;

import io.github.openunirest.http.HttpResponse;
import io.github.openunirest.http.JsonNode;
import io.github.openunirest.http.Unirest;
import org.json.JSONObject;

/**
 * This class interacts with Firebase Authentication to sign in/up users.
 */
public class FirebaseAuthHandler {
    /** Needs to be moved out of here */
    private final String API_KEY = "AIzaSyATG-joGkgdMcFCJnbSAfyLvRe6ft_knS0";
    private final String BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
    private final String VERIFY_ENDPOINT = "verifyPassword";
    private final String CREATE_ENDPOINT = "signupNewUser";

    /**
     * @param email the email of the user
     * @param password the password of the user
     * @return uid if the authentication succeeds
     *
     * Sends a request to Firebase Authentication to see if the email/password is a valid combination.
     * If the authentication succeeds, return the user id of the authenticated user, else raise an exception.
     */
    public String verifyUserAuth(String email, String password) {
        HttpResponse<JsonNode> jsonResponse = sendCredentials(email, password, VERIFY_ENDPOINT);
        int status = jsonResponse.getStatus();
        JSONObject responseBody = jsonResponse.getBody().getObject();

        // success
        if(status == 200) {
            return responseBody.getString("localId");
        }
        // failed and no error given
        if(!responseBody.has("error")) {
            throw new RuntimeException("Error: " + status);
        }
        // failed but firebase provided error info
        JSONObject error = responseBody.getJSONObject("error");
        String errorMessage = error.getString("message");
        switch (errorMessage) {
            case "EMAIL_NOT_FOUND":
                throw new IllegalArgumentException("Couldn't find your email id.");
            case "INVALID_PASSWORD":
                throw new IllegalArgumentException("Wrong password.");
            default:
                throw new RuntimeException("Error: " + errorMessage);
        }
    }

    /**
     * @param email the email id for the account to be made
     * @param password the password for the account to be made.
     * @return uid of the newly created user, if sign up succeeds, else raise an exception.
     *
     * Asks Firebase Authentication to create a new account with the given email/password.
     * Throws an exception if sign up failed.
     */
    public String createUserAcc(String email, String password) {
        HttpResponse<JsonNode> jsonResponse = sendCredentials(email, password, CREATE_ENDPOINT);
        int status = jsonResponse.getStatus();
        JSONObject responseBody = jsonResponse.getBody().getObject();

        // success
        if(status == 200) {
            return responseBody.getString("localId");
        }
        // failed and no error given
        if(!responseBody.has("error")) {
            throw new RuntimeException("Error: " + status);
        }
        // failed but firebase provided error info
        JSONObject error = responseBody.getJSONObject("error");
        String errorMessage = error.getString("message");
        if(errorMessage.equals("EMAIL_EXISTS")) {
            throw new IllegalArgumentException("Email already taken.");
        }
        else if(errorMessage.startsWith("WEAK_PASSWORD")) {
            throw new IllegalArgumentException("Password should be at least 6 characters.");
        }
        else {
            throw new RuntimeException("Error: " + errorMessage);
        }
    }

    /**
     * @param email the email id to send
     * @param password the password to send
     * @param endpoint the route endpoint to send credentials to
     * @return the response from the server
     *
     * A helper method that sends an email id and password in JSON format to the given URL endpoint.
     * Returns the response from the rest server.
     */
    private HttpResponse<JsonNode> sendCredentials(String email, String password, String endpoint) {
        return Unirest.post(BASE_URL + endpoint + "?key=" + API_KEY)
                .header("accept", "application/json")
                .field("email", email)
                .field("password", password)
                .field("returnSecureToken", "true")
                .asJson();
    }
}
