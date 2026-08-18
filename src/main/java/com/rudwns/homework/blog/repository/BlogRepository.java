package com.rudwns.homework.blog.repository;

import com.rudwns.homework.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
}