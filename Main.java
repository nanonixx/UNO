package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Card {
    int num;
    String color;
    boolean skips;
}

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";  //vaina de los colores en el texto
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        ArrayList<Card> hand_player = new ArrayList<>();  //mano jugador
        ArrayList<Card> hand_comp = new ArrayList<>();    //mano cpu

        //Action --> [ [num, color], [num, color], [num, color] ]


        int menu=0;

        for (int i = 0; i < 7; i++) {    //empezamos con 7 cartas cadascuno
            Action.Robar(hand_player);
            Action.Robar(hand_comp);
        }

        while (menu!=2) {

            System.out.println("\n1. Ver reglas del juego");
            //System.out.println("2. Robar del mazo");
            System.out.println("2. Comenzar a jugar\n");
            System.out.println("¡Recuerda decir \"UNO\" antes de jugar tu última carta!");  //PROTIP!

            menu = input.nextInt();

            switch (menu) {
                case 1:
                    Instruct.reglas();
                    break;

                /* case 2:
                    Card carta=Action.Robar(hand_player);

                    System.out.println("Has robado una carta");
                    Action.colorines(carta);
                    break; */
            }

        }

        //JUEGO
        boolean game = true;     //sigue el juego?
        boolean turno = true;    //es tu turno?

        Card topcard = Action.DeckCard();  //carta random en la topcard inicial
        System.out.print("TOP CARD: ");
        Action.colorines(topcard);

        boolean saltar;

        while (game) {

            if (turno) { //turno del jugador
                Thread.sleep(1000);
                System.out.println("\u001B[36m\n~~~ TU TURNO ~~~\n"+ANSI_RESET);

                System.out.println("0. ROBAR CARTA\n");  //si le das al 0 roba carta
                Action.ver_mano(hand_player);

                topcard=Action.play_card(hand_player, topcard, hand_comp);
                System.out.println("Tus cartas restantes: "+hand_player.size());

                System.out.print("\nTOP CARD: ");
                Action.colorines(topcard);        //carta jugada por el humano

            }

            else { //turno del ordenador
                System.out.println("\u001B[36m\n~~~ TURNO DE LA MÁQUINA ~~~\n"+ANSI_RESET);
                //System.out.print("Mano máquina: ");
                //Action.ver_mano(hand_comp);
                Thread.sleep(2000);          //la cpu hace que piensa pero no. es para darle fluidez emberdat

                topcard=Action.play_AI(hand_comp, topcard, hand_player);

                System.out.println("Cartas restantes de la máquina: "+hand_comp.size());    //pa cotillearle
                if (hand_comp.size()==1) System.out.println("\nMáquina: ¡UNO! >:)");
                Thread.sleep(1000);    //hace que piensa otra vez xd
                System.out.print("\nTOP CARD: ");
                Action.colorines(topcard);
            }

            turno=!turno;  //cambia de turno
//pero
            if (topcard.skips){  //si se skipea el turno
                turno=!turno;    //pues revierto lo de que se cambia el turno
                System.out.println("Se ha saltado el turno");    //pos eso mismo
                topcard.skips=false;   //vamos a revertir el valor pa que no se ralle y entre en bucle o algo
            }

            if (hand_comp.size()==0 || (hand_player.size()==0)) game=false;  //si tienes 0 cartas a la verga el jeugo
        }

        if (hand_comp.size()==0) System.out.println("Lo siento, te ha ganado una máquina pringao"); //si pierdes sale esto
        if (hand_player.size()==0) System.out.println("Eres la pera limonera, buen trabajo");       //si ganas eres la pera limonera bro


    }


}