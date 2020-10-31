package petbook.dto.pet;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetRequestDTO {
	
	private static final String REGEX_ZONE_DATE_TIME = "(\\d{4}-\\d{2}-\\d{2})[A-Z]+(\\d{2}:\\d{2}:\\d{2}).([0-9+-:]+)";
	
	@NotNull(message = "The name of pet is mandatory")
	@Size(message = "The name of pet must be between two and twenty characters")
	private String name;
	
	@NotNull(message = "The gender of pet is mandatory")
	private String gender;
	
	@Pattern(regexp = REGEX_ZONE_DATE_TIME)
	@NotNull(message = "The date of age of pet is mandatory")
	private String dateOfAge;
	
}
