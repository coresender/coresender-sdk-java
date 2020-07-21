package com.coresender.sdk.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Sending operation response.
 */
public class SendEmailResponse {

    private LinkedList<Data> data;

    private Meta meta;

    /**
     * @return detailed messages processing information
     */
    public LinkedList<Data> getData() {
        return this.data;
    }

    /**
     * @return metadata information (e.g. request time)
     */
    public Meta getMeta() {
        return this.meta;
    }

    @Override
    public String toString() {
        return "SendEmailResponse(data=" + this.getData() + ", meta=" + this.getMeta() + ")";
    }

    /**
     * Single message processing information.
     */
    public static class Data {

        private String messageId;

        private String customId;

        private String status;

        private List<Error> errors;

        private String code;

        /**
         * @return message id
         */
        public String getMessageId() {
            return this.messageId;
        }

        /**
         * @return message custom id
         */
        public String getCustomId() {
            return this.customId;
        }

        /**
         * @return message status
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * @return a list of errors if ocurred
         */
        public List<Error> getErrors() {
            return this.errors;
        }

        /**
         * @return code
         */
        public String getCode() {
            return this.code;
        }

        @Override
        public String toString() {
            return "SendEmailResponse.Data(messageId=" + this.getMessageId() + ", customId=" + this.getCustomId() + ", status=" + this.getStatus() + ", errors=" + this
                    .getErrors() + ", code=" + this.getCode() + ")";
        }
    }

    /**
     * Error information.
     */
    public static class Error {

        String code;

        String description;

        String field;

        String value;

        List<Error> errors;

        /**
         * @return error code
         */
        public String getCode() {
            return this.code;
        }

        /**
         * @return error description
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * @return field name
         */
        public String getField() {
            return this.field;
        }

        /**
         * @return field error description
         */
        public String getValue() {
            return this.value;
        }

        /**
         * @return a list of nested errors if ocurred
         */
        public List<Error> getErrors() {
            return this.errors;
        }

        @Override
        public String toString() {
            return "SendEmailResponse.Error(code=" + this.getCode() + ", description=" + this.getDescription() + ", field=" + this.getField() + ", value=" + this
                    .getValue() + ", errors=" + this.getErrors() + ")";
        }
    }

    /**
     * Metadata information.
     */
    public static class Meta {

        private String rqTime;

        /**
         * @return request processing time
         */
        public String getRqTime() {
            return this.rqTime;
        }

        @Override
        public String toString() {
            return "SendEmailResponse.Meta(rqTime=" + this.getRqTime() + ")";
        }
    }
}
