package longlh.dev.base.excel.base.excel.controller;

import lombok.RequiredArgsConstructor;
import longlh.dev.base.excel.base.excel.dto.ContactDto;
import longlh.dev.base.excel.base.excel.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping("/contact/add")
    public ContactDto create(@RequestBody ContactDto contactDto) {
        return contactService.add(contactDto);
        Json

    }

//    @GetMapping("/contact/all")
//    public List<ContactDto> getAll() {
//        return contactService.getAll();
//    }
}
