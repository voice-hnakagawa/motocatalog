package ooo.klae.sample.motocatalog.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ooo.klae.sample.motocatalog.beans.User;

// import ooo.klae.sample.motocatalog.beans.User;
import ooo.klae.sample.motocatalog.mappers.UserMapper;

@Slf4j
@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // return new User("test", "$2a$10$arkj/WxXFtK6ryKBSGx.8.GZ.sEIVyRGiWR3FFhcIlL4obl/5yqMC");
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            // due to the way spring security works now, custom messages won't throw even if they fire. you'll get generic 'invalid credentials' instead. so log the message and throw the generic one.
            log.warn("ユーザーが見つかりません: {}", username);
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }
        return user;
    }
}
