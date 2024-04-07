package vn.hanu.fit.sqa.group3.act2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hanu.fit.sqa.group3.act2.model.Size;
import vn.hanu.fit.sqa.group3.act2.model.Topping;

import java.util.Optional;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    Optional<Topping> findByType(int type);
}
