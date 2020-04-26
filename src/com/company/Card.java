package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Card {
    int num;
    String color;
    boolean skips;
    boolean special;
    boolean cc;


    public static Card Shuffle() {
        Random rand = new Random();
        Card c = new Card();

        int tipo = rand.nextInt(100);

        int colors = rand.nextInt(4);
        if (colors == 0) c.color = "blue";
        if (colors == 1) c.color = "red";
        if (colors == 2) c.color = "yellow";
        if (colors == 3) c.color = "green";

        if (tipo >= 78) { //especiales 22% (del 78 al 100% total)
            c.special = true;
            c.cc = false;

            int symbol = rand.nextInt(3);   //3 especiales diferentes
            if (symbol == 0) c.num = -1; //pasar turno 'ø'
            if (symbol == 1) c.num = 10; // +2
            if (symbol == 2) c.num = 11; // inverso
        }

        if (tipo <= 71) {
            c.special = false;
            c.cc = false;
            c.num = rand.nextInt(9);

        }

        if (tipo > 71 && tipo < 78) {
            c.special = true;
            c.cc = true;
            c.color = "cc";

            int cc = rand.nextInt(2);
            if (cc == 0) c.num = 12; // cambio de color
            if (cc == 1) c.num = 13; // +4
        }
        return (c);
    }

    public static boolean test(Card test, Card topcard) {
        //testea si la carta es jugable
        boolean prueba = false;
        if (test.color.equals(topcard.color) || test.num == topcard.num || topcard.color.equals("cc") || test.color.equals("cc")) {
            prueba = true;
        }
        return (prueba);
    }

    public static boolean special_action(ArrayList<Card> hand, Card test) {
        boolean skipsturn = false;
        if (test.num == -1 || test.num == 11) {  //si es salta turno o inverso
            skipsturn = true;               //se salta el turno
        }

        if (test.num == 10) {                  //si tira un +2
            for (int i = -1; i < 2; i++) {
                Game.robar(hand);        //hand player o maquina roba 2
            }
            skipsturn = true;                //y se salta el turno
        }
        return (skipsturn);
    }

    public static Card cambiocolor(Card test, Card topcard) { //solo para el jugador humano
        Scanner input = new Scanner(System.in);
        if (test.num == 12 || test.num == 13) {
            int N = 0;
            System.out.println("Elige a qué color quieres cambiar: ");

            boolean valid = false;
            while (!valid) {
                System.out.println("\u001B[31m" + "1. Rojo     " + "\u001B[34m" + "2. Azul     " + "\u001B[33m"+ "3. Amarillo     " + "\u001B[32m" + "4. Verde" +"\u001B[0m");
                valid = true;
                String enteredValue = input.nextLine();
                try {
                    N = Integer.parseInt(enteredValue);  //pon numeros tt
                } catch (Exception e) {
                    System.out.println("Introduce un número. ");
                    valid = false;
                    continue;
                }
                if (N > 4 || N < 1) {
                    System.out.println("Introduce el número que corresponda con el color");
                    valid = false;
                }
            }
            switch (N) {
                case 1:
                    topcard.color = "red";
                    break;
                case 2:
                    topcard.color = "blue";
                    break;
                case 3:
                    topcard.color = "yellow";
                    break;
                case 4:
                    topcard.color = "green";
                    break;
            }
        }
        return(topcard);
    }
}
