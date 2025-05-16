package longlh.dev.base.excel.base.excel.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import longlh.dev.base.excel.base.excel.dto.ContactDto;
import longlh.dev.base.excel.base.excel.entity.Contact;
import longlh.dev.base.excel.base.excel.repository.ContactRepository;
import longlh.dev.base.excel.base.excel.service.ContactService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
    //private final JdbcTemplate jdbcTemplate;
    private final ContactRepository contactRepository;
    private final RedisTemplate<String, Object> redisTemplate;

//    public ContactServiceImpl(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public ContactDto add(ContactDto contactDto) {
        Contact contact = Contact.builder()
                .address(contactDto.getAddress())
                .email(contactDto.getEmail())
                .name(contactDto.getName())
                .telephone(contactDto.getTelephone())
                .build();
        contactRepository.save(contact);

//        if (Objects.nonNull(contactDto)) {
//            String sql = "insert into contact(name,email,address,telephone) values (?,?,?,?)";
//            int rowAffect = jdbcTemplate.update(sql, contactDto.getName(), contactDto.getEmail(),
//                    contactDto.getAddress(), contactDto.getTelephone());
////            if (rowAffect > 0) {
////                sql = "select * from contact where email = ?";
////                return jdbcTemplate.queryForObject(sql, new RowMapper<ContactDto>() {
////                    @Override
////                    public ContactDto mapRow(ResultSet rs, int rowNum) throws SQLException {
////                        ContactDto dto = ContactDto.builder()
////                                .id(rs.getInt("id"))
////                                .name(rs.getString("name"))
////                                .email(rs.getString("email"))
////                                .address(rs.getString("address"))
////                                .telephone(rs.getString("telephone"))
////                                .build();
////                        return dto;
////                    }
////                });
////            }
//        }

        // lưu log vào redis
        redisTemplate.opsForValue().set(contact.getId().toString(), contact);

        Contact contactFromRedis = (Contact) redisTemplate.opsForValue().get(contact.getId().toString());
        log.info("From Redis: {}", contactFromRedis);
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
