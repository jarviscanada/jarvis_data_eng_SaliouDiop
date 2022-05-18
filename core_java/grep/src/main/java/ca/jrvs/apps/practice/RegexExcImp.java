package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {

    public boolean matchJpeg(String fileName) {
        return fileName.matches("(.*)\\.(jpeg|jpg)");
    }

    public boolean matchIp(String ip) {
        return ip.matches("(\\d{1,3}\\.){3}\\d{1,3}");
    }

    public boolean isEmptyLine(String line) {
        return line.trim().isEmpty();
    }

}
