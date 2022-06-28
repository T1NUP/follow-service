package com.cts.restfulwebservices.follow.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.restfulwebservices.follow.model.Follow;


@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
//	Follow findByUsername(String username);
	
	@Query("select DISTINCT(f.following) from Follow f where f.username = ?#{[0]}")
	List<String> findAllFollowing(String username);
	
	@Modifying
	@Transactional
	@Query("delete from Follow f where f.username = ?#{[0]} and f.following = ?#{[1]}")
	void deleteFollowRecord(String user,String username);
}
