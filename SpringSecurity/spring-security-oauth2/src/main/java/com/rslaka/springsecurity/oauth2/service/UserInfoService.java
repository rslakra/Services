package com.rslaka.springsecurity.oauth2.service;

import java.util.List;

import com.rslaka.springsecurity.oauth2.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rslaka.springsecurity.oauth2.repository.UserInfoRepository;

@Repository
@Transactional
public class UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	public UserInfo getUserInfoByUserName(String userName) {
		short enabled = 1;
		return userInfoRepository.findByUserNameAndEnabled(userName, enabled);
	}

	public List<UserInfo> getAllActiveUserInfo() {
		return userInfoRepository.findAllByEnabled((short) 1);
	}

	public UserInfo getUserInfoById(Integer id) {
		return userInfoRepository.findById(id);
	}

	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userInfoRepository.save(userInfo);
	}

	public UserInfo updateUser(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userInfoRepository.findById(id);
		userInfo.setUserName(userRecord.getUserName());
		userInfo.setPassword(userRecord.getPassword());
		userInfo.setRole(userRecord.getRole());
		userInfo.setEnabled(userRecord.getEnabled());
		return userInfoRepository.save(userInfo);
	}

	public void deleteUser(Integer id) {
		userInfoRepository.deleteById(id);
	}

	public UserInfo updatePassword(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userInfoRepository.findById(id);
		userInfo.setPassword(userRecord.getPassword());
		return userInfoRepository.save(userInfo);
	}

	public UserInfo updateRole(Integer id, UserInfo userRecord) {
		UserInfo userInfo = userInfoRepository.findById(id);
		userInfo.setRole(userRecord.getRole());
		return userInfoRepository.save(userInfo);
	}
}
