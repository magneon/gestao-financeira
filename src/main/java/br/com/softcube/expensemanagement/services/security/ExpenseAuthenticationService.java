package br.com.softcube.expensemanagement.services.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softcube.expensemanagement.daos.UserDao;
import br.com.softcube.expensemanagement.models.User;

@Service
public class ExpenseAuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserDao daoUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = daoUser.findByUsername(username);
		if (user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Bad Credentials!");
	}

}
