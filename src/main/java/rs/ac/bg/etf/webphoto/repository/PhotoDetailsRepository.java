package rs.ac.bg.etf.webphoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;

public interface PhotoDetailsRepository extends JpaRepository<PhotoDetails, Long> {
}
