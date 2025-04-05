package longlh.dev.base.excel.base.excel.service.impl;

import longlh.dev.base.excel.base.excel.dto.ContactDto;
import longlh.dev.base.excel.base.excel.service.ContactService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Objects;

@Service
public class ContactServiceImpl implements ContactService {
    private final JdbcTemplate jdbcTemplate;

    public ContactServiceImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ContactDto add(ContactDto contactDto) {
        if (Objects.nonNull(contactDto)) {
            String sql = "insert into contact(name,email,address,telephone) values (?,?,?,?)";
            int rowAffect = jdbcTemplate.update(sql, contactDto.getName(), contactDto.getEmail(),
                    contactDto.getAddress(), contactDto.getTelephone());
//            if (rowAffect > 0) {
//                sql = "select * from contact where email = ?";
//                return jdbcTemplate.queryForObject(sql, new RowMapper<ContactDto>() {
//                    @Override
//                    public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        ContactDto dto = ContactDto.builder()
//                                .id(rs.getInt("id"))
//                                .name(rs.getString("name"))
//                                .email(rs.getString("email"))
//                                .address(rs.getString("address"))
//                                .telephone(rs.getString("telephone"))
//                                .build();
//                        return dto;
//                    }
//                });
//            }
        }
        return null;
    }

//    @Override
//    public List<ContactDto> getAll() {
//        String sql = "SELECT * FROM contact";
//        return jdbcTemplate.query(sql, new RowMapper<ContactDto>() {
//            @Override
//            public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                ContactDto contactDto = ContactDto.builder()
//                        .id(rs.getInt("id"))
//                        .name(rs.getString("name"))
//                        .email(rs.getString("email"))
//                        .address(rs.getString("address"))
//                        .telephone(rs.getString("telephone"))
//                        .build();
//                return contactDto;
//            }
//        });
//    }
}
