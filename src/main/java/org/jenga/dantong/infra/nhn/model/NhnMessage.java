package org.jenga.dantong.infra.nhn.model;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class NhnMessage {

    private String senderAddress;
    private String senderName;
    private String title;
    private String body;
    private ArrayList<Receiver> receiverList;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReceiverList{

        private ArrayList<Receiver> receivers;

        @Builder
        @AllArgsConstructor
        @Getter
        public static class Receiver {
            private String receiveMailAddr;
            private ReceiveType receiverType;
        }
    }
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Receiver {
        private String receiveMailAddr;
        private ReceiveType receiveType;
    }
}
