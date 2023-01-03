package platform.services.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private Environment env;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (env.getProperty("platform.user").equals(username)) {
			
			UserDetails ans = User.withUsername(env.getProperty("platform.user"))
					.password(env.getProperty("platform.pass")).roles("ADMIN", "USER")
					.build();

			return ans;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}
