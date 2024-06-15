package com.example.menu.item;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface InMemoryItemRepository extends CrudRepository<Item, Long> {
}
