package vn.techmaster.fluxh2reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.fluxh2reactive.model.Person;
import vn.techmaster.fluxh2reactive.service.StorageService;

@Controller
@RequestMapping("/file")
public class FileController {
  @Autowired  private StorageService storageService;
  @GetMapping
  public String home() {
    return "upload";
  }

  @PostMapping(value = "/upload", consumes = { "multipart/form-data" })
  public String upload(@ModelAttribute Person person, ModelMap modelMap, Model model) {
    modelMap.addAttribute("person",person);

    storageService.uploadFile(person.getPhoto());
    model.addAttribute("name", person.getName());   
    model.addAttribute("photo", person.getPhoto().getOriginalFilename());
    return "success";
  }
}
