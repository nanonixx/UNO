package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);

        Display.show_menu();

        ArrayList<Card> hand_player = new ArrayList<>();  //mano jugador
        ArrayList<Card> hand_comp = new ArrayList<>();    //mano cpu

        boolean game = true;
        boolean turno = true;
        boolean saltar;

        Game.start_game(hand_player, hand_comp);
        Card topcard = Card.Shuffle();
        System.out.println("TOP CARD: ");
        Display.colorines(topcard);

        while (game) {
            if (turno) topcard=Player.turn(topcard, hand_player, hand_comp);
            else topcard=Machine.turn(topcard, hand_player, hand_comp);

            turno = !turno;

            if (topcard.skips) {
                turno = !turno;
                System.out.println("Se ha saltado el turno");
                topcard.skips = false; //se revierte el valor
            }

            if (hand_comp.size() == 0 || (hand_player.size() == 0)) game = false;
        }
        Display.game_over(hand_comp, hand_player);
    }

}