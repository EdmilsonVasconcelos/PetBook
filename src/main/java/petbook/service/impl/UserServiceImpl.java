package petbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import petbook.exception.InvalidPasswordException;
import petbook.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    @Transactional
    public petbook.model.User salvar(petbook.model.User user){
        return repository.save(user);
    }
	
    public UserDetails autenticar(petbook.model.User participant){
    	UserDetails user = loadUserByUsername(participant.getEmail());
        boolean senhasBatem = encoder.matches(participant.getPassword(), user.getPassword());

        if(senhasBatem){
            return user;
        }

        throw new InvalidPasswordException();
    }

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		petbook.model.User participant = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database"));

        String[] roles = participant.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(participant.getEmail())
                .password(participant.getPassword())
                .roles(roles)
                .build();
    }

}
