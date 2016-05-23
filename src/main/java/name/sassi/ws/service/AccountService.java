package name.sassi.ws.service;

import name.sassi.ws.model.Account;

/**
 * Created by tsassi on 23/05/2016.
 */
public interface AccountService {
    Account findByUsername(String username);
}
