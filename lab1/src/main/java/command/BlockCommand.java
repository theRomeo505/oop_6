package command;

import beans.Account;

import java.io.IOException;

public class BlockCommand extends Command{
    @Override
    public void execute() throws IOException {
        String cardNumber = req.getParameter("card");
        Account account = getAccount(cardNumber);
        if(account == null){
            return;
        }
        account.setIsBlocked(true);
        dao.blockAccount(account, true);
        req.getSession().setAttribute("warning", "Warning: blocked");

        req.getSession().setAttribute("block", true);
        resp.sendRedirect("/lab1_war_exploded/client?command=Payments");
    }
}
