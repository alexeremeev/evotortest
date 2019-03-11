package ru.evotor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.evotor.domain.Endpoint;

@Repository
public interface EndpointRepository extends CrudRepository<Endpoint, Long>{

}
