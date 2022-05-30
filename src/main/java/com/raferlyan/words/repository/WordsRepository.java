package com.raferlyan.words.repository;

import com.raferlyan.words.entity.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author raferlyan
 * @date 2022/5/30 08:41
 **/
@Repository
public interface WordsRepository extends JpaRepository<Words,Integer> {

    @Query(value = "SELECT MAX(DISPLAY_ORDER) FROM WORDS.WORDS", nativeQuery = true)
    Integer findMaxDisplayOrderInEntries();

    List<Words> findByWordsContainingAndIsDeletedFalse(String keyword);
}
