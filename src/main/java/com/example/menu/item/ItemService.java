package com.example.menu.item;

import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class ItemService {
    private final CrudRepository<Item, Long> repository;

    public ItemService(CrudRepository<Item, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Item> defaultItems() {
        return List.of(
                new Item(1L, "Galactic Gooey", 599L, "Dive into a cosmic blend of flavors with a double-stacked beef patty, interstellar cheddar cheese, crispy meteorite bacon, and a dollop of our signature Martian gooey sauce. All nestled within a shiny, galaxy-glazed bun", "https://cdn.shopify.com/s/files/1/0281/1189/6681/files/DBCB_1.png?v=1587069291"),
                new Item(2L, "Jungle Jammin", 299L, "A veggie patty made from black beans and quinoa, topped with a zesty pineapple ring, jungle jackfruit BBQ sauce, and fresh jungle greens. Served on a toasted whole-grain bun", "https://www.diannesvegankitchen.com/wp-content/uploads/2021/04/burger-square.jpg"),
                new Item(3L, "Cheesy Volcano", 199L, " This burger is a molten masterpiece with a molten cheddar cheese core, spicy jalapeños, crispy onion strings, and smoky chipotle mayo. Served on a volcanic charcoal bun for that extra eruption of taste", "https://www.dartagnan.com/dw/image/v2/BJQL_PRD/on/demandware.static/-/Sites-dartagnan-Library/default/dw4e5ca574/images/content/spice-crusted-jalapeno-burger-recipe.jpg?sw=1440&strip=false")
        );
    }

    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        Iterable<Item> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Item> find(Long id) {
        return repository.findById(id);
    }

    public Item create(Item item) {
        Item copy = new Item(
                new Date().getTime(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getImage()
        );
        return repository.save(copy);
    }

    public Optional<Item> update(Long id, Item newItem) {
        return repository.findById(id).map(oldItem -> {
            Item updated = oldItem.updateWith(newItem);
            return repository.save(updated);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
