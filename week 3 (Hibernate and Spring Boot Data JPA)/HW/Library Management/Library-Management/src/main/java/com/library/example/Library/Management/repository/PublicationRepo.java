package com.library.example.Library.Management.repository;

import com.library.example.Library.Management.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepo extends JpaRepository<Publication,Long> {
}
