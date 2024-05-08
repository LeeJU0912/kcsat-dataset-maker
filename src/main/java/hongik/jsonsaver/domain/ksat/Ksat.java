package hongik.jsonsaver.domain.ksat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Ksat {
    private String type;
    private String question;
    private String translate;
    private String mainText;
    private String choice;
    private String answer;

    public Ksat() {
    }

    public Ksat(String type, String question, String translate, String mainText, String choice, String answer) {
        this.type = type;
        this.question = question;
        this.translate = translate;
        this.mainText = mainText;
        this.choice = choice;
        this.answer = answer;
    }
}