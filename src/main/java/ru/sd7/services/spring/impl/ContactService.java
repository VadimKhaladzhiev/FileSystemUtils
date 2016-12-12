package ru.sd7.services.spring.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sd7.model.Contact;
import ru.sd7.repository.ContactDao;
import ru.sd7.repository.SequenceDao;

import java.util.List;

/**
 * https://habrahabr.ru/post/217391/
 * */
@Service
public class ContactService {

    @Autowired private SequenceDao sequenceDao;
    @Autowired private ContactDao contactDao;

    public void add(Contact contact) {
        contact.setId(sequenceDao.getNextSequenceId(Contact.COLLECTION_NAME));
        contactDao.save(contact);
    }

    public void update(Contact contact) {
        contactDao.save(contact);
    }

    public Contact get(Long id) {
        return contactDao.get(id);
    }

    public List<Contact> getAll() {
        return contactDao.getAll();
    }

    public void remove(Long id) {
        contactDao.remove(id);
    }
}