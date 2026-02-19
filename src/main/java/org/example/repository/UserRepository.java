package org.example.repository;

import java.util.Optional;
import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  public Optional<UserEntity> findByName(String name);

  public boolean existsByName(String name);
}
