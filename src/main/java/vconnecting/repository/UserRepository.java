package vconnecting.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vconnecting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String username);

	User findByEmail(String email);

	@Transactional
	void deleteByEmail(String email);

	@Transactional
	@Query("SELECT p FROM User p JOIN FETCH p.languages")
	List<User> findAllUsersWithLanguages();

}
