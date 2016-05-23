package name.sassi.ws.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by tsassi on 23/05/2016.
 */
@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private AccountUserDetailService accountUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if(authentication.getCredentials() == null || userDetails.getPassword() == null)
            throw new BadCredentialsException("Credentials may not be null.");
        if(!passwordEncoder.matches((String) authentication.getCredentials(), userDetails.getPassword()))
            throw new BadCredentialsException("Invalid credentials");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        return null;
    }
}
