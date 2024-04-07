package vn.hanu.fit.sqa.group3.act2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hanu.fit.sqa.group3.act2.model.Size;

import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {
    Optional<Size> findByType(int type);
}
