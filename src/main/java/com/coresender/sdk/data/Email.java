package com.coresender.sdk.data;

import java.util.List;

/**
 * Email message to be sent.
 */
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

    private final List<Address> replyTo;

    private Email(final Body body, final Address from, final List<Address> to, final String subject, final String customId, final boolean customIdUnique, final boolean trackOpens, final boolean trackClicks, final String listId, final String listUnsubscribe, final List<Address> replyTo) {
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
        this.replyTo = replyTo;
    }

    /**
     * Creates EmailBuilder instance.
     *
     * @return EmailBuilder object
     */
    public static EmailBuilder builder() {
        return new EmailBuilder();
    }

    /**
     * @return reply's to addresses
     */
    public List<Address> getReplyTo() {
        return replyTo;
    }

    /**
     * @return sender's address
     */
    public Address getFrom() {
        return this.from;
    }

    /**
     * @return recipient's addresses
     */
    public List<Address> getTo() {
        return this.to;
    }

    /**
     * @return message subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * @return message custom id
     */
    public String getCustomId() {
        return this.customId;
    }

    /**
     * @return whether custom id should be unique
     */
    public boolean isCustomIdUnique() {
        return this.customIdUnique;
    }

    /**
     * @return wheter openings should be tracked
     */
    public boolean isTrackOpens() {
        return this.trackOpens;
    }

    /**
     * @return wheter clicks should be tracked
     */
    public boolean isTrackClicks() {
        return this.trackClicks;
    }

    /**
     * @return sending list name
     */
    public String getListId() {
        return this.listId;
    }

    /**
     * @return unsubscribe list name
     */
    public String getListUnsubscribe() {
        return this.listUnsubscribe;
    }

    @Override
    public String toString() {
        return "Email(body=" + this.getBody() + ", from=" + this.getFrom() + ", to=" + this.getTo() + ", bodyText=" + this.getBodyText() + ", bodyHtml=" + this.getBodyHtml() + ", subject=" + this
                .getSubject() + ", customId=" + this.getCustomId() + ", customIdUnique=" + this.isCustomIdUnique() + ", trackOpens=" + this.isTrackOpens() + ", trackClicks=" + this
                .isTrackClicks() + ", listId=" + this.getListId() + ", listUnsubscribe=" + this.getListUnsubscribe() + ", replyTo=" + this.replyTo + ")";
    }

    /**
     * @return message content
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * @return text version of message content
     */
    private String getBodyText() {
        return this.body.text;
    }

    /**
     * @return html version of message content
     */
    private String getBodyHtml() {
        return this.body.html;
    }

    /**
     * Email address.
     */
    public static class Address {

        private final String email;

        private final String name;

        private Address(final String email, final String name) {
            this.email = email;
            this.name = name;
        }

        public static Address of(String email) {
            return new Address(email, null);
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

    /**
     * Email body
     */
    private static class Body {

        private String text;

        private String html;

        /**
         * @return text version of message content
         */
        public String getText() {
            return this.text;
        }

        public void setText(final String text) {
            this.text = text;
        }

        /**
         * @return html version of message content
         */
        public String getHtml() {
            return this.html;
        }

        public void setHtml(final String html) {
            this.html = html;
        }
    }

    /**
     * Email builder for convenient message creation.
     */
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

        private List<Address> replyTo;

        EmailBuilder() {
        }

        /**
         * Sets text version of message content.
         *
         * @param text conent
         * @return builder object
         */
        public EmailBuilder bodyText(String text) {
            body.setText(text);
            return this;
        }

        /**
         * Sets html version of message content.
         *
         * @param html content
         * @return builder object
         */
        public EmailBuilder bodyHtml(String html) {
            body.setHtml(html);
            return this;
        }

        /**
         * Sets sender.
         *
         * @param from sender address
         * @return builder object
         */
        public EmailBuilder from(final Address from) {
            if (from == null) {
                throw new IllegalArgumentException("from is marked non-null but is null");
            }
            this.from = from;
            return this;
        }

        /**
         * Sets recipients.
         *
         * @param to list of recipients
         * @return builder object
         */
        public EmailBuilder to(final List<Address> to) {
            if (to == null) {
                throw new IllegalArgumentException("to is marked non-null but is null");
            }
            this.to = to;
            return this;
        }

        /**
         * Sets subject.
         *
         * @param subject message subject
         * @return builder object
         */
        public EmailBuilder subject(final String subject) {
            if (subject == null) {
                throw new IllegalArgumentException("subject is marked non-null but is null");
            }
            this.subject = subject;
            return this;
        }

        /**
         * Sets custom id.
         *
         * @param customId message custom id
         * @return builder object
         */
        public EmailBuilder customId(final String customId) {
            this.customId = customId;
            return this;
        }

        /**
         * Sets whether custom id should be unique.
         *
         * @param customIdUnique if custom id should be unique
         * @return builder object
         */
        public EmailBuilder customIdUnique(final boolean customIdUnique) {
            this.customIdUnique = customIdUnique;
            return this;
        }

        /**
         * Sets whether openings should be tracked.
         *
         * @param trackOpens if openings should be tracked
         * @return builder object
         */
        public EmailBuilder trackOpens(final boolean trackOpens) {
            this.trackOpens = trackOpens;
            return this;
        }

        /**
         * Sets whether clicks should be tracked.
         *
         * @param trackClicks if clicks should be tracked
         * @return builder object
         */
        public EmailBuilder trackClicks(final boolean trackClicks) {
            this.trackClicks = trackClicks;
            return this;
        }

        /**
         * Sets list identifier.
         *
         * @param listId list identifier
         * @return builder object
         */
        public EmailBuilder listId(final String listId) {
            this.listId = listId;
            return this;
        }

        /**
         * Sets the unsubscribe list.
         *
         * @param listUnsubscribe unsubscribe list name
         * @return builder object
         */
        public EmailBuilder listUnsubscribe(final String listUnsubscribe) {
            this.listUnsubscribe = listUnsubscribe;
            return this;
        }

        /**
         * Sets reply to.
         *
         * @param replyTo address
         * @return builder object
         */
        public EmailBuilder replyTo(final List<Address> replyTo) {
            if (replyTo == null) {
                throw new IllegalArgumentException("replyTo is marked non-null but is null");
            }
            this.replyTo = replyTo;
            return this;
        }

        /**
         * Creates Email instance.
         *
         * @return email object
         */
        public Email build() {
            return new Email(this.body, this.from, this.to, this.subject, this.customId, this.customIdUnique, this.trackOpens, this.trackClicks, this.listId,
                             this.listUnsubscribe, this.replyTo);
        }

        @Override
        public String toString() {
            return "Email.EmailBuilder(body=" + this.body + ", from=" + this.from + ", to=" + this.to + ", bodyText=" + this.body.text + ", bodyHtml=" + this.body.html + ", subject=" + this.subject + ", customId=" + this.customId + ", customIdUnique=" + this.customIdUnique + ", trackOpens=" + this.trackOpens + ", trackClicks=" + this.trackClicks + ", listId=" + this.listId + ", listUnsubscribe=" + this.listUnsubscribe + ", replyTo=" + this.replyTo + ")";
        }
    }
}
