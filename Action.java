package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Action {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[1;32m";   //movida texto coloraines
    public static final String ANSI_YELLOW = "\u001B[1;33m";
    public static final String ANSI_BLUE = "\u001B[1;34m";

    public static Card DeckCard() {          //lo que te da una carta random
        Random rand = new Random();
        Card c = new Card();
        c.skips=false;       //por defecto no skippea OMG ESTO LO PUEDO CAMBIAR PARA NO VICIARLO TANTO LUEGO!! REVELACION
                             //MIENTRAS ESCRIBO COMENTARIOS CHORRAS OMG!!!!!!!!!!!!!!!!!!!!
        int tipo = rand.nextInt(100); //numero random del 0 al 100

        int colors = rand.nextInt(4); //4 colores a ver que sale en random

        if (colors == 0) c.color = "blue";
        if (colors == 1) c.color = "red";
        if (colors == 2) c.color = "yellow";
        if (colors == 3) c.color = "green";

        if (tipo >= 78) { //especiales 22% (del 78 al 100% total)


            int symbol = rand.nextInt(3);            //3 especiales diferentes
            if (symbol == 0) c.num = 0; //pasar turno 'ø'
            if (symbol == 1) c.num = 10; // +2
            if (symbol == 2) c.num = 11; // inverso


        }
        if (tipo <= 71) { //numericos
            while (c.num == 0) {              //me cuenta el 0 pero no me da la gana poner la carta 0... debería?
                c.num = rand.nextInt(9); //del 0 al 9 pero si no es 0 se revierte. celebro ga-la-xia
            }


        }

        if (tipo > 71 && tipo < 78) { //cambio de color que hay 2 cambios de color y prob. 7% (78-71 del 100 total)
            int cc = rand.nextInt(2);
            if (cc == 0) c.num = 12; // cambio de color
            if (cc == 1) c.num = 13; // +4

            c.color = "cc"; //no tiene color inicial el cambio de color
        }

        return (c); //pasa la carta nano

    }

    public static Card Robar(ArrayList hand) {

        Card carta = DeckCard(); //robas carta aleaotira

        hand.add(carta);  //metes la carta en tu mano

        return (carta);  //y la carta.

    }


    public static void ver_mano(ArrayList hand) {  //ver_mano
        System.out.println("Tu mano:");
        int cont = 1;   //numerito delante de cada carta

        for (Card carta : (Iterable<Card>) hand) {  //lo de los socios pero el intellij me dice que asi mas corto (no entiendo pero funciona)
            System.out.print(cont + ":  ");
            colorines(carta);   //función del futuro *musica de regreso al futuro*
            cont++;  //pos un contador naomi
        }
    }

    public static Card play_card(ArrayList hand, Card topcard, ArrayList handIA) { //he hecho otra porque si no no imprime
        System.out.println("¿Que carta quieres jugar?");                          //pero podria ponerle un booleano de player true if maquina false
        Scanner input = new Scanner(System.in);                                   //ya veremos qué hago xd
        boolean uno=false;  //si no dices uno a la pinga vas
        boolean validvalid=false;     //parezco adria con los booleanos ()

        int p = 1; //la carta a testear a ver si nos las quieres colar o no

        boolean saltar; //skipea turno??? esto lo controla nice
        boolean valid = false; //otro valid de mierda, yokese (carta valida o numero para timar)

        while (!valid) {
            boolean valid2 = false; //OTRO BOOLEANO JAJAJAJAJAJA NO ME ACUERDO YA (creo que mira que hayas metido un int)
            while (!valid2) {
                valid2 = true;
                String enteredValue = input.nextLine();  //mete cosa en teclado
                if (hand.size()==1 && !validvalid){   //si queda 1 carta te impide robar--> mentira está arreglado ok
                    if (enteredValue.equals("0")) {   //si robas puedes librarte de decir uno
                        Card robada = Robar(hand);     //robas robada
                        System.out.print("Has robado una carta: ");
                        colorines(robada);

                        if (Especiales.prueba_carta(robada, topcard)){    //es jugable esta carta?
                            System.out.println("¿Quieres jugar esta carta? (S/n)");
                            String Si=input.nextLine();
                            String si=Si.toLowerCase();
                            if (si.equals("s")){    //mayus o minusculas. otra cosa y entiende como 'no'! al loro!!
                                topcard = robada;
                                hand.remove(hand.size() - 1);  //te quita la ultima carta añadida
                                Especiales.special(handIA, robada);
                                topcard=Especiales.cambiocolor(robada, topcard);  //no hace los skips bien
                                if (robada.num==0||robada.num==10||robada.num==11||robada.num==13) topcard.skips=true;
                            }

                        }

                        return (topcard); //a la verga la funcion.. creo?
                    }
                    String u = enteredValue.toLowerCase();   //has dicho uno, bribón?
                    if (u.equals("uno")) {
                        valid2 = false;        //vuelve a pillar cosa
                        uno=true;
                        validvalid=true;       //revierte lo del uno. eso hacía el validvalid! o al reves
                    }
                }
                else {
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
                Card robada=Robar(hand);
                System.out.print("Has robado una carta: ");
                colorines(robada);

                if (Especiales.prueba_carta(robada, topcard)){  //lo mismo de antes pero no se ponerlo en funcion JAJAJ
                    System.out.println("¿Quieres jugar esta carta? (S/n)");
                    String Si=input.nextLine();
                    String si=Si.toLowerCase();
                    if (si.equals("s")){
                        topcard = robada;
                        hand.remove(hand.size() - 1);
                        Especiales.special(handIA, robada);
                        topcard=Especiales.cambiocolor(robada, topcard);
                        if (robada.num==0||robada.num==10||robada.num==11||robada.num==13) topcard.skips=true;
                    }

                }

                return (topcard);

            }

            Card test = (Card) hand.get(p - 1); // [ IMPORTANTE somehow ]

            if (test.color.equals(topcard.color) || test.num == topcard.num || topcard.color.equals("cc") || test.color.equals("cc")) {
                if (hand.size()!=1||uno) {  //si coincide numero y color o es cambiocolor está nice
                    topcard = test;         // a porcierto si no dices uno y te queda una carta esto no pasa
                    hand.remove(p - 1);
                }
               else{
                    System.out.println("No has escrito \"UNO\" antes de tirar la última carta. Robas 1 carta.");
                      Robar(hand);   //pa los pringaos que se olvidan lmao
                }
                valid = true;


                topcard=Especiales.cambiocolor(test, topcard); //enseña topcard

                saltar = Especiales.special(handIA, test); //evalua si skipea
                if (test.num == 13) {  //+4?
                    for (int i = 0; i < 4; i++) {
                        Action.Robar(handIA);   //la ia roba con +4
                    }
                    System.out.println("La máquina ha robado 4 cartas");
                    saltar = true;  //skippea turno
                }
                if (test.num == 10) System.out.println("La máquina ha robado 2 cartas"); //+2

                if (saltar) topcard.skips=true; //skippea

            } else System.out.println("Carta no jugable");

        }

        return (topcard);

    }

    public static Card play_AI(ArrayList hand, Card topcard, ArrayList handPl) {  //aparte
        Random rand = new Random();  //numero random
        boolean saltar;        //hace que se salte el turno del jugador?
        boolean draw = true;   //tiene que robar o no
        boolean no_stop=true;  //hace que siga el bucle hasta que encuentre carta jugable

        int p = 1;
        Card test = (Card) hand.get(p - 1);  //como el humano

        while (p <= hand.size() && no_stop) {   //siempre que no haya encontrado ya carta jugable y tenga cartas en la mano

            test = (Card) hand.get(p - 1);   //primera carta a ver si coincide

            if (Especiales.prueba_carta(test, topcard)) {
                topcard = test;
                hand.remove(p - 1);   //quita carta de la mano
                System.out.print("La máquina ha jugado una carta: ");
                draw=false;     //ya no debe robar
                no_stop=false;  //ya no busca mas cartas (rompe este bucle.) podria haber usado break y no lleno la memoria de variables estúpiads..
            }
            p++;  //sigue buscando
        }

        if (draw) {
            Card robada = Robar(hand);
            System.out.println("La máquina ha robado");
            topcard.skips = false;  //ya no se salta turno
            if (Especiales.prueba_carta(robada, topcard)) {  //a no ser...  que la carta que ha robado sea jugable! la testea
                topcard = robada;
                if (test.num == 13) {   //+4
                    for (int i = 0; i < 4; i++) {
                        Action.Robar(handPl);
                    }
                    System.out.println("Has robado 4 cartas");
                    saltar = true;          //otro saltar
                }
                Especiales.special(handPl, robada);  //mira si es especial
                if (robada.num == 12 || robada.num == 13) {  //cambio de color

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
                if (robada.num == 0 || robada.num == 10 || robada.num == 11 || robada.num == 13) topcard.skips = true;
                System.out.println("La máquina ha jugado la carta que acaba de robar");
                hand.remove(hand.size() - 1);
            }
        }
        if (!draw) {  //si no roba carta del mazo:

            if (test.num == 12 || test.num == 13) {  //cambio de color

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

            colorines(test);  //imprime de coloraines la carta qe ha jugado
            saltar = Especiales.special(handPl, test); //vuelve a evaluar si se skipea el turno ¿¿¿OTRA VEZ????
            //ah, eso en las especiales

            if (test.num == 10) System.out.println("Has robado 2 cartas");  // +2

            if (test.num == 13) {   //+4
                for (int i = 0; i < 4; i++) {
                    Action.Robar(handPl);
                }
                System.out.println("Has robado 4 cartas");
                saltar = true;          //otro saltar
            }

            if (saltar) topcard.skips = true;  //pero skippea o no?? xdxddxd
        }

        return (topcard);
    }

    public static void colorines(Card carta) {  //función mu chuli
        String num = "1";   //numero sin importancia para que no se crashee #javaExperience

        if (carta.num == 0) num = "[ ø ]";    //pasaturno  //lo que imprimirá por pantalla
        if (carta.num == 10) num = "[ +2 ]";  //+2
        if (carta.num == 11) num = "[ ⇆ ]";   //invertir sentido
        if (carta.num == 12) num = "[ cc ]";  //cambio color normal
        if (carta.num == 13) num = "[ +4 ]";  // +4   cambio color

        if (carta.num > 0 && carta.num < 10) num = String.valueOf(carta.num);  //si es del 0 al nueve pos eso

        if (carta.color.equals("blue")) System.out.println(ANSI_BLUE + num + " " + carta.color + ANSI_RESET);
        if (carta.color.equals("yellow")) System.out.println(ANSI_YELLOW + num + " " + carta.color + ANSI_RESET);
        if (carta.color.equals("green")) System.out.println(ANSI_GREEN + num + " " + carta.color + ANSI_RESET);
        if (carta.color.equals("red")) System.out.println(ANSI_RED + num + " " + carta.color + ANSI_RESET);
        if (carta.color.equals("cc")) System.out.println(num);

        /*imprime coloraines correspondientes anoser que sea cambio color PERO si al cc le pones color detecta que tiene color
        y se imprime el color ES MÁGICO me salió de suerte, como no*/
    }
}
