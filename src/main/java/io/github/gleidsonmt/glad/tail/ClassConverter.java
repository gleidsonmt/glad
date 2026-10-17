package io.github.gleidsonmt.glad.tail;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 06/10/2026
 */
public class ClassConverter {

    private final String[][] prefixes = {
            {"w", "-fx-pref-width: "},
            {"min-w", "-fx-min-width: "},
            {"max-w", "-fx-max-width: "},
            {"h", "-fx-pref-height: "},
            {"min-h", "-fx-min-height: "},
            {"max-h", "-fx-max-height: "},
            {"cursor", "-fx-cursor: "},
            {"align", "-fx-alignment: "},
            {"text", "-fx-fill: "},
            {"pd", "-fx-padding: "},
            {"hgap", "-fx-hgap: "},
            {"vgap", "-fx-vgap: "},
            {"spacing", "-fx-spacing: "},
            {"icon", "-icon-color: "},
            {"display", "-fx-content-display: "},
            {"bg", " -fx-background-color: "}
    };

    private final String[] modifiers = {
            "hover", "pressed"
    };

    public void printOnReadme() {
        try {
            // Converts the string path to a Path object
            Path path = Path.of("Tailwind.MD");

            StringBuilder content = new StringBuilder("## Default Prefixes \n");
            content.append("| Selectors | Property | \n");
            content.append("| :--- | :---: | \n");

            for (String[] prefix : prefixes) {
                content.append("|").append(prefix[0]);
                content.append("|").append(prefix[1].replace(":", "")).append("|").append("\n");
            }

            content.append("## State Classes \n");

            for (String modifier : modifiers) {
                content.append("### ").append(modifier).append("\n");
            }

            // Writes the text using UTF-8 encoding by default
            // CREATE: creates the file if it doesn't exist
            // TRUNCATE_EXISTING: overwrites the file if it already exists
            Files.writeString(path, content,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("Error on write on Tailwind.MD: " + e.getMessage());
        }
    }

    public String convert(String className) {
        return convert(prefixes, modifiers, className);
    }

    public String clean(String className) {
//        return className.replaceAll("[\\[\\]]+", "").replace(":", "-");
        return className.contains("[")
                ? className
                .replace(" ", "")
                .replaceAll("[\\[\\]]+", "")
                .replace(":", "-")
                .replace("%", "pct")
                .replaceAll("[^a-z0-9-]+", "")
                :
                className;
//        return className
//                .replace(" ", "")
//                .replaceAll("[\\[\\]]+", "")
//                .replace(":", "-")
//                .replace("%", "pct")
//                .replaceAll("[^a-z0-9-]+", "");
    }

    public String convert(String[][] prefixes, String[] modifiers, String className) {
        String cssSelector = "";

        StringBuilder cssContent = new StringBuilder();
        StringBuilder availablePrefixes = new StringBuilder();
        for (String[] prefix : prefixes) {
            availablePrefixes.append(prefix[0]).append("|");
        }
        availablePrefixes.delete(availablePrefixes.length() - 1, availablePrefixes.length());

        String regex = "\\b(?:([a-zA-Z0-9]+):)?(" + availablePrefixes + ")-(\\[([^]\n" +
                       "]*)]|(-?[a-zA-Z0-9%#\\-]+" +
                       "))\\b?";

        final Pattern pattern =
                Pattern.compile(regex);

        Matcher matcher = pattern.matcher(className);


        if (matcher.matches()) {
            String fullClass = matcher.group(0);
            String modifier = matcher.group(1);
            String prefix = matcher.group(2);

            // Se o grupo 3 (com colchetes) for nulo, pega o grupo 4 (sem colchetes)
            String value = matcher.group(3) != null ? matcher.group(3) : matcher.group(4);

            cssSelector = clean(fullClass);

            StringBuilder ruleBuilder = new StringBuilder();
            boolean validRule;
            boolean function = value.contains("(");

            if (value.contains("[") || value.contains("]")) {
                value = value.replace("[", "").replace("]", "");
            }

            for (String[] p : prefixes) {

                if (prefix.equals(p[0])) {

                    if (prefix.equals("display")) {
                        if (value.equals("graphic")) {
                            value = "graphic-only";
                        }

                        if (value.equals("text")) {
                            value = "text-only";
                        }
                    }

                    if (prefix.equals("w") || prefix.equals("h") || prefix.equals("max-h") || prefix.equals("min-h") || prefix.equals("max-w")) {
                        if (!isNumber(value)) break;
                    }

                    if (prefix.equals("bg")) {

                        if (isCustomColor(value)) {
                            value = "-" + value;
                        } else if (!isColor(value) && !function) {
                            value = "-" + value;
                        }
                    }

                    if (prefix.equals("icon")) {
                        if (!isColor(value)) return "";
                        if (isColor(value) && !function) {
                            value = "-" + value;
                        }
                    }

                    if (prefix.equals("text")) {
                        if (isNumber(value)) {
                            p[1] = "-fx-font-size: ";
                        } else if (isColor(value)) {
                            p[1] = "-text-color: ";
                        } else if (value.equals("accent")) {
                            value = "-fx-" + value;
                        } else {
                            p[1] = "";
                        }

                    }

                    if (modifier != null) {
                        for (String m : modifiers) {
                            if (m.equals(modifier)) {
                                cssSelector = modifier + "-" + prefix + "-" + value.replaceFirst("-", "") + ":" + modifier;
                                break;
                            }
                        }
                    }

                    if (!p[1].isEmpty() && !p[1].isBlank())
                        ruleBuilder.append("\t").append(p[1]).append(value).append(";\n");
                }
            }

            validRule = !ruleBuilder.isEmpty();

            if (validRule) {
                cssContent.append(".").append(cssSelector).append(" {\n")
                        .append(ruleBuilder)
                        .append("}\n\n");
                return cssContent.toString();
            } else {
                return null;
            }
        }
        return null;
    }

    private boolean isNumber(String val) {
        return val.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isCustomColor(String color) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+-[0-9]+");
        return pattern.matcher(color).matches();
    }

    private boolean isColor(String color) {
        color = color.replaceFirst("-", "").replaceAll("[^a-zA-Z]", "");
        try {
            // Tenta converter a String (aceita "red", "#FF0000", "rgb(255,0,0)", etc.)
            Color.web(color);
            return true; // Se não deu erro, a cor é válida
        } catch (IllegalArgumentException e) {
            // O erro foi interceptado aqui porque a string não é uma cor reconhecida
            return false;
        } catch (NullPointerException e) {
            // Opcional: trata o caso da string ser nula
            return false;
        }
    }
}