package petbook.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {
	
	private Long id;
	
	private String post;
	
	private Long idPet;
	
	private LocalDateTime created;
		
	private LocalDateTime updated;

}
