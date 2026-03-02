package gabrielebelluco.EnergyRoad.entities;

import gabrielebelluco.EnergyRoad.ENUMS.ContactRequestType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "contact_requests")
public class ContactRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID contactRequestId;
    private String contactRequestName;
    private String email;
    private String subject;
    private String message;
    @Enumerated(EnumType.STRING)
    private ContactRequestType contactRequestType;
    private LocalDate contactRequestCreatedAt;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    public ContactRequest() {
    }

    public ContactRequest(UUID contactRequestId, String contactRequestName, String email, String subject, ContactRequestType contactRequestType, String message, LocalDate contactRequestCreatedAt, User user) {
        this.contactRequestId = contactRequestId;
        this.contactRequestName = contactRequestName;
        this.email = email;
        this.subject = subject;
        this.contactRequestType = contactRequestType;
        this.message = message;
        this.contactRequestCreatedAt = contactRequestCreatedAt;
        this.user = user;
    }

    public UUID getContactRequestId() {
        return contactRequestId;
    }

    public String getContactRequestName() {
        return contactRequestName;
    }

    public void setContactRequestName(String contactRequestName) {
        this.contactRequestName = contactRequestName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactRequestType getContactRequestType() {
        return contactRequestType;
    }

    public void setContactRequestType(ContactRequestType contactRequestType) {
        this.contactRequestType = contactRequestType;
    }

    public LocalDate getContactRequestCreatedAt() {
        return contactRequestCreatedAt;
    }

    public void setContactRequestCreatedAt(LocalDate contactRequestCreatedAt) {
        this.contactRequestCreatedAt = contactRequestCreatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ContactRequest{" +
                "contactRequestId=" + contactRequestId +
                ", contactRequestName='" + contactRequestName + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", contactRequestType=" + contactRequestType +
                ", contactRequestCreatedAt=" + contactRequestCreatedAt +
                ", user=" + user +
                '}';
    }
}
