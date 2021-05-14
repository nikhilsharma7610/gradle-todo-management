package com.todomgt.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.todomgt.app.model.Item;
import com.todomgt.app.model.ItemCreateRequest;

import java.util.List;

public interface ItemService {

    Item create(ItemCreateRequest itemCreateRequest) throws JsonProcessingException;
    List<Item> findAll();
    boolean makeItemInactive(long itemId);
    boolean makeItemActive(long itemId);

}
