package vn.techmaster.fluxh2reactive.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/filerest")

public class FileRestController {


  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<String> restupload(@RequestBody Flux<Part> parts) {
    return partFluxDescription(parts);
  }

  private static Mono<String> partFluxDescription(Flux<? extends Part> partsFlux) {
    return partsFlux.log().collectList().map(FileRestController::partListDescription);
  }

  private static String partListDescription(List<? extends Part> parts) {
    return parts.stream().map(FileRestController::partDescription)
        .collect(Collectors.joining(",", "[", "]"));
  }

  private static String partDescription(Part part) {
    return part instanceof FilePart ? part.name() + ":" + ((FilePart) part).filename() : part.name();
  }

}
