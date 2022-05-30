package com.example.demo.commands;

import com.example.demo.commands.entity.Basket;
import com.example.demo.commands.entity.Command;
import com.example.demo.commands.repository.CommandRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Command")
@RequestMapping("/command")
class CommandController {

  @Autowired
  CommandRepository repository;

  @PutMapping("test")
  @Transactional
  public void Test(@RequestBody Command item) {
    List<Basket> baskets = item.getBasket();

    List<Basket> newBaskets = new ArrayList<>();
    baskets.forEach(
      i -> {
        newBaskets.add(Basket.of(i.getQuantity(), i.getProduct_id(), item));
      }
    );

    item.setBasket(newBaskets);

    repository.save(item);
  }

  @GetMapping
  public ResponseEntity<List<Command>> getAll() {
    return null;
  }

  @GetMapping("{id}")
  public ResponseEntity<Command> getById(@PathVariable("id") Long id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<Command> create(@RequestBody List<Command> items) {
    for (Command item : items) {
      List<Basket> baskets = item.getBasket();

      List<Basket> newBaskets = new ArrayList<>();
      baskets.forEach(
        i -> {
          newBaskets.add(Basket.of(i.getQuantity(), i.getProduct_id(), item));
        }
      );

      item.setBasket(newBaskets);

      repository.save(item);
    }

    return null;
  }

  @PutMapping("{id}")
  public ResponseEntity<Command> update(
    @PathVariable("id") Long id,
    @RequestBody Command item
  ) {
    return null;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
    return null;
  }
}
