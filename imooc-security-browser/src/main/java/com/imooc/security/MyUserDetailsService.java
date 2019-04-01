package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名:" + username);
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		return new User(username,"123456",authorities);
	}

}
