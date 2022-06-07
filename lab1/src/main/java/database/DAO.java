package database;

import beans.*;

import java.util.List;

public interface DAO {

    Bank getBank(User user);

    User getUser(String username, String password);
    boolean checkIsUserExists(String login);
    int registrateUser(User user);

    Card getCard(String cardNum);
    List<Card> getUsersCards(User user);
    List<Card> getBlockedCards(List<Integer> accounts);

    List<Payment> getPayments(User user);
    int addPayment(Payment payment);

    Account getAccount(Card card);
    List<Account> getAccounts(List<Card> cards);
    void updateAccount(Account account);
    void blockAccount(Account account, boolean isBlocked);
}
