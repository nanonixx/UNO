package com.company;

import java.io.*;
import java.util.Scanner;

public class File {

    public static void log_reader(){

        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("log.txt"));
            String line = "";
            while((line=inputStream.readLine()) != null) {
                System.out.println(line);
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



    public static void log_writer(Card carta, boolean human, boolean turnend) {
        try {

            FileWriter log = new FileWriter("log.txt", true);
            BufferedWriter outputStream = new BufferedWriter(log);

            if (!turnend) {
                outputStream.write("\nTopcard: ");

                String num = "1";
                if (carta.num > -1 && carta.num < 10) num = String.valueOf(carta.num);
                outputStream.write(num + " " + carta.color+"\n");

                outputStream.write("\nTurno de");
                outputStream.write(human ? "l jugador: \n" : " la máquina: \n");
            } else {
                outputStream.write("Carta jugada: ");

                String num = "1";
                if (carta.num > -1 && carta.num < 10) num = String.valueOf(carta.num);
                outputStream.write(num + " " + carta.color+"\n");
            }

            outputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
