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
            System.out.println("3. Ver historial del juego anterior");
            System.out.println("4. Salir del juego\n");
            System.out.println("¡Recuerda decir \"UNO\" antes de jugar tu última carta!");  //PROTIP!

            menu = input.nextInt();

            switch (menu) {
                case 1:
                    show_rules();
                    break;
                case 3:
                    File.log_reader();
                    break;
                case 4:
                    System.exit(0);
            }
            Thread.sleep(1000);
        }
    }

    public static void game_over(ArrayList<Card> hand_comp, ArrayList<Card> hand_player){
        if (hand_comp.size()==0) System.out.println("Lo siento, te ha ganado una máquina, pringao");
        if (hand_player.size()==0) System.out.println("Eres la pera limonera, buen trabajo");
    }

    public static void show_rules() throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("\u001B[36m~~ REGLAS DEL JUEGO ~~\u001B[0m\n\nEsto una versión del juego de cartas UNO™ en la que un jugador humano juega contra la máquina.");
        int menu = 0;

        while (menu != 5) {
            System.out.println("\nÍndice:");
            System.out.println("\n1. Comienzo del juego");
            System.out.println("2. Decurso del juego");
            System.out.println("3. Objetivo del juego");
            System.out.println("4. Cartas especiales\n");
            System.out.println("5. VOLVER AL MENÚ PRINCIPAL");

            menu = input.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("\u001B[36m\nCOMIENZO DEL JUEGO\u001B[0m");
                    System.out.println("\nCada jugador empieza con 7 cartas en su mano. Habrá un mazo para robar y otro mazo de juego, cuya primera carta será una carta aleatoria del mazo de robar (TOP CARD). El primer turno será siempre del jugador humano.");
                    break;

                case 2:
                    System.out.println("\u001B[36m\nDECURSO DEL JUEGO\u001B[0m");
                    System.out.println("\nEn cada turno el jugador tiene dos opciones: jugar una carta de cambio de color o que tenga el mismo valor o color que la carta del mazo de juego (TOP CARD) seleccionando el número al lado de dicha carta; o robar una carta del mazo para robar (escribiendo “0” en el teclado).");
                    System.out.println("\nSi al robar una carta dicha carta es jugable, existe la opción de jugarla en ese momento.");
                    System.out.println("\nAntes de jugar la última carta se tendrá que escribir “UNO” en el teclado, de lo contrario la útlima carta no se jugará, se robará una carta del mazo y se saltará el turno.");
                    break;


                case 3:
                    System.out.println("\u001B[36m\nOBJETIVO DEL JUEGO\u001B[0m");
                    System.out.println("\nGanará el jugador que antes se quede sin cartas en su mano. En todo momento se podrá ver el número de cartas que tiene la máquina.");
                    break;

                case 4:
                    System.out.println("\u001B[36m\nCARTAS ESPECIALES\u001B[0m");
                    System.out.println("\nHay algunas cartas que hacen acciones especiales al jugarlas:");
                    System.out.println("\nTOMA DOS [ +2 ] : Si es jugada el otro jugador deberá robar dos cartas y se le saltará el turno.");
                    System.out.println("\nPASA TURNO [ ø ]: Si es jugada se saltará el turno del otro jugador.");
                    System.out.println("\nCAMBIO DE SENTIDO [ ⇆ ] : Si es jugada se cambiará el sentido de juego. En este caso, al ser solamente dos jugadores funciona igual que el “pasa turno”.");
                    System.out.println("\nCAMBIO DE COLOR [ cc ]: Si es jugada el jugador podrá elegir el color que quiere que esté en juego.");
                    System.out.println("\nMÁS CUATRO [ +4 ]: Si es jugada el otro jugador deberá robar cuatro cartas y se saltará el turno. Además el jugador podrá elegir el color que quiere que esté en juego.");
                    break;
            }
            if (menu != 5) Thread.sleep(3500);


        }

    }
}
