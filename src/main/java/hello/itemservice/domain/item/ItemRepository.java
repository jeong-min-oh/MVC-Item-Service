package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static 사용한거 주의, 실무에선 해시맵 사용 x -> 동접시 문제 발생가능  : ConcurrentHashMap 사용권장
    private static long sequence = 0L;  // static, 실무에서 long 해서 사용하면 동접시 문제발생 가능


    //Item 저장하는 기능
    public Item save(Item item) {
        item.setId(++sequence);             // 아이디 자동으로 1씩 증가
        store.put(item.getId(), item);
        return item;
    }

    //ID 조회 기능
    public Item findById(Long id) {
        return store.get(id);
    }

    //전체 조회 기능
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //업데이트 기능 (아이디와, 업데이트파람 넣으면 업데이트 가능하게)
    public void update(Long itemid,Item updateParam) {
        Item findItem = findById(itemid);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }


    // 테스트까지 하면 핵심로직 만든거임 MVC
}
