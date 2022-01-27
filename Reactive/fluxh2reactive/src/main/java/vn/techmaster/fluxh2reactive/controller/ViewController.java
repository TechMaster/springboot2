package vn.techmaster.fluxh2reactive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import lombok.RequiredArgsConstructor;
import vn.techmaster.fluxh2reactive.service.MessageService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
  private final MessageService messageService;

  @GetMapping()
  public String index(final Model model) {

    // loads 1 and display 1, stream data, data driven mode.
    //https://www.thymeleaf.org/apidocs/thymeleaf-spring5/3.0.5.M3/org/thymeleaf/spring5/context/webflux/IReactiveDataDriverContextVariable.html
    //https://stackoverflow.com/questions/55163728/an-error-happened-during-template-parsing
    IReactiveDataDriverContextVariable reactiveDataDrivenMode =
            new ReactiveDataDriverContextVariable(messageService.latestMessages(), 1);

    model.addAttribute("messages", reactiveDataDrivenMode);

    // classic, wait repository loaded all and display it.
    //model.addAttribute("movies", movieRepository.findAll());

    return "index";

}


}
