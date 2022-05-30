package com.raferlyan.words.service;

import cn.hutool.core.bean.BeanUtil;
import com.raferlyan.words.dto.DeleteDto;
import com.raferlyan.words.dto.SearchDto;
import com.raferlyan.words.dto.WordsListDto;
import com.raferlyan.words.dto.WordsWriteDto;
import com.raferlyan.words.entity.Words;
import com.raferlyan.words.repository.WordsRepository;
import com.raferlyan.words.utils.ServiceException;
import com.raferlyan.words.utils.ServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author raferlyan
 * @date 2022/5/30 08:42
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class WordsService {

    private final WordsRepository wordsRepository;

    public List<WordsListDto> getWords() {
        List<Words> words = wordsRepository.findAll();
        return words.stream().filter(words2 -> !words2.getIsDeleted()).map(words1 ->
                BeanUtil.copyProperties(words1, WordsListDto.class)).collect(Collectors.toList());
    }

    public List<WordsListDto> retrieve(SearchDto searchDto) {
        List<Words> words = wordsRepository.findByWordsContainingAndIsDeletedFalse(searchDto.getKeyword());
        return words.stream().map(words1 ->
                BeanUtil.copyProperties(words1, WordsListDto.class)).collect(Collectors.toList());
    }

    public Words create(WordsWriteDto wordsWriteDto) {
        if (wordsWriteDto == null) {
            throw ServiceException.withMessage("所填不能为空");
        }
        Integer maxDisplayOrder = wordsRepository.findMaxDisplayOrderInEntries();
        if (maxDisplayOrder == null){
            maxDisplayOrder = 0;
        }
        Words words = Words.builder().words(wordsWriteDto.getWords()).translation(wordsWriteDto.getTranslation())
                .remark(wordsWriteDto.getRemark()).displayOrder(maxDisplayOrder).build();
        wordsRepository.save(words);
        return words;
    }

    public List<Words> update(List<WordsWriteDto> wordsWriteDtoList) {
        if (wordsWriteDtoList == null) {
            throw ServiceException.withMessage("所填不能为空");
        }
        List<Words> wordsList = wordsWriteDtoList.stream().map(wordsWriteDto ->
                BeanUtil.copyProperties(wordsWriteDto, Words.class)).collect(Collectors.toList());
        wordsRepository.saveAll(wordsList);
        return wordsList;
    }

    public ServiceResponse<Object> delete(List<DeleteDto> wordIds) {
        List<Words> wordsList = wordIds.stream().map(deleteDto -> {
            Optional<Words> optionalWords = wordsRepository.findById(deleteDto.getId());
            Words words = optionalWords.get();
            words.setIsDeleted(true);
            return words;
        }).collect(Collectors.toList());
        wordsRepository.saveAll(wordsList);
        return ServiceResponse.ok("删除成功");
    }
}
