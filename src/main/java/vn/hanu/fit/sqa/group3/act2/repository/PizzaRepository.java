package vn.hanu.fit.sqa.group3.act2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hanu.fit.sqa.group3.act2.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
