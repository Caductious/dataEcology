package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Counter {
    public static int counter(BigInteger num1) throws IOException, InterruptedException {
        int count = 0;
        try{
        for(long i=0; i<100000; i++){
            String linkEnd = (num1.add(new BigInteger(String.valueOf(i)))).toString(16);
            String url = "https://rutube.ru/video/" + linkEnd;
            HttpClient client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL).build();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                count++;
                System.out.println("Видео работает: " + url);
            }
            writeLog(url, response.statusCode());
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }


    public static void writeLog(String link, int statusCode) throws IOException {
        FileWriter writer = new FileWriter("dataEcologyLog.txt", true);
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = time.format(format);
        writer.write(link + ";" + statusCode + ";" + formattedDate + "\n");
        writer.flush();
    }
}