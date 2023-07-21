package dev.isaac.tests.springjdbc.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TestDao implements Dao {

    final String SELECT_ALL = "select * from mock_data";
    final String SELECT_BY_GENDER = "SELECT * from mock_data where gender = :gender";

    private final NamedParameterJdbcTemplate template;

    public TestDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    @Override
    public List<MockData> findAll() {
        return template.query(SELECT_ALL, new MockDataMapper());
    }

    @Override
    public List<MockData> findByGender(String gender) {
        return template.query(SELECT_BY_GENDER, new MapSqlParameterSource("gender", gender),
                new MockDataMapper());
    }

    @Override
    public List<MockData> findByContainsDomainInEmail(String domain) {
        return null;
    }

    private static final class MockDataMapper implements RowMapper<MockData> {
        @Override
        public MockData mapRow(ResultSet rs, int rowNum) throws SQLException {
            var entity = new MockData();

            entity.setId(rs.getInt("id"));
            entity.setFirstName(rs.getString("first_name"));
            entity.setLastName(rs.getString("last_name"));
            entity.setEmail(rs.getString("email"));
            entity.setGender(rs.getString("gender"));
            entity.setIpAddress(rs.getString("ip_address"));

            return entity;
        }
    }
}
