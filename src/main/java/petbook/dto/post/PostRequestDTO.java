package petbook.dto.post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDTO {
	
	@NotNull(message = "The post is mandatory")
	@Size(min = 3, max = 255, message = "The post must be between three and two hundred and fifty five characters")
	private String post;
	
}
