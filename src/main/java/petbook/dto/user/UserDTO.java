package petbook.dto.user;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@JsonIgnore
	private Long id;
	
	@NotNull(message = "The e-mail is mandatory")
    private String email;
    
	@NotNull(message = "The password is mandatory")
    private String password;
    
	@AssertTrue
	@NotNull(message = "The admin is mandatory")
    private Boolean admin;

}
