package io.github.hobbstech.commons.usermanager.user.dao;

import io.github.hobbstech.commons.utilities.jpa.BaseDao;
import io.github.hobbstech.commons.usermanager.user.model.Token;

import java.util.Optional;

public interface TokenDao extends BaseDao<Token> {

    Optional<Token> findByUser_Id(long userId);

    Optional<Token> findByValue(String tokenValue);

    boolean existsByValue(String tokenValue);

}
