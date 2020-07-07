package com.coresender.example;

import com.coresender.sdk.Coresender;
import com.coresender.sdk.data.Email.Address;
import com.coresender.sdk.data.SendEmailResponse;
import kong.unirest.HttpResponse;
import org.slf4j.Logger;

import java.util.List;

import static com.coresender.sdk.data.Email.builder;
import static org.slf4j.LoggerFactory.getLogger;

public class CoresenderApplication {

  private static final Logger log = getLogger(CoresenderApplication.class);

  public static void main(String... args) {
    Coresender coreSender = Coresender.builder().accountId("<<INSERT SENDING ACCOUNT ID>>").apiKey("<<INSERT SENDING ACCOUNT API KEY>>").build();

    HttpResponse<SendEmailResponse> response = coreSender.sendSimpleEmail(builder().from(Address.of("jean.luc@example.com", "Jean-Luc Picard"))
                                                                                   .to(List.of(Address.of("geordi@example.com", "Geordi La Forge")))
                                                                                   .subject("I need engines")
                                                                                   .bodyText("Geordi, I need engines, now!")
                                                                                   .bodyHtml("<p>Geordi, I need engines, <strong>now!</strong></p>")
                                                                                   .customId("1234-qwerty")
                                                                                   .customIdUnique(false)
                                                                                   .trackClicks(true)
                                                                                   .trackOpens(true)
                                                                                   .listUnsubscribe("https://example.com/unsubscribe/abcd-1234")
                                                                                   .listId("no list")
                                                                                   .build());
    if (response.getBody() == null) {
      log.info("{}\n{}", response.getStatusText(), response.getParsingError());
    } else {
      response.getBody().getData().forEach(data -> log.info(data.toString()));
    }

    coreSender.addToBatch(builder().from(Address.of("gandalf@middleearth.com", "Gandalf The Grey"))
                                   .to(List.of(Address.of("balrog.of.moria@example.com", "Balrog")))
                                   .subject("Passage problems")
                                   .bodyText("You shall not pass!")
                                   .bodyHtml("<p>I'm sorry but you shall <strong>not pass!</strong></p>")
                                   .customId("1234-qwerty")
                                   .customIdUnique(false)
                                   .trackClicks(true)
                                   .trackOpens(true)
                                   .listUnsubscribe("https://example.com/unsubscribe/abcd-1234")
                                   .listId("no list")
                                   .build());

    coreSender.addToBatch(builder().from(Address.of("saruman@middleearth.com", "Saruman"))
                                   .to(List.of(Address.of("dark.lord@example.com", "Sauron")))
                                   .subject("Agenda for the next weeks")
                                   .bodyText("Uruk-hais are ready my Lord, what are the orders?")
                                   .bodyHtml("<p>Uruk-hais are ready my Lord, what are the orders?</p>")
                                   .customId("1234-qwerty")
                                   .customIdUnique(false)
                                   .trackClicks(true)
                                   .trackOpens(true)
                                   .listUnsubscribe("https://example.com/unsubscribe/abcd-1234")
                                   .listId("no list")
                                   .build());


    response = coreSender.execute();

    if (response.getBody() == null) {
      log.info("{}\n{}", response.getStatusText(), response.getParsingError());
    } else {
      response.getBody().getData().forEach(data -> log.info(data.toString()));
    }
  }
}
