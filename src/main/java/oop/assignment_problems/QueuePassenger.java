class QueuePassenger {
    int passengerId;
    QueuePassenger nextPassenger;

    QueuePassenger(int passengerId) {
        this.passengerId = passengerId;
        this.nextPassenger = null;
    }
}

public class TicketQueueInverter {
    public static QueuePassenger reverse(QueuePassenger head) {
        QueuePassenger prevPassenger = null;
        QueuePassenger currentPassenger = head;
        QueuePassenger nextPassenger = null;

        while (currentPassenger != null) {
            nextPassenger = currentPassenger.nextPassenger;
            currentPassenger.nextPassenger = prevPassenger;
            prevPassenger = currentPassenger;
            currentPassenger = nextPassenger;
        }

        return prevPassenger;
    }

    public static void printQueue(QueuePassenger head) {
        QueuePassenger cursor = head;
        while (cursor != null) {
            System.out.print(cursor.passengerId + " -> ");
            cursor = cursor.nextPassenger;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        QueuePassenger head1 = new QueuePassenger(1);
        head1.nextPassenger = new QueuePassenger(2);
        head1.nextPassenger.nextPassenger = new QueuePassenger(3);
        head1.nextPassenger.nextPassenger.nextPassenger = new QueuePassenger(4);

        head1 = reverse(head1);
        printQueue(head1);

        QueuePassenger head2 = new QueuePassenger(9);
        head2 = reverse(head2);
        printQueue(head2);
    }
}
