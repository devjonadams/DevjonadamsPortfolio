package com.devjonadams.markov;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

// TODO: Add regex handling to generateName and generateNames
// TODO: Add time-handling to generateNames
public class NameGenerator {

    private Generator generator;

    public NameGenerator(Collection<String> inData, int inOrder, float inPrior, boolean inBackoff) {
        this.generator = new Generator(inData, inOrder, inPrior, inBackoff);
    }

    public NameGenerator(Collection<String> inData, int inOrder, float inPrior) {
        this(inData, inOrder, inPrior, false);
    }

    public String generateName(int minLength, int maxLength, String startsWith, String endsWith, String includes, String excludes) {
        String nameGenerated = "";

        nameGenerated = generator.generate();
        nameGenerated = nameGenerated.replace("#", "");

        if (isNameValid(nameGenerated, minLength, maxLength, startsWith, endsWith, includes, excludes)) {
            return nameGenerated;
        }
        return "";
    }

    private boolean isNameValid(String input, int minLength, int maxLength, String startsWith, String endsWith, String includes, String excludes) {
        return (input.length() >= minLength && input.length() <= maxLength
                && input.startsWith(startsWith) && input.endsWith(endsWith)
                && (includes == null || input.contains(includes))
                && (excludes == null || !input.contains(excludes)));
    }

    public ArrayList<String> generateNames(int inNumNames, int minLength, int maxLength, String startsWith, String endsWith, String includes, String excludes) {
        ArrayList<String> names = new ArrayList<>();

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime currentTime = LocalDateTime.now();

        while (names.size() < inNumNames) {
            String generatedName = generateName(minLength, maxLength, startsWith, endsWith, includes, excludes);
            if (!generatedName.isEmpty()) {
                names.add(generatedName);
            }

            currentTime = LocalDateTime.now();
        }
        return names;
    }
}
