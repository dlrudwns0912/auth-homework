package com.rudwns.homework.domain.blog.repository;

import com.rudwns.homework.domain.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    Optional<BlogEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}