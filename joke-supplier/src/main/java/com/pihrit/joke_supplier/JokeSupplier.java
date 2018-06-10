package com.pihrit.joke_supplier;

import java.util.Random;

public class JokeSupplier {

    public final String allJokes[] = new String[]{
            "Why are astronauts so calm? \n- No pressure in vacuum.",
            "What do you call a programmer from Scandinavia? \n- Nerdic.",
            "A programmer had a problem. He decided to user Java to solve it. \n-He now has a ProblemFactory"
    };

    public String getJoke() {
        return allJokes[getRandomIndex()];
    }

    private int getRandomIndex() {
        return new Random().nextInt(allJokes.length);
    }

}
