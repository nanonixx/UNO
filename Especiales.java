package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Especiales {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";  //coloraines blablabla
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static boolean special (ArrayList hand, Card test) {  //cartas especiales pero emberdat SOLO pasaturno, inverso y +2
        boolean saltaturno = false;

        if (test.num==0 || test.num==11){  //si es salta turno o inverso
            saltaturno=true;               //se salta el turno
        }

        if (test.num==10){                  //si tira un +2
            for (int i = 0; i < 2; i++) {
                Action.Robar(hand);        //hand player o AI roba 2
            }
            saltaturno=true;                //se salta el turno
        }
        return (saltaturno);
    }

    public static boolean prueba_carta (Card test, Card topcard){  //para ver si la carta es jugable o no
        boolean prueba=false;
        if (test.color.equals(topcard.color) || test.num == topcard.num || topcard.color.equals("cc") || test.color.equals("cc")) {
            prueba=true;
        }
        return (prueba);
    }

    public static Card cambiocolor (Card test, Card topcard){  //hace el cambio de color para humanos sin decir si hace skips
        Scanner input = new Scanner(System.in);                //porque no puedo devolver cartas y booleanos p*uta bdia aiuda
        if (test.num==12 || test.num==13) {
            int N = 0;
            System.out.println("Elige a qué color quieres cambiar: ");

            boolean valid3 = false;   //OTRO VALID?????????????
            while (!valid3) {
                System.out.println(ANSI_RED+"1. Rojo     "+ANSI_BLUE+"2. Azul     "+ANSI_YELLOW+"3. Amarillo     "+ANSI_GREEN+"4. Verde"+ANSI_RESET);
                valid3 = true;   //Ah es para el try-catch, no cuenta
                String enteredValue = input.nextLine();
                try {
                    N = Integer.parseInt(enteredValue);  //pon numeros tt
                } catch (Exception e) {
                    System.out.println("Introduce un número. ");
                    valid3 = false;
                    continue;
                }
                if (N > 4 || N < 1) {
                    System.out.println("Introduce el número que corresponda con el color");
                    valid3 = false;
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
    }  //pos yastaria

}