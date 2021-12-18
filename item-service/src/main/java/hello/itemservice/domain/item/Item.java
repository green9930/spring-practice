package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data // @Getter @Setter @RequiredArgsConstructor @ToString 포함 - 예상 밖의 현상이 발생할 수 있기 때문에 위험하다.
@Getter @Setter
public class Item {

  private Long id;
  private String itemName;
  private Integer price; // null일 수도 있기 때문에 (int면 0이라도 들어가야 한다.)
  private Integer quantity; // null일 수도 있기 때문에 (int면 0이라도 들어가야 한다.)

  public Item() {

  }

  public Item(String itemName, Integer price, Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }
}
