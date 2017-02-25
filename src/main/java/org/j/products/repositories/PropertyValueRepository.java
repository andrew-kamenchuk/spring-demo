package org.j.products.repositories;

import org.j.products.entities.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew on 2/2/17.
 */
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {
}

