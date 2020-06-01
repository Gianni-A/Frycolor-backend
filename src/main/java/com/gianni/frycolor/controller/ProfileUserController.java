package com.gianni.frycolor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gianni.frycolor.controller.api.ProfileFriendApi;
import com.gianni.frycolor.controller.api.ProfileMediaApi;
import com.gianni.frycolor.controller.api.ProfileUserControllerApi;
import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;
import com.gianni.frycolor.exception.FriendsException;
import com.gianni.frycolor.exception.MediaException;
import com.gianni.frycolor.exception.UserExistException;
import com.gianni.frycolor.service.ProfileUserService;

@RestController
public class ProfileUserController implements ProfileUserControllerApi, ProfileFriendApi, ProfileMediaApi {
	
	@Autowired
	ProfileUserService service;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getUserInformation(int userInfId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getUserInformation(userInfId));
		}
		catch (UserExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity updateUserInformation(UserInformation userInformation) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.updateUserInformation(userInformation));
		}
		catch (UserExistException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getListFriends(int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getListFriends(userId));
		} catch(FriendsException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity addFriend(UserFriends userFriends) {
		try {
			service.addFriend(userFriends);
			return new ResponseEntity(HttpStatus.OK);
		} catch (FriendsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity deleteFriend(UserFriends userFriend) {
		try {
			service.deleteFriend(userFriend);
			return new ResponseEntity(HttpStatus.OK);
		} catch(FriendsException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity addOrUpdateMediaProfile(MultipartFile file, int userId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.addOrUpdateMediaProfile(file, userId));
		} catch (MediaException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}

}
