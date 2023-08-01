package org.wcci.adjrvirtualpet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.adjrvirtualpet.entities.Cat;

public interface CatRepo extends CrudRepository<Cat, Long> {}