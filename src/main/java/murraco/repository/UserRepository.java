package murraco.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import murraco.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  boolean existsByEmail(String username);

  User findByEmail(String email);

  @Transactional
  void deleteByEmail(String email);

}
