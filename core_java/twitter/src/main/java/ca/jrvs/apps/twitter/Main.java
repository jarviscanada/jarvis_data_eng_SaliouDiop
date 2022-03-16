package ca.jrvs.apps.twitter;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("API_KEY"));
    }

}
