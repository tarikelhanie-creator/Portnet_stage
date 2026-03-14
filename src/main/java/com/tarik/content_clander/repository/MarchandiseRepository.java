package com.tarik.content_clander.repository;
import com.tarik.content_clander.model.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MarchandiseRepository extends JpaRepository<Merchandise, Long> {}