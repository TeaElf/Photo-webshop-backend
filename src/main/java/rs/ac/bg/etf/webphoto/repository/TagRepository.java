package rs.ac.bg.etf.webphoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.webphoto.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
