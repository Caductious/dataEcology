package org.example;

import java.io.IOException;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args){
        String link1 = "https://rutube.ru/video/26492bdd30eda9821fe906a0ff9e0e00/";//Изначальная ссылка
        String link2 = "https://rutube.ru/video/26492bdd30eda9821fe906a0ff9fb384";//Повторный запуск после ошибки


        String[] link1Parts = link1.split("/");
        String[] link2Parts = link2.split("/");
        String num1Hex = link1Parts[4].toUpperCase();
        String num2Hex = link2Parts[4].toUpperCase();
        BigInteger num1 = new BigInteger(num1Hex,16);
        BigInteger num2 = new BigInteger(num2Hex,16);
        //System.out.println(num1.min(num2));

        System.out.println("Количество рабочих ссылок rutube:" + Counter.counter(num2));
    }
}