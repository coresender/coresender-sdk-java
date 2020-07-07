package com.coresender.sdk.data;

import java.util.List;

public class Email {

  private final Address from;

  private final List<Address> to;

  private final String subject;

  private final String customId;

  private final boolean customIdUnique;

  private final boolean trackOpens;

  private final boolean trackClicks;

  private final String listId;

  private final String listUnsubscribe;

  private final Body body;

  private Email(final Body body, final Address from, final List<Address> to, final String subject, final String customId, final boolean customIdUnique, final boolean trackOpens, final boolean trackClicks, final String listId, final String listUnsubscribe) {
    if (from == null) {
      throw new IllegalArgumentException("from is marked non-null but is null");
    }
    if (to == null) {
      throw new IllegalArgumentException("to is marked non-null but is null");
    }
    if (subject == null) {
      throw new IllegalArgumentException("subject is marked non-null but is null");
    }
    this.body = body;
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.customId = customId;
    this.customIdUnique = customIdUnique;
    this.trackOpens = trackOpens;
    this.trackClicks = trackClicks;
    this.listId = listId;
    this.listUnsubscribe = listUnsubscribe;
  }

  public static EmailBuilder builder() {
    return new EmailBuilder();
  }

  public Address getFrom() {
    return this.from;
  }

  public List<Address> getTo() {
    return this.to;
  }

  public String getSubject() {
    return this.subject;
  }

  public String getCustomId() {
    return this.customId;
  }

  public boolean isCustomIdUnique() {
    return this.customIdUnique;
  }

  public boolean isTrackOpens() {
    return this.trackOpens;
  }

  public boolean isTrackClicks() {
    return this.trackClicks;
  }

  public String getListId() {
    return this.listId;
  }

  public String getListUnsubscribe() {
    return this.listUnsubscribe;
  }

  @Override
  public String toString() {
    return "Email(body=" + this.getBody() + ", from=" + this.getFrom() + ", to=" + this.getTo() + ", bodyText=" + this.getBodyText() + ", bodyHtml=" + this.getBodyHtml() + ", subject=" + this
        .getSubject() + ", customId=" + this.getCustomId() + ", customIdUnique=" + this.isCustomIdUnique() + ", trackOpens=" + this.isTrackOpens() + ", trackClicks=" + this
        .isTrackClicks() + ", listId=" + this.getListId() + ", listUnsubscribe=" + this.getListUnsubscribe() + ")";
  }

  public Body getBody() {
    return this.body;
  }

  public String getBodyText() {
    return this.body.text;
  }

  public String getBodyHtml() {
    return this.body.html;
  }

  public static class Address {

    private final String email;

    private final String name;

    private Address(final String email, final String name) {
      this.email = email;
      this.name = name;
    }

    public static Address of(String email, String name) {
      return new Address(email, name);
    }

    public String getEmail() {
      return this.email;
    }

    public String getName() {
      return this.name;
    }

    @Override
    public String toString() {
      return "Email.Address(email=" + this.getEmail() + ", name=" + this.getName() + ")";
    }
  }

  private static class Body {

    private String text;

    private String html;

    public String getText() {
      return this.text;
    }

    public void setText(final String text) {
      this.text = text;
    }

    public String getHtml() {
      return this.html;
    }

    public void setHtml(final String html) {
      this.html = html;
    }
  }

  public static class EmailBuilder {

    private final Body body = new Body();

    private Address from;

    private List<Address> to;

    private String subject;

    private String customId;

    private boolean customIdUnique;

    private boolean trackOpens;

    private boolean trackClicks;

    private String listId;

    private String listUnsubscribe;

    EmailBuilder() {
    }

    public EmailBuilder bodyText(String text) {
      body.setText(text);
      return this;
    }

    public EmailBuilder bodyHtml(String html) {
      body.setHtml(html);
      return this;
    }

    private EmailBuilder body() {
      return this;
    }

    public EmailBuilder from(final Address from) {
      if (from == null) {
        throw new IllegalArgumentException("from is marked non-null but is null");
      }
      this.from = from;
      return this;
    }

    public EmailBuilder to(final List<Address> to) {
      if (to == null) {
        throw new IllegalArgumentException("to is marked non-null but is null");
      }
      this.to = to;
      return this;
    }

    public EmailBuilder subject(final String subject) {
      if (subject == null) {
        throw new IllegalArgumentException("subject is marked non-null but is null");
      }
      this.subject = subject;
      return this;
    }

    public EmailBuilder customId(final String customId) {
      this.customId = customId;
      return this;
    }

    public EmailBuilder customIdUnique(final boolean customIdUnique) {
      this.customIdUnique = customIdUnique;
      return this;
    }

    public EmailBuilder trackOpens(final boolean trackOpens) {
      this.trackOpens = trackOpens;
      return this;
    }

    public EmailBuilder trackClicks(final boolean trackClicks) {
      this.trackClicks = trackClicks;
      return this;
    }

    public EmailBuilder listId(final String listId) {
      this.listId = listId;
      return this;
    }

    public EmailBuilder listUnsubscribe(final String listUnsubscribe) {
      this.listUnsubscribe = listUnsubscribe;
      return this;
    }

    public Email build() {
      return new Email(this.body, this.from, this.to, this.subject, this.customId, this.customIdUnique, this.trackOpens, this.trackClicks, this.listId,
                       this.listUnsubscribe);
    }

    @Override
    public String toString() {
      return "Email.EmailBuilder(body=" + this.body + ", from=" + this.from + ", to=" + this.to + ", bodyText=" + this.body.text + ", bodyHtml=" + this.body.html + ", subject=" + this.subject + ", customId=" + this.customId + ", customIdUnique=" + this.customIdUnique + ", trackOpens=" + this.trackOpens + ", trackClicks=" + this.trackClicks + ", listId=" + this.listId + ", listUnsubscribe=" + this.listUnsubscribe + ")";
    }
  }
}
