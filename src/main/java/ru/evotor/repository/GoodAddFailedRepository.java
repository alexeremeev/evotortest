package ru.evotor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evotor.domain.GoodAddFailed;

@Repository
public interface GoodAddFailedRepository extends CrudRepository<GoodAddFailed, Long> {
}
