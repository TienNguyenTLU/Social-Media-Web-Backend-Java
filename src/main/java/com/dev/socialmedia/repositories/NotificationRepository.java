package com.dev.socialmedia.repositories;

import com.dev.socialmedia.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Long countByRecipientIdAndIsReadFalse(Long userId);
    Long countByRecipientIdAndTypeAndIsReadFalse(Long userId, String type);
    List<Notification> findAllByRecipientId(Long userId);
    List<Notification> findAllByRecipientIdAndType(Long userId, String type);

    @Query("UPDATE Notification n SET n.isRead = true WHERE n.recipient.id = :userId AND n.type = :type")
    void markAllAsReadByType(Long userId, String type);

    @Query("UPDATE Notification n SET n.isRead = true WHERE n.id = :id AND n.recipient.id = :userId")
    void markAsReadById(Long id);
}
