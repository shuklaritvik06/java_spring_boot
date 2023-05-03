package com.ritvik.cli.commands;

import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@ShellComponent
@ShellCommandGroup(value = "Random Jokes")
public class GetRandomJokes {
    private static final String GET_URL = "https://api.api-ninjas.com/v1/jokes";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "FVj7j4LP4LA9Nnexpp6mMg==2POcf24XLXklpF1U";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    @ShellMethod(key = "joke",value = "get random jokes")
    private void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("X-Api-Key", API_KEY);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String data = response.substring(1,response.length()-1);
            JSONObject json = new JSONObject(data);
            System.out.println(ANSI_YELLOW+json.toString(4)+ANSI_RESET);
        } else {
            System.out.println("Some Error Occurred.");
        }

    }
}
