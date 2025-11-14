
package com.benchmark.sdr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemsProxyController {

    private final ItemRepository itemRepository;

    public ItemsProxyController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(value = "/api/items", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Item> items(@RequestParam(required = false) Long categoryId, @PageableDefault Pageable pageable) {
        if (categoryId == null) {
            return itemRepository.findAll(pageable);
        }
        return itemRepository.findByCategoryId(categoryId, pageable);
    }
}
