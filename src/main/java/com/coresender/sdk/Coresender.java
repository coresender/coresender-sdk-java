package com.coresender.sdk;

import com.coresender.sdk.data.Email;
import com.coresender.sdk.data.SendEmailResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 */
public class Coresender {

  public static final String CORESENDER_SENDING_API_ID = "CORESENDER_SENDING_API_ID";

  public static final String CORESENDER_SENDING_API_KEY = "CORESENDER_SENDING_API_KEY";

  private static final Logger log = getLogger(Coresender.class);

  private static final ObjectMapper mapper = new ObjectMapper();

  private static final String URL = "https://api.coresender.com/v1/send_email";

  static {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
  }

  private final String accountId;

  private final String apiKey;

  private final kong.unirest.ObjectMapper unirestMapper = new JacksonObjectMapper(mapper);

  private final List<Email> batch = new ArrayList<>();

  private Coresender(final String accountId, final String apiKey) {
    if (accountId == null) {
      throw new IllegalArgumentException("accountId is marked non-null but is null");
    }
    if (apiKey == null) {
      throw new IllegalArgumentException("apiKey is marked non-null but is null");
    }
    this.accountId = accountId;
    this.apiKey = apiKey;
  }

  public static String prettyPrintResponse(SendEmailResponse response) {
    return prettyPrint(response);
  }

  private static String prettyPrint(Object object) {
    try {
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    } catch (JsonProcessingException exception) {
      throw new IllegalArgumentException(exception.getCause());
    }
  }

  public static CoresenderBuilder builder() {
    return new CoresenderBuilder();
  }

  public void addToBatch(Email email) {
    batch.add(email);
  }

  public HttpResponse<SendEmailResponse> sendSimpleEmail(Email email) {
    return sendEmailBatch(List.of(email));
  }

  /**
   * @return
   */
  public HttpResponse<SendEmailResponse> execute() {
    HttpResponse<SendEmailResponse> response = sendEmailBatch(batch);
    batch.clear();
    return response;
  }

  private HttpResponse<SendEmailResponse> sendEmailBatch(Collection<Email> emails) {
    log.debug("Sending emails: {}", prettyPrint(emails));
    HttpResponse<SendEmailResponse> response = Unirest.post(URL)
                                                      .basicAuth(accountId, apiKey)
                                                      .body(emails)
                                                      .withObjectMapper(unirestMapper)
                                                      .asObject(SendEmailResponse.class);
    log.debug("Got response: {}", prettyPrint(response.getBody()));
    return response;
  }

  public static class CoresenderBuilder {

    private String accountId;

    private String apiKey;

    CoresenderBuilder() {
    }

    String getEnvironmentVariable(String variable) {
      return System.getenv(variable);
    }

    public CoresenderBuilder accountId(final String accountId) {
      if (accountId == null) {
        throw new IllegalArgumentException("accountId is marked non-null but is null");
      }
      this.accountId = accountId;
      return this;
    }

    public CoresenderBuilder apiKey(final String apiKey) {
      if (apiKey == null) {
        throw new IllegalArgumentException("apiKey is marked non-null but is null");
      }
      this.apiKey = apiKey;
      return this;
    }

    private void setupApiFromEnvironmentVariables() {
      String accountId = getEnvironmentVariable(CORESENDER_SENDING_API_ID);
      if (this.accountId == null && accountId != null) {
        log.debug("Setting up account id from {} variable: {}", CORESENDER_SENDING_API_ID, accountId);
        this.accountId = accountId;
      }
      String apiKey = getEnvironmentVariable(CORESENDER_SENDING_API_KEY);
      if (this.apiKey == null && apiKey != null) {
        log.debug("Setting api key from {} variable: {}", CORESENDER_SENDING_API_KEY, apiKey);
        this.apiKey = apiKey;
      }
    }

    public Coresender build() {
      setupApiFromEnvironmentVariables();
      return new Coresender(this.accountId, this.apiKey);
    }

    @Override
    public String toString() {
      return "Coresender.CoresenderBuilder(accountId=" + this.accountId + ", apiKey=" + this.apiKey + ")";
    }
  }
}
