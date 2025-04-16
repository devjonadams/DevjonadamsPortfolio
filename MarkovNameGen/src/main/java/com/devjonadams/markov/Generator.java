package com.devjonadams.markov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class Generator {

    public Generator() {}

    protected int order = 0;
    protected float prior = 0.0f;
    protected boolean backoff = false;
    protected ArrayList<Model> models = new ArrayList<>();

    public Generator(Collection<String> inData, int inOrder, float inPrior, boolean inBackoff) {
        if (inData.isEmpty() || inOrder < 1 || inPrior < 0) {
            throw new IllegalArgumentException("Parameters must contain acceptable values");
        }

        this.order = inOrder;
        this.prior = inPrior;
        this.backoff = inBackoff;

        // Get all the unique letters in the alphabet of provided data words.
        LinkedHashSet<String> setAlphabet = new LinkedHashSet<>();
        for (String word : inData) {
            for (char letter : word.toCharArray()) {
                setAlphabet.add(String.valueOf(letter));
            }

        }

        // Sort the alphabet of strings.
        setAlphabet = setAlphabet.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        ArrayList<String> listDomain = new ArrayList<>(setAlphabet);
        listDomain.addFirst("#");

        models = new ArrayList<>();
        if (this.backoff) {
            for(int i = 0; i <= this.order; i++) {
                // Add models from Highest to Lowest order.
                models.add(new Model(inData, (this.order - i), this.prior, listDomain));
            }
        }
        else {
            models.add(new Model(inData, this.order, this.prior, listDomain));
        }
    }

    public String generate() {
        String word = "#".repeat(this.order);
        String letter = getLetter(word);
        while(!letter.equals("#") && !letter.isEmpty()) {
            word += letter;
            letter = getLetter(word);
        }

        return word;
    }

    protected String getLetter(String word) {
        if (word.isEmpty()) {
            throw new IllegalArgumentException("Parameter must contain at least one letter");
        }

        String letter = "", context;
        context = word.substring((word.length() - this.order));
        for (Model model : this.models) {
            letter = model.generate(context);
            if (letter.isEmpty() || letter.equals("#")) {
                context = context.substring(1);
            }
            else {
                break;
            }
        }

        return letter;
    }


}
