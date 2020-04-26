package com.company;

import java.util.ArrayList;

public class Game {

    public static void start_game(ArrayList<Card> hand_player, ArrayList<Card> hand_comp) {
        for (int i = 0; i < 7; i++) {    //empezamos con 7 cartas cadascuno
            robar(hand_player);
            robar(hand_comp);

        }
    }

    public static Card robar(ArrayList hand) {

        Card carta = Card.Shuffle(); //robas carta aleaotira

        hand.add(carta);  //y metes la carta en tu mano

        return (carta);
    }




}
