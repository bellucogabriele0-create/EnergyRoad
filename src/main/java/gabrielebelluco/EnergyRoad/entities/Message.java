package gabrielebelluco.EnergyRoad.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID messageId;
    private String messageContent;
    private LocalDateTime messageCreatedAt;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    public Message() {
    }

    public Message(UUID messageId, String messageContent, User sender, User receiver) {
        this.messageId = messageId;
        this.messageContent = messageContent;
        this.messageCreatedAt = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getMessageCreatedAt() {
        return messageCreatedAt;
    }

    public void setMessageCreatedAt(LocalDateTime messageCreatedAt) {
        this.messageCreatedAt = messageCreatedAt;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageContent='" + messageContent + '\'' +
                ", messageCreatedAt=" + messageCreatedAt +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}

