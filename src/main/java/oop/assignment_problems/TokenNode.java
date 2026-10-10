class TokenNode {
    int tokenValue;
    TokenNode nextToken;

    TokenNode(int tokenValue) {
        this.tokenValue = tokenValue;
        this.nextToken = null;
    }
}

public class PriorityTokenTracker {
    TokenNode firstToken;

    public void addFirst(int token) {
        TokenNode newToken = new TokenNode(token);
        newToken.nextToken = firstToken;
        firstToken = newToken;
    }

    public void addLast(int token) {
        TokenNode newToken = new TokenNode(token);
        if (firstToken == null) {
            firstToken = newToken;
            return;
        }
        TokenNode cursor = firstToken;
        while (cursor.nextToken != null) {
            cursor = cursor.nextToken;
        }
        cursor.nextToken = newToken;
    }

    public void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }
        TokenNode newToken = new TokenNode(token);
        TokenNode cursor = firstToken;
        for (int i = 0; i < index - 1 && cursor != null; i++) {
            cursor = cursor.nextToken;
        }
        if (cursor != null) {
            newToken.nextToken = cursor.nextToken;
            cursor.nextToken = newToken;
        }
    }

    public void printTokens() {
        TokenNode cursor = firstToken;
        while (cursor != null) {
            System.out.print(cursor.tokenValue + " -> ");
            cursor = cursor.nextToken;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        PriorityTokenTracker tracker = new PriorityTokenTracker();
        tracker.addLast(101);
        tracker.addLast(102);
        tracker.addLast(103);

        tracker.addFirst(100);
        tracker.addLast(104);
        tracker.insertAt(2, 150);

        tracker.printTokens();
    }
}
