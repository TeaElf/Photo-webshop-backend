package rs.ac.bg.etf.webphoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.webphoto.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
