package com.example.bojeung365api.repository;

import com.example.bojeung365api.entity.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileMetaRepository extends JpaRepository<FileMeta, Long> {
    Optional<FileMeta> findByUid(String uid);
    boolean existsByUid(String uid);
    List<FileMeta> findByUidIn(List<String> uids);
}
