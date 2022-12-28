package com.example.springselectshop.repository;

import com.example.springselectshop.entity.Folder;
import com.example.springselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUserAndNameIn(User user, List<String> names);
    List<Folder> findAllByUser(User user);
}
