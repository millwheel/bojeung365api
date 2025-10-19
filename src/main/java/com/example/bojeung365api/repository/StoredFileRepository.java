package com.example.bojeung365api.repository;

import com.example.bojeung365api.entity.StoredFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoredFileRepository extends JpaRepository<StoredFile, Long> {
    Optional<StoredFile> findByUid(String uid);
    boolean existsByUid(String uid);
    List<StoredFile> findByUidIn(List<String> uids);
}
