package petbook.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import petbook.model.enums.StatusFriendship;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Friendship {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "id_requested")
	private Long idRequester;

	@Column(name = "id_receiver")
	private Long idReceiver;
	
	@Column(name = "status_friendship")
	private StatusFriendship statusFriendship;
	
	@CreatedDate
    @Column(updatable = false)
	private LocalDateTime created;
		
    @LastModifiedDate
    @Column
	private LocalDateTime updated;

}
