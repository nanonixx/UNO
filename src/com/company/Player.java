package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Scanner input = new Scanner(System.in);

    public static void ver_mano(ArrayList hand) {  //ver_mano
        System.out.println("Tu mano:");
        int cont = 1;

        for (Card carta : (Iterable<Card>) hand) { //gracias stackoverflow
            System.out.print(cont + ":  ");
            Display.colorines(carta);
            cont++;
        }
    }

    public static Card play_card(ArrayList hand, Card topcard, ArrayList handIA) {
        Scanner input = new Scanner(System.in);
        boolean uno = false;
        boolean validuno = false;
        boolean skips;

        System.out.println("¿Que carta quieres jugar?");
        int p = 1;

        boolean valid = false;

        while (!valid) {
            boolean valid2 = false;
            while (!valid2) {  //para el try-catch / están bien metidos los datos
                valid2 = true;
                String enteredValue = input.nextLine();

                if (hand.size()==1 && !validuno){

                    if (enteredValue.equals("0")) {   //si robas puedes librarte de decir uno
                        Card robada = Game.robar(hand);     //robas robada
                        System.out.print("Has robado una carta: ");
                        Display.colorines(robada);

                        if (Card.test(robada, topcard)){    //es jugable esta carta?
                            System.out.println("¿Quieres jugar esta carta? (S/n)");
                            String S=input.nextLine();
                            String s=S.toLowerCase();
                            if (s.equals("s")){    //mayus o minusculas. otra cosa y entiende como 'no'!
                                topcard = robada;
                                hand.remove(hand.size() - 1);  //te quita la ultima carta añadida
                                if (robada.special==true) Card.special_action(handIA, robada);
                                if (robada.cc==true) topcard=Card.cambiocolor(robada, topcard);  //no hace los skips bien
                                if (robada.num==-1||robada.num==10||robada.num==11||robada.num==13) topcard.skips=true;
                            }

                        }

                        return (topcard);
                    }
                    String u = enteredValue.toLowerCase();   //has dicho uno, bribón?
                    if (u.equals("uno")) {
                        valid2 = false;        //vuelve a pillar cosa
                        uno=true;
                        validuno=true;       //revierte lo del uno. eso hacía el validvalid! o al reves
                    }
                }
                else{
                    try {              //try catch que evalua si es int
                        p = Integer.parseInt(enteredValue);
                    } catch (Exception e) {
                        System.out.println("Introduce un número. ");
                        valid2 = false;
                        continue;
                    }
                    if (p > hand.size() || p < 0) {
                        System.out.println("Número fuera de rango. ");
                        valid2 = false;
                    }
                }
            }
            if (p == 0) {
                Card robada=Game.robar(hand);
                System.out.print("Has robado una carta: ");
                Display.colorines(robada);

                if (Card.test(robada, topcard)){  //no lo puedo poner en una funcion
                    System.out.println("¿Quieres jugar esta carta? (S/n)");
                    String Si=input.nextLine();
                    String si=Si.toLowerCase();
                    if (si.equals("s")){
                        topcard = robada;
                        hand.remove(hand.size() - 1);
                        Card.special_action(handIA, robada);
                        topcard = Card.cambiocolor(robada, topcard);
                        if (robada.num==-1||robada.num==10||robada.num==11||robada.num==13) topcard.skips=true;
                    }

                }

                return (topcard);

            }
            Card test = (Card) hand.get(p-1); //importante
            if (test.color.equals(topcard.color) || test.num == topcard.num || topcard.color.equals("cc") || test.color.equals("cc")) {
                if (hand.size()!=1||uno) {  //si coincide numero y color o es cambiocolor está nice
                    topcard = test;         // a porcierto si no dices uno y te queda una carta esto no pasa
                    hand.remove(p - 1);
                }
                else{
                    System.out.println("No has escrito \"UNO\" antes de tirar la última carta. Robas 1 carta.");
                    Game.robar(hand);   //pa los pringaos que se olvidan de poner uno
                }
                valid = true;


                topcard = Card.cambiocolor(test, topcard); //enseña topcard

                skips = Card.special_action(handIA, test); //mira si skipea
                if (test.num == 13) {  //+4?
                    for (int i = 0; i < 4; i++) {
                        Game.robar(handIA);   //la ia roba con +4
                    }
                    System.out.println("La máquina ha robado 4 cartas");
                    skips = true;  //skippea turno
                }
                if (test.num == 10) System.out.println("La máquina ha robado 2 cartas"); //+2

                if (skips) topcard.skips=true; //skippea

            } else System.out.println("Carta no jugable");
        }
        return (topcard);
    }

    public static Card turn(Card topcard, ArrayList<Card> hand_player, ArrayList<Card> hand_comp) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("\u001B[36m\n~~~ TU TURNO ~~~\n"+"\u001B[0m");

        System.out.println("0. ROBAR CARTA\n");  //si le das al 0 roba carta
        ver_mano(hand_player);

        topcard=play_card(hand_player, topcard, hand_comp);
        System.out.println("Tus cartas restantes: "+hand_player.size());

        System.out.print("\nTOP CARD: ");
        Display.colorines(topcard);
        return (topcard);
    }

}

