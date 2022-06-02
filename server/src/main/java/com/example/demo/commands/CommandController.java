package com.example.demo.commands;

import com.example.demo.commands.dto.BasketDto;
import com.example.demo.commands.dto.CommandDto;
import com.example.demo.commands.entity.Basket;
import com.example.demo.commands.entity.Command;
import com.example.demo.commands.repository.CommandMapper;
import com.example.demo.commands.repository.CommandRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Commands")
@RequestMapping("/commands")
class CommandController {

  CommandRepository commandRepository;
  CommandMapper commandMapper;

  @Autowired
  CommandController(
    CommandRepository commandRepository,
    CommandMapper commandMapper
  ) {
    this.commandMapper = commandMapper;
    this.commandRepository = commandRepository;
  }

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

    commandRepository.save(item);
  }

  @GetMapping(params = { "_start", "_end", "_sort", "_order", "status" })
  public ResponseEntity<List<CommandDto>> getAll(
    @RequestParam(name = "_start") Integer start,
    @RequestParam(name = "_end") Integer end,
    @RequestParam(name = "_sort") String sort,
    @RequestParam(name = "_order") String order,
    @RequestParam(name = "status") String status
  ) {
    Integer take = end - start;

    List<CommandDto> commandsQueRes = commandMapper.getPaginatedcommands(
      start,
      take,
      sort,
      order,
      status
    );

    String commandCount = commandMapper.getCommandCount(status);

    return ResponseEntity
      .ok()
      .header("X-Total-Count", commandCount)
      .body(buildCommandsDtoList(commandsQueRes));
  }

  @GetMapping(params = "id")
  public ResponseEntity<List<Command>> getManyReference(
    @RequestParam("id") List<Long> ids
  ) {
    List<Command> commands = commandMapper.getManyCommands(ids);

    return ResponseEntity.ok().body(commands);
  }

  @GetMapping("{id}")
  public ResponseEntity<Command> getById(@PathVariable("id") Long id) {
    Command command = commandRepository
      .findById(id)
      .orElseThrow(NoSuchElementException::new);

    return ResponseEntity.ok().body(command);
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

      commandRepository.save(item);
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

  private List<CommandDto> buildCommandsDtoList(
    List<CommandDto> commandsQueRes
  ) {
    return commandsQueRes
      .stream()
      .map(
        customer -> {
          String productId_quantity = customer.getProductId_quantity();

          customer.setBasket(processBasketeDtoList(productId_quantity));
          customer.setProductId_quantity(null);
          return customer;
        }
      )
      .collect(Collectors.toList());
  }

  /*
   * Convert productId_quantity string to ArrayList<BasketDto>
   *
   * For instance:
   *    From
   *      String productId_quantity: "55,3,111,3";
   *    TO
   *      ArrayList<BasketDto>: [ { "quantity": 3, "product_id": 55 }, { "quantity": 3, "product_id": 111 } ]
   */
  private ArrayList<BasketDto> processBasketeDtoList(
    String productId_quantity
  ) {
    ArrayList<String> flatList = new ArrayList<String>(
      Arrays.asList(productId_quantity.split(","))
    );

    ArrayList<ArrayList<String>> nestedList = new ArrayList<ArrayList<String>>();

    for (int i = 0; i < flatList.size(); i += 2) {
      nestedList.add(new ArrayList<String>());
    }

    for (int i = 0; i < flatList.size(); i += 2) {
      nestedList.get(i / 2).add(flatList.get(i));
      nestedList.get(i / 2).add(flatList.get(i + 1));
    }

    ArrayList<BasketDto> basketDtoList = new ArrayList<>();

    for (int i = 0; i < nestedList.size(); i++) {
      ArrayList<String> list = nestedList.get(i);

      Long productIdLong = Long.parseLong(list.get(0));
      Integer quantityInt = Integer.parseInt(list.get(1));

      basketDtoList.add(BasketDto.of(quantityInt, productIdLong));
    }

    return basketDtoList;
  }
}
