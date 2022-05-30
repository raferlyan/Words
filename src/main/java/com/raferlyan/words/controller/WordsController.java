package com.raferlyan.words.controller;

import com.raferlyan.words.dto.DeleteDto;
import com.raferlyan.words.dto.SearchDto;
import com.raferlyan.words.dto.WordsListDto;
import com.raferlyan.words.dto.WordsWriteDto;
import com.raferlyan.words.entity.Words;
import com.raferlyan.words.service.WordsService;
import com.raferlyan.words.utils.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author raferlyan
 * @date 2022/5/30 08:42
 **/
@RestController
@RequiredArgsConstructor
public class WordsController {

    private final WordsService wordsService;

    private static final String baseUrl = "/words";

    @GetMapping(baseUrl)
    public ServiceResponse<List<WordsListDto>> getWords(){
        return ServiceResponse.ok(wordsService.getWords());
    }

    @GetMapping(baseUrl + "/retrieve")
    public ServiceResponse<List<WordsListDto>> retrieve(@RequestBody SearchDto searchDto){
        return ServiceResponse.ok(wordsService.retrieve(searchDto));
    }

    @PostMapping(baseUrl)
    public ServiceResponse<Words> create(@RequestBody WordsWriteDto wordsWriteDtoList){
        return ServiceResponse.ok(wordsService.create(wordsWriteDtoList));
    }

    @PutMapping(baseUrl)
    public ServiceResponse<List<Words>> update(@RequestBody List<WordsWriteDto> wordsWriteDtoList){
        return ServiceResponse.ok(wordsService.update(wordsWriteDtoList));
    }

    @PostMapping(baseUrl + "/delete")
    public ServiceResponse<Object> delete(@RequestBody List<DeleteDto> deleteDtoList){
        return wordsService.delete(deleteDtoList);
    }
}
