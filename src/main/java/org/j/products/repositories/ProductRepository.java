package org.j.products.repositories;

import org.j.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Andrew on 12/29/16.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
