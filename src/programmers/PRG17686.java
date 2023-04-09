package programmers;

import java.util.*;
import java.util.regex.*;

public class PRG17686 {
    public Pattern pattern = Pattern.compile("\\d+");

    public String[] solution(String[] files) {
        Arrays.sort(files, (f1, f2) -> {
            String[] parts1 = getParts(f1);
            String[] parts2 = getParts(f2);

            String head1 = parts1[0];
            String head2 = parts2[0];

            int number1 = Integer.parseInt(parts1[1]);
            int number2 = Integer.parseInt(parts2[1]);

            int headResult = head1.compareTo(head2);

            return headResult == 0 ? number1 - number2 : headResult;
        });

        return files;
    }

    public String[] getParts(String file) {
        String[] parts = new String[2];
        String number = "";

        Matcher matcher = pattern.matcher(file);
        if (matcher.find()) {
            number = matcher.group();
        }

        parts[0] = file.split(number)[0].toLowerCase();
        parts[1] = number;

        return parts;
    }
}
