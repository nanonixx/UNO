package com.company;

import java.util.Scanner;

public class Instruct {

    public static void reglas() throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("\u001B[36m~~ REGLAS DEL JUEGO ~~\u001B[0m\n\nEsto una versión del juego de cartas UNO™ en la que un jugador humano juega contra la máquina.");
        int menu=0;

        while (menu!=5) {
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
            Thread.sleep(1000);


            }






        }
    }

