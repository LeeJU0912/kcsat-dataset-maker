package hongik.jsonsaver.domain.ksat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ksat {
    private String type;
    private String question;
    private String mainText;
    private String choice;
    private String answer;

    public Ksat() {
    }

    public Ksat(String type, String question, String mainText, String choice, String answer) {
        this.type = type;
        this.question = question;
        this.mainText = mainText;
        this.choice = choice;
        this.answer = answer;
    }

    public void fixChoiceNumberFont() {
        StringBuilder stringBuilder = new StringBuilder(choice);

        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '①') {
                stringBuilder.replace(i, i + 1, "(");
                stringBuilder.insert(i + 1, "1)");
            }
            else if (stringBuilder.charAt(i) == '②') {
                stringBuilder.replace(i, i + 1, "(");
                stringBuilder.insert(i + 1, "2)");
            }
            else if (stringBuilder.charAt(i) == '③') {
                stringBuilder.replace(i, i + 1, "(");
                stringBuilder.insert(i + 1, "3)");
            }
            else if (stringBuilder.charAt(i) == '④') {
                stringBuilder.replace(i, i + 1, "(");
                stringBuilder.insert(i + 1, "4)");
            }
            else if (stringBuilder.charAt(i) == '⑤') {
                stringBuilder.replace(i, i + 1, "(");
                stringBuilder.insert(i + 1, "5)");
            }
        }

        this.choice = stringBuilder.toString();
    }
}