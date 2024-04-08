package hongik.jsonsaver.domain.ksat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class KsatRepository {
    private static final ConcurrentHashMap<Long, LinkedHashMap> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public Ksat save(Ksat ksat) {
        ksat.fixChoiceNumberFont();

        LinkedHashMap<String, String> temp = new LinkedHashMap<>();
        temp.put("문제 유형", ksat.getType());
        temp.put("문제", ksat.getQuestion());
        temp.put("본문", ksat.getMainText());
        temp.put("보기", ksat.getChoice());
        temp.put("정답", ksat.getAnswer());

        store.put(sequence.incrementAndGet(), temp);
        return ksat;
    }

    public void makeFile() {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\Apple\\IdeaProjects\\json-saver\\src\\main\\resources\\dataset.json")) {
            ObjectMapper objectMapper = new ObjectMapper();

            String jsonStr = objectMapper.writeValueAsString(store);

            fileWriter.write(jsonStr);
            fileWriter.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public AtomicLong getSize() {
        return sequence;
    }

    public void clearStore() {
        store.clear();
    }
}
