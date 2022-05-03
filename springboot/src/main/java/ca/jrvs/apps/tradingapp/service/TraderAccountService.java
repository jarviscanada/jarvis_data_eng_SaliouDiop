package ca.jrvs.apps.tradingapp.service;

import ca.jrvs.apps.tradingapp.dao.AccountDao;
import ca.jrvs.apps.tradingapp.dao.PostionDao;
import ca.jrvs.apps.tradingapp.dao.SecurityDao;
import ca.jrvs.apps.tradingapp.dao.TraderDao;
import ca.jrvs.apps.tradingapp.model.domain.Account;
import ca.jrvs.apps.tradingapp.model.domain.Trader;
import ca.jrvs.apps.tradingapp.model.domain.TraderAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {
    private TraderDao traderDao;
    private AccountDao accountDao;
    private PostionDao positionDao;
    private SecurityDao securityDao;

    @Autowired
    public TraderAccountService(TraderDao traderDao, AccountDao accountDao,
                                PostionDao positionDao, SecurityDao securityDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityDao = securityDao;
    }

    public TraderAccountView createTraderAndAccount(Trader trader) {
        Trader savedTrader = traderDao.save(trader);
        Account account = new Account();
        account.setId(savedTrader.getId());
        account.setTraderId(savedTrader.getId());
        account.setAmount(0.0);
        accountDao.save(account);
        return new TraderAccountView(savedTrader, account);
    }

    public void deleteTraderById(Integer traderId) {
        if (!traderDao.existsById(traderId)) {
            throw new IllegalArgumentException("Trader does not exist");
        }
        Account account = accountDao.findById(traderId).get();
        if (account.getAmount() != 0.0) {
            throw new IllegalArgumentException("Account has non-zero balance");
        }
        if (positionDao.findById(account.getId()) != null) {
            throw new IllegalArgumentException("Account has positions");
        }
        traderDao.deleteById(traderId);
        accountDao.deleteById(account.getId());
        securityDao.deleteByAccountId(account.getId());
    }

}
