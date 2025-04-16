package com.devjonadams.markov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Model {

    protected int order;
    protected float prior;
    protected ArrayList<String> alphabet;
    protected HashMap<String, ArrayList<String>> observations;
    protected HashMap<String, ArrayList<Float>> chains;

    public Model(Collection<String> inData, int inOrder, float inPrior, ArrayList<String> inAlphabet) {

        this.prior = inPrior;
        this.order = inOrder;
        this.alphabet = inAlphabet;

        this.observations = new HashMap<>();
        this.train(inData);
        this.buildChains();
    }

    protected void train(Collection<String> inData) {

        while (!inData.isEmpty()) {
            String dataVal = inData.iterator().next();
            dataVal = ("#".repeat(this.order) + dataVal + "#");
            for (int i = 0; i < (dataVal.length() - this.order); i++) {
                var key = dataVal.substring(i, i + this.order);
                var value = observations.get(key);
                if (value.isEmpty()) {
                    value = new ArrayList<>();
                    observations.put(key, value);
                }
                value.add(dataVal.charAt(i + this.order) + "");
            }
        }
    }

    public String generate(String inContext) {
        assert !inContext.isEmpty();
        var chain = this.chains.get(inContext);
        if (chain == null) {
            return null;
        }

        assert !chain.isEmpty();
        return alphabet.get(selectIndex(chain));
    }

    public void retrain(Collection<String> inData) {
        assert !inData.isEmpty();
        this.train(inData);
        this.buildChains();
    }

    protected void buildChains() {
        this.chains = new HashMap<>();

        for (var context : this.observations.keySet()) {
            for (String prediction : this.alphabet) {
                var value = this.chains.get(context);
                if (value != null) {
                    this.chains.put(context, value);
                }

                if (value == null) {
                    value = new ArrayList<>();
                }

                value.add(this.prior + Model.countMatches(this.observations.get(context), prediction));

            }
        }
    }

    protected static int countMatches(ArrayList<String> inArray, String v) {
        if (inArray.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (String s : inArray) {
            if (s.equals(v)) {
                count++;
            }
        }

        return count;
    }

    protected static int selectIndex(ArrayList<Float> inChain) {
        ArrayList<Float> totals = new ArrayList<>();
        float accumulator = 0.0f;

        for (Float weight : inChain) {
            accumulator += weight;
            totals.add(accumulator);
        }

        var rand = Math.random() * accumulator;
        for (int i = 0; i < totals.size(); i++) {
            if (rand < totals.get(i)) {
                return i;
            }
        }

        return 0;
    }
}
