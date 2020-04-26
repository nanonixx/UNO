package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Machine {

    public static Card play_AI(ArrayList hand, Card topcard, ArrayList handPl) {  //aparte
        Random rand = new Random();  //numero random
        boolean saltar;        //hace que se salte el turno del jugador?
        boolean draw = true;   //tiene que robar o no
        boolean no_stop=true;  //hace que siga el bucle hasta que encuentre carta jugable

        int p = 1;
        Card test = (Card) hand.get(p - 1);  //como el humano

        while (p <= hand.size() && no_stop) {   //siempre que no haya encontrado ya carta jugable y tenga cartas en la mano

            test = (Card) hand.get(p - 1);   //primera carta a ver si coincide

            if (Card.test(test, topcard)) {
                topcard = test;
                hand.remove(p - 1);   //quita carta de la mano
                System.out.print("La máquina ha jugado una carta: ");
                draw=false;     //ya no debe robar
                no_stop=false;  //ya no busca mas cartas (rompe este bucle.) podria haber usado break y no lleno la memoria de variables estúpiads..
            }
            p++;  //sigue buscando
        }

        if (draw) {
            Card robada = Game.robar(hand);
            System.out.println("La máquina ha robado");
            topcard.skips = false;  //ya no se salta turno
            if (Card.test(robada, topcard)) { //la testea
                topcard = robada;
                if (test.num == 13) {   //+4
                    for (int i = 0; i < 4; i++) {
                        Game.robar(handPl);
                    }
                    System.out.println("Has robado 4 cartas");
                    saltar = true;          //otro saltar
                }
                if (robada.special==true)Card.special_action(handPl, robada);  //mira si es especial
                if (robada.cc==true) {  //cambio de color

                    int rnd = rand.nextInt(4); //color random

                    switch (rnd) {
                        case 0:
                            topcard.color = "red";
                            break;
                        case 1:
                            topcard.color = "blue";
                            break;
                        case 2:
                            topcard.color = "yellow";
                            break;
                        case 3:
                            topcard.color = "green";
                            break; }


                }   //cartas que saltan turno siguiente:
                if (robada.num == -1 || robada.num == 10 || robada.num == 11 || robada.num == 13) topcard.skips = true;
                System.out.println("La máquina ha jugado la carta que acaba de robar");
                hand.remove(hand.size() - 1);
            }
        }
        if (!draw) {  //si no roba carta del mazo:

            if (test.cc==true) {  //cambio de color

                int rnd = rand.nextInt(4);

                switch (rnd) {
                    case 0:
                        topcard.color = "red";
                        break;
                    case 1:
                        topcard.color = "blue";
                        break;
                    case 2:
                        topcard.color = "yellow";
                        break;
                    case 3:
                        topcard.color = "green";
                        break;
                }
            }

            Display.colorines(test);  //imprime de coloraines la carta qe ha jugado
            saltar = Card.special_action(handPl, test); //vuelve a evaluar si se skipea el turno ¿¿¿OTRA VEZ????
            //ah, eso en las especiales

            if (test.num == 10) System.out.println("Has robado 2 cartas");  // +2

            if (test.num == 13) {   //+4
                for (int i = 0; i < 4; i++) {
                    Game.robar(handPl);
                }
                System.out.println("Has robado 4 cartas");
                saltar = true;          //otro saltar
            }

            if (saltar) topcard.skips = true;
        }

        return (topcard);
    }

    public static Card turn(Card topcard, ArrayList<Card> hand_player, ArrayList<Card> hand_comp) throws InterruptedException {
        System.out.println("\u001B[36m\n~~~ TURNO DE LA MÁQUINA ~~~\n"+"\u001B[0m");
        //Action.ver_mano(hand_comp);
        Thread.sleep(2000);          //la cpu hace que piensa pero no. es para darle fluidez emberdat

        topcard = play_AI(hand_comp, topcard, hand_player);

        System.out.println("Cartas restantes de la máquina: "+hand_comp.size());    //pa cotillearle
        if (hand_comp.size()==1) System.out.println("\nMáquina: ¡UNO! >:)");
        Thread.sleep(1000);    //hace que piensa otra vez xd
        System.out.print("\nTOP CARD: ");

        Display.colorines(topcard);
        return(topcard);
    }

}

