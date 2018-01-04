package pl.kwi.springboot.db.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.kwi.springboot.db.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{}
