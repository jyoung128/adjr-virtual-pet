package org.wcci.adjrvirtualpet.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.adjrvirtualpet.entities.Dog;

public interface DogRepo extends CrudRepository<Dog, Long> {}