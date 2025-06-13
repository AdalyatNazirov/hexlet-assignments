package exercise.controller;

import exercise.dto.ContactCreateDTO;
import exercise.dto.ContactDTO;
import exercise.model.Contact;
import exercise.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO dto) {
        Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setPhone(dto.getPhone());

        contact = contactRepository.save(contact);

        var contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setCreatedAt(contact.getCreatedAt());
        contactDTO.setUpdatedAt(contact.getUpdatedAt());

        return contactDTO;
    }
    // END
}
