package ru.evotor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evotor.domain.Unit;
@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {
}
