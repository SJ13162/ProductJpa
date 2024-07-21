package com.learning.simran.jpaTutorial.repositories;

import com.learning.simran.jpaTutorial.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime date);

    List<ProductEntity> findByPriceAndQuantity(BigDecimal price, int quantity);

    List<ProductEntity> findByPriceGreaterThanAndQuantityLessThan(BigDecimal price, int quantity);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContaining(String title);

    ProductEntity findByTitleAndPrice(String title, BigDecimal price);

    //JPQL
    @Query("SELECT p from ProductEntity p where p.title=?1 and p.price=?2")
    ProductEntity findByTitleAndPriceCustomQuery(String title, BigDecimal price);

    List<ProductEntity> findByTitleOrderByPrice(String title);

    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);

    Page<ProductEntity> findAll(Pageable pageable);
}
