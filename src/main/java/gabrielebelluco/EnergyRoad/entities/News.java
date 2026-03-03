package gabrielebelluco.EnergyRoad.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID newsId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private LocalDate newsCreatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    public News() {
    }

    public News(UUID newsId, String title, String content, LocalDate newsCreatedAt, User createdBy) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.newsCreatedAt = newsCreatedAt;
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getNewsCreatedAt() {
        return newsCreatedAt;
    }

    public void setNewsCreatedAt(LocalDate newsCreatedAt) {
        this.newsCreatedAt = newsCreatedAt;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", newsCreatedAt=" + newsCreatedAt +
                ", createdBy=" + createdBy +
                '}';
    }
}
