package petbook.dto.post;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import petbook.model.Post;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPostsResponseDTO {
	
	private Long idPet;
	
	private List<Post> posts;

}
