package com.danhost.entity.repository;
import com.danhost.entity.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArestaRepository extends JpaRepository<Edge, Long> {
}
