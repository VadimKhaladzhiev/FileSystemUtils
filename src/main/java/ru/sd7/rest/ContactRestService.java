package ru.sd7.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sd7.model.Contact;
import ru.sd7.services.spring.impl.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactRestService {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Contact> showAll() {
        return contactService.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean addContact(Contact contact) {
        if(contact.getId() == null) contactService.add(contact);
        else contactService.update(contact);

        return true;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public Contact showContact(@RequestParam(required = true) Long id) {
        return contactService.get(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean deleteContact(@RequestParam(required = true) Long id) {
        contactService.remove(id);
        return true;
    }
}
