class OrderEntry {
    int orderCode;
    OrderEntry nextEntry;

    OrderEntry(int orderCode) {
        this.orderCode = orderCode;
        this.nextEntry = null;
    }
}

public class OrderListPurger {
    public static OrderEntry removeAll(OrderEntry head, int code) {
        while (head != null && head.orderCode == code) {
            head = head.nextEntry;
        }

        OrderEntry current = head;
        OrderEntry previous = null;

        while (current != null) {
            if (current.orderCode == code) {
                if (previous != null) {
                    previous.nextEntry = current.nextEntry;
                }
            } else {
                previous = current;
            }
            current = current.nextEntry;
        }

        return head;
    }

    public static void printOrders(OrderEntry head) {
        OrderEntry cursor = head;
        while (cursor != null) {
            System.out.print(cursor.orderCode + " -> ");
            cursor = cursor.nextEntry;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        OrderEntry head1 = new OrderEntry(5);
        head1.nextEntry = new OrderEntry(3);
        head1.nextEntry.nextEntry = new OrderEntry(5);
        head1.nextEntry.nextEntry.nextEntry = new OrderEntry(8);
        head1.nextEntry.nextEntry.nextEntry.nextEntry = new OrderEntry(5);

        head1 = removeAll(head1, 5);
        printOrders(head1);

        OrderEntry head2 = new OrderEntry(5);
        head2.nextEntry = new OrderEntry(5);
        head2.nextEntry.nextEntry = new OrderEntry(5);

        head2 = removeAll(head2, 5);
        printOrders(head2);
    }
}
