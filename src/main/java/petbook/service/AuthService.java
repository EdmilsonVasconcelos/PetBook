package petbook.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

	public UserDetails autheticate(petbook.model.User participant);

}
