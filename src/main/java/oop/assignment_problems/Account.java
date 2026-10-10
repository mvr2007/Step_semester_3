java.util.Scanner scanner = new java.util.Scanner(System.in);
java.util.Map<String, Account> accounts = new java.util.HashMap<>();

while (scanner.hasNext()) {
    String type = scanner.next();
    if (type.equals("Savings")) {
        String id = scanner.next();
        double balance = scanner.nextDouble();
        accounts.put(id, new SavingsAccount(id, balance));
    } else if (type.equals("Current")) {
        String id = scanner.next();
        double balance = scanner.nextDouble();
        accounts.put(id, new CurrentAccount(id, balance));
    } else if (type.equals("WITHDRAW")) {
        String id = scanner.next();
        double amount = scanner.nextDouble();
        Account acc = accounts.get(id);
        if (acc == null) {
            System.out.println("Account not found");
        } else {
            acc.withdraw(amount);
        }
    }
}

abstract class Account {
    protected String id;
    protected double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);
}

class SavingsAccount extends Account {
    public SavingsAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < 1000) {
            System.out.println(id + " rejected: minimum balance 1000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (long) balance);
        }
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount < -5000) {
            System.out.println(id + " rejected: overdraft limit 5000");
        } else {
            balance -= amount;
            System.out.println(id + " balance " + (long) balance);
        }
    }
}
