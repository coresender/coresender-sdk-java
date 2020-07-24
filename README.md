# Coresender Java SDK

This is the officially supported Java library for [Coresender](https://coresender.com). It allows you to quickly and easily integrate with our API and improve your email deliverability.

## Prerequisites

* Java version 11+
* The Coresender service. You can start with a free 100 emails/month developer plan and move to one of our [pricing plans](https://coresender.com/pricing) when you're done.

## Installation

To install the SDK, you will need to be using [Maven](https://maven.apache.org/).

The Coresender Java SDK utilizes [Unirest library](http://kong.github.io/unirest-java/).

Run the following command to get started: 

```bash
mvnw install
```

## Usage

Here's how to send an email using the SDK:

```java
import com.coresender.sdk.Coresender;
import com.coresender.sdk.data.Email;

import java.util.List;

public class Example1 {

  public static void main(String[] args) {
    Coresender coresender = Coresender.builder().accountId("<<INSERT SENDING ACCOUNT ID>>").apiKey("<<INSERT SENDING ACCOUNT API KEY>>").build();
    coresender.sendSimpleEmail(Email.builder()
                                    .from(Email.Address.of("jean.luc@example.com", "Jean-Luc Picard"))
                                    .to(List.of(Email.Address.of("geordi@example.com", "Geordi La Forge")))
                                    .replyTo(List.of(Address.of("info@enterprise.com")))
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
  }
}
```

Here's how to send a batch of emails using the SDK:

```java
import com.coresender.sdk.Coresender;

import java.util.List;

import static com.coresender.sdk.data.Email.Address;
import static com.coresender.sdk.data.Email.builder;

public class Example2 {

  public static void main(String[] args) {
    Coresender coresender = Coresender.builder().accountId("<<INSERT SENDING ACCOUNT ID>>").apiKey("<<INSERT SENDING ACCOUNT API KEY>>").build();

    coresender.addToBatch(builder()
                               .from(Address.of("gandalf@middleearth.com", "Gandalf The Grey"))
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

    coresender.addToBatch(builder()
                               .from(Address.of("saruman@middleearth.com", "Saruman"))
                               .to(List.of(Address.of("dark.lord@example.com", "Sauron")))
                               .replyTo(List.of(Address.of("info@orthanc.com", "Orthanc Tower")))
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
    coresender.execute();
  }
}
```

### Response

The result of an API call is a domain object.

```java
import com.coresender.sdk.Coresender;
import com.coresender.sdk.data.SendEmailResponse;
import kong.unirest.HttpResponse;

public class Example3 {

  public static void main(String[] args) {
    Coresender coresender = Coresender.builder().accountId("<<INSERT SENDING ACCOUNT ID>>").apiKey("<<INSERT SENDING ACCOUNT API KEY>>").build();
    HttpResponse<SendEmailResponse> response = coresender.execute();

    if (response.getBody() == null) {
      log.info("{}\n{}", response.getStatusText(), response.getParsingError());
    } else {
      response.getBody().getData().forEach(data -> log.info(data.toString()));
    }
  }
}
```
Also see [HttpResponse](https://github.com/Kong/unirest-java/blob/main/unirest/src/main/java/kong/unirest/HttpResponse.java) and 
[example application](example/src/main/java/com/coresender/example/CoresenderApplication.java) for more.

### Debug logging

This SDK depends on [Unirest](http://kong.github.io/unirest-java) library which uses [Apache Http Components](https://hc.apache.org).
Enabling debug logging requires configuring logging levels for these libraries.
Sample logback.xml file for [Logback](http://logback.qos.ch):
```xml
<configuration>
    <logger name="org.apache" level="DEBUG"/>
    <logger name="com.coresender.example" level="DEBUG"/>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%date %highlight(%-5level) %cyan(%class{0}:%L) - %msg %n</pattern>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```
You also need to add dependencies to your project:
```xml
<!-- Needed if you want to debug org.apache.httpcomponents -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
    <version>1.7.29</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```
See [example](example/pom.xml) for full configuration.

## Contribute

The Coresender Java SDK is an open-source project released under MIT license. We welcome any contributions!

You can help by:
* Writing new code
* Creating issues if you find problems
* Helping others with their issues
* Reviewing PRs
