package dev.isaac.tests.springjdbc.dao;

import java.util.List;

public interface Dao {
    List<MockData> findAll();

    List<MockData> findByGender(String gender);

    List<MockData> findByContainsDomainInEmail(String domain);
}
