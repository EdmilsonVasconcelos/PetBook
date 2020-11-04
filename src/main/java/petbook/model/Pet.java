package petbook.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import petbook.model.enums.Gender;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Pet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 8)
	private Gender gender;
	
	@Column(name = "date_of_age")
	private LocalDateTime dateOfAge;
	
	@OneToOne
    @JoinColumn(name = "id_tutor")
	private User user;
	
	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Post> posts;
	
    @CreatedDate
    @Column(updatable = false)
	private LocalDateTime created;
		
    @LastModifiedDate
    @Column
	private LocalDateTime updated;

}
