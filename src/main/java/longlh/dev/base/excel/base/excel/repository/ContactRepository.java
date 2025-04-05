package longlh.dev.base.excel.base.excel.repository;

import longlh.dev.base.excel.base.excel.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {


}
