package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Display {

    public static void colorines(Card carta) {  //printea las cartas con su color
        String num = "1";

        if (carta.num == -1) num = "[ ø ]";    //pasaturno
        if (carta.num == 10) num = "[ +2 ]";  //+2
        if (carta.num == 11) num = "[ ⇆ ]";   //invertir sentido
        if (carta.num == 12) num = "[ cc ]";  //cambio color normal
        if (carta.num == 13) num = "[ +4 ]";  // +4   cambio color

        if (carta.num > -1 && carta.num < 10) num = String.valueOf(carta.num);
        if (carta.color.equals("blue")) System.out.println("\u001B[1;34m" + num + " " + carta.color + "\u001B[0m");
        if (carta.color.equals("yellow")) System.out.println("\u001B[1;33m" + num + " " + carta.color + "\u001B[0m");
        if (carta.color.equals("green")) System.out.println("\u001B[1;32m" + num + " " + carta.color + "\u001B[0m");
        if (carta.color.equals("red")) System.out.println("\u001B[1;31m" + num + " " + carta.color + "\u001B[0m");
        if (carta.color.equals("cc")) System.out.println(num);
    }

    public static void show_menu() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int menu = 0;
        while (menu!=2) {

            System.out.println("\n1. Ver reglas del juego");
            System.out.println("2. Comenzar a jugar");
            System.out.println("3. Salir del juego\n");
            System.out.println("¡Recuerda decir \"UNO\" antes de jugar tu última carta!");  //PROTIP!

            menu = input.nextInt();

            switch (menu) {
                case 1:
                    Rules.show_rules();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    public static void game_over(ArrayList<Card> hand_comp, ArrayList<Card> hand_player){
        if (hand_comp.size()==0) System.out.println("Lo siento, te ha ganado una máquina pringao");
        if (hand_player.size()==0) System.out.println("Eres la pera limonera, buen trabajo");
    }


}
