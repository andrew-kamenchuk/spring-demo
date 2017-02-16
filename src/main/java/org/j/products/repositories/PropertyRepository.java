package org.j.products.repositories;

import org.j.products.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew on 2/2/17.
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
