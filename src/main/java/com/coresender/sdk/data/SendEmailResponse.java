package com.coresender.sdk.data;

import java.util.LinkedList;
import java.util.List;

public class SendEmailResponse {

  private LinkedList<Data> data;

  private Meta meta;

  public LinkedList<Data> getData() {
    return this.data;
  }

  public Meta getMeta() {
    return this.meta;
  }

  @Override
  public String toString() {
    return "SendEmailResponse(data=" + this.getData() + ", meta=" + this.getMeta() + ")";
  }

  public static class Data {

    private String messageId;

    private String customId;

    private String status;

    private List<Error> errors;

    private String code;

    public String getMessageId() {
      return this.messageId;
    }

    public String getCustomId() {
      return this.customId;
    }

    public String getStatus() {
      return this.status;
    }

    public List<Error> getErrors() {
      return this.errors;
    }

    public String getCode() {
      return this.code;
    }

    @Override
    public String toString() {
      return "SendEmailResponse.Data(messageId=" + this.getMessageId() + ", customId=" + this.getCustomId() + ", status=" + this.getStatus() + ", errors=" + this
          .getErrors() + ", code=" + this.getCode() + ")";
    }
  }

  public static class Error {

    String code;

    String description;

    String field;

    String value;

    List<Error> errors;

    public String getCode() {
      return this.code;
    }

    public String getDescription() {
      return this.description;
    }

    public String getField() {
      return this.field;
    }

    public String getValue() {
      return this.value;
    }

    public List<Error> getErrors() {
      return this.errors;
    }

    @Override
    public String toString() {
      return "SendEmailResponse.Error(code=" + this.getCode() + ", description=" + this.getDescription() + ", field=" + this.getField() + ", value=" + this.getValue() + ", errors=" + this
          .getErrors() + ")";
    }
  }

  public static class Meta {

    private String rqTime;

    public String getRqTime() {
      return this.rqTime;
    }

    @Override
    public String toString() {
      return "SendEmailResponse.Meta(rqTime=" + this.getRqTime() + ")";
    }
  }
}
