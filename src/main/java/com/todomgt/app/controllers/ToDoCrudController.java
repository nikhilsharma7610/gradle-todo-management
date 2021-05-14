package com.todomgt.app.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.todomgt.app.model.Item;
import com.todomgt.app.model.ItemCreateRequest;
import com.todomgt.app.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.todomgt.app.constants.ApplicationConstants.OBJECT_MAPPER;

@Slf4j
@RestController
@RequestMapping("/item")
public class ToDoCrudController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createItem(@RequestBody ItemCreateRequest itemCreateRequest) throws JsonProcessingException {

        Item item = itemService.create(itemCreateRequest);
        return OBJECT_MAPPER.writeValueAsString(item);
    }

    @RequestMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllItems() throws JsonProcessingException {
        List<Item> items = itemService.findAll();
        return CollectionUtils.isEmpty(items) ? "No items found" :
                OBJECT_MAPPER.writeValueAsString(items);
    }

    @RequestMapping(value = "/active/{itemId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String makeItemActive(@PathVariable long itemId) {
        return itemService.makeItemActive(itemId) +"";
    }

    @RequestMapping(value = "/inactive/{itemId}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String makeItemInactive(@PathVariable long itemId) {
        return itemService.makeItemInactive(itemId) +"";
    }

}
