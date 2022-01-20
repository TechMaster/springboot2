package vn.techmaster.fluxh2reactive.controller;

import static org.springframework.http.ResponseEntity.created;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.techmaster.fluxh2reactive.model.Message;
import vn.techmaster.fluxh2reactive.model.MessageRequest;
import vn.techmaster.fluxh2reactive.service.MessageService;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Message> messageStream() {
    return this.messageService.latestMessages();
  }

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ResponseEntity<?>> send(@RequestBody MessageRequest request) {
    var message = Message.builder().id(UUID.randomUUID()).body(request.message()).sentAt(
        LocalDateTime.now()).build();
    this.messageService.send(message);
    return Mono.just(created(URI.create("/messages/" + message.getId())).build());
  }
}
