package hongik.jsonsaver.controller;

import hongik.jsonsaver.domain.ksat.Ksat;
import hongik.jsonsaver.domain.ksat.KsatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KsatController {
    private final KsatRepository ksatRepository;

    @GetMapping("")
    public String redirect() {
        return "redirect:/ksat/addForm";
    }

    @GetMapping("/ksat/addForm")
    public String addForm(Model model) {
        Long size = ksatRepository.getSize();
        model.addAttribute("size", size);
        model.addAttribute("download", ksatRepository);

        log.info("Connected :)");

        return "addForm";
    }

    @PostMapping("/ksat/addForm")
    public String addData(Ksat ksat, RedirectAttributes redirectAttributes) {
        if (ksatRepository.chkBlankInput(ksat)) {
            redirectAttributes.addAttribute("saveFail", true);
            return "redirect:addForm";
        }
        ksatRepository.save(ksat);
        ksatRepository.makeFile();

        log.info("Num : {}, Question : {}", ksatRepository.getSize(), ksat.getQuestion());

        redirectAttributes.addAttribute("ksatId", ksatRepository.getSize());
        redirectAttributes.addAttribute("saveSuccess", true);
        return "redirect:addForm";
    }

    @GetMapping("/ksat/deleteForm")
    public String deleteForm() {
        return "deleteForm";
    }

    @PostMapping("/ksat/deleteForm")
    public String deleteData(@RequestParam String id, RedirectAttributes redirectAttributes) {
        if (!id.isEmpty() && ksatRepository.deleteData(id)) {
            log.info("Delete ID : {}", id);

            redirectAttributes.addAttribute("idx", id);
            redirectAttributes.addAttribute("deleteSuccess", true);
        }
        else {
            log.info("Delete Error");

            redirectAttributes.addAttribute("deleteFail", true);
        }

        return "redirect:deleteForm";
    }

    @GetMapping("/ksat/download")
    public ResponseEntity<Resource> download() throws MalformedURLException {
        ClassPathResource filePath = new ClassPathResource("src/main/resources/dataset.json");

        UrlResource resource = new UrlResource("file:" + filePath.getPath());

        log.info("Download Complete :)");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "dataset.json")
                .body(resource);
    }
}
