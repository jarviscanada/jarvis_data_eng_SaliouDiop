package ca.jrvs.apps.twitter;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("API_KEY"));
        System.out.println(dotenv.get("API_KEY_SECRET"));
        System.out.println(dotenv.get("ACCESS_TOKEN")) ;
        System.out.println(dotenv.get("ACCESS_TOKEN_SECRET"));
    }

}