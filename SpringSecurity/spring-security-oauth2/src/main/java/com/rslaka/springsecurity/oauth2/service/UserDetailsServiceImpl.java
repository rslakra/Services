package com.rslaka.springsecurity.oauth2.service;

import java.util.Arrays;

import com.rslaka.springsecurity.oauth2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserInfoService userInfoService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
		return new User(userInfo.getUserName(), userInfo.getPassword(), Arrays.asList(authority));
	}
}
