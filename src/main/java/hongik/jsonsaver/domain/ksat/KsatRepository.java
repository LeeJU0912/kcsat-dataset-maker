package hongik.jsonsaver.domain.ksat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class KsatRepository {
    private static final ConcurrentHashMap<Long, Ksat> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong();

    public Ksat save(Ksat ksat) {
        ksat.fixChoiceNumberFont();

        store.put(sequence.incrementAndGet(), ksat);
        return ksat;
    }

    public void makeFile() {
        try (FileWriter fileWriter = new FileWriter("/Users/leeju/IdeaProjects/json-saver/src/main/resources/dataset.json")) {
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
