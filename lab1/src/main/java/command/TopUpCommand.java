package command;

import beans.Account;

import javax.servlet.ServletException;
import java.io.IOException;

public class TopUpCommand  extends Command {
    @Override
    public void execute() throws ServletException, IOException {
        int money = Integer.valueOf(req.getParameter("money"));
        String cardNumber = req.getParameter("card");

        Account account = getAccount(cardNumber);

        if(account == null){
            return;
        }
        if(account.getIsBlocked()){
            req.getSession().setAttribute("warning", "Warning: Account is blocked");
        }
        else {
            account.setMoneyAmount(account.getMoneyAmount() + money);
            dao.updateAccount(account);
            req.getSession().setAttribute("topup", "+" + money + "$ to account.");
        }
        resp.sendRedirect("/lab1_war_exploded/client?command=Payments");
    }
}
