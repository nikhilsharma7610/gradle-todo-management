package com.todomgt.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.todomgt.app.enums.Status;
import com.todomgt.app.model.Item;
import com.todomgt.app.model.ItemCreateRequest;
import com.todomgt.app.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.todomgt.app.constants.ApplicationConstants.OBJECT_MAPPER;
import static java.util.stream.Collectors.*;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item create(ItemCreateRequest itemCreateRequest) throws JsonProcessingException {

        Item item = null;
        if (itemCreateRequest != null) {
            String jsonStr = OBJECT_MAPPER.writeValueAsString(itemCreateRequest);
            item = OBJECT_MAPPER.readValue(jsonStr, Item.class);
            itemRepository.save(item);
        }
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = itemRepository.findAll();
        if (!CollectionUtils.isEmpty(items)) {
            Map<Status, List<Item>> itemsGrouped = items.stream()
                    .collect(groupingBy(Item::getStatus,
                            mapping(Function.identity(),
                                    collectingAndThen(toList(),
                                            e -> e.stream().sorted(Comparator.comparing(Item::getUpdatedAt))
                                                    .collect(toList())))));
            items.clear();
            if (!CollectionUtils.isEmpty(itemsGrouped.get(Status.ACTIVE)))
                items.addAll(itemsGrouped.get(Status.ACTIVE));
            if (!CollectionUtils.isEmpty(itemsGrouped.get(Status.DONE)))
            items.addAll(itemsGrouped.get(Status.DONE));
        }
        return items;
    }

    @Override
    public boolean makeItemInactive(long itemId) {
        updateStatus(itemId, Status.DONE);
        return true;
    }

    @Override
    public boolean makeItemActive(long itemId) {
        updateStatus(itemId, Status.ACTIVE);
        return true;
    }

    private void updateStatus(long itemId, Status status) {
        Item item = itemRepository.findById(itemId);
        item.setStatus(status);
        itemRepository.save(item);
    }
}
