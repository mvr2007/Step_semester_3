import java.util.HashMap;
import java.util.Map;
public class CanteenOrders {
    public static class PopularItemResult {
        public String item;
        public int count;
        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }
        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }
    public static PopularItemResult mostPopular(String[] orders) {
        Map<String, Integer> counts = new HashMap<>();
        for (String order : orders) {
            counts.put(order, counts.getOrDefault(order, 0) + 1);
        }
        int maxCount = 0;
        String mostPopularItem = null;
        for (String order : orders) {
            int count = counts.get(order);
            if (count > maxCount) {
                maxCount = count;
                mostPopularItem = order;
            }
        }
        return new PopularItemResult(mostPopularItem, maxCount);
    }
    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println(mostPopular(orders1));

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println(mostPopular(orders2)); 
    }
}
