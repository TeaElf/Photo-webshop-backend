package rs.ac.bg.etf.webphoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.webphoto.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
