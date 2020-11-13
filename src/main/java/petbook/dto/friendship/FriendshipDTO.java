package petbook.dto.friendship;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import petbook.model.enums.StatusFriendship;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDTO {
	
	@JsonIgnore
	private Long id;
	
	private Long idRequester;

	private Long idReceiver;
	
	private StatusFriendship statusFriendship;

}
