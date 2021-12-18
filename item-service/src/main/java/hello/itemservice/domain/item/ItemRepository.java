package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // @Component 포함 : 컴포넌트 스캔의 대상이 된다.
public class ItemRepository {

  private static final Map<Long, Item> store = new HashMap<>(); // static
  private static long sequence = 0L; // static

  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }

  public List<Item> findAll() {
    return new ArrayList<>(store.values()); // ArrayList에 변화가 생겨도 store에 영향을 주지 않기 위해
  }

  public void update(Long itemId, Item updateParam) {
    Item findItem = findById(itemId);
    findItem.setItemName(updateParam.getItemName());
    findItem.setPrice(updateParam.getPrice());
    findItem.setQuantity(updateParam.getQuantity());
  }

  public void clearStore() {
    store.clear();
  }
}
