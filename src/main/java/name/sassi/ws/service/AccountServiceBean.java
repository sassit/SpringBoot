package name.sassi.ws.service;

import name.sassi.ws.model.Account;
import name.sassi.ws.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tsassi on 23/05/2016.
 */
@Service
public class AccountServiceBean implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
