package hongik.jsonsaver.controller;

import hongik.jsonsaver.domain.ksat.Ksat;
import hongik.jsonsaver.domain.ksat.KsatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Controller
@RequestMapping("/ksat")
@RequiredArgsConstructor
public class KsatController {
    private final KsatRepository ksatRepository;

    @GetMapping("/addForm")
    public String addForm(Model model) {
        AtomicLong size = ksatRepository.getSize();
        model.addAttribute("size", size);
        model.addAttribute("download", ksatRepository);
        return "addForm";
    }

    @PostMapping("/addForm")
    public String addData(Ksat ksat, RedirectAttributes redirectAttributes) {
        ksatRepository.save(ksat);
        ksatRepository.makeFile();

        log.info("Num : {}, Question : {}", ksatRepository.getSize(), ksat.getQuestion());

        redirectAttributes.addAttribute("ksatId", ksatRepository.getSize());
        redirectAttributes.addAttribute("saveStatus", true);
        return "redirect:addForm";
    }

    @GetMapping("/deleteForm")
    public String deleteForm(Model model) {
        return "deleteForm";
    }

    @PostMapping("/deleteForm")
    public String deleteData(@RequestParam String id, RedirectAttributes redirectAttributes) {

        if (ksatRepository.deleteData(id)) {
            log.info("Delete ID : {}", id);

            redirectAttributes.addAttribute("idx", id);
            redirectAttributes.addAttribute("deleteStatus", true);
        }
        else {
            log.info("Delete Error");
        }

        return "redirect:deleteForm";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download() {
        String path = "C:\\Users\\Apple\\IdeaProjects\\json-saver\\src\\main\\resources\\dataset.json";
        File file = new File(path);

        byte[] result;
        ResponseEntity<byte[]> entity = null;

        try {
            result = FileCopyUtils.copyToByteArray(file);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition", "attachment; filename=" + "dataset.json");

            entity = new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
