package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.UserFriends;
import com.gianni.frycolor.entities.UserInformation;

@Repository
public interface ProfileUserDao extends JpaRepository<UserInformation, Integer> {
	
	@Query("SELECT uf FROM UserInformation uf WHERE uf.usInfId = :userId")
	UserInformation getUserInfoById(@Param("userId") int userId);
	
	@Query(value = "SELECT count(1) FROM users_app WHERE us_inf_id = :userInfId", nativeQuery = true)
	int existUser(@Param("userInfId") int userInfId);
	
	
	/*@Query("SELECT usFrd FROM UserFriends usFrd "
			+ "INNER JOIN User us ON  us.usId = usFrd.frdUsId"
			+ "INNER JOIN "
			+ "WHERE usFrd.frdUsId = :userId")
	List<UserInformation> getListFriends(@Param("userId") int userId);*/

}
