package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.exceptions.AddressBookException;
import com.bridgelabz.addressbook.model.ContactInfo;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class AddressBookService implements IAddressBookServices {

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private AddressBookRepository addressBookRepository;

    public List<ContactInfo> getContact() {

        return addressBookRepository.findAll();
    }

    public ContactInfo getContactById(long contactId) {
        return addressBookRepository.findById(contactId)
                .orElseThrow(() -> new AddressBookException("Contact with id " + contactId + " does not exist..!"));
    }

    public ContactInfo createContact(ContactDTO contactDTO) {
        ContactInfo contact = new ContactInfo();
        contact.createContact(contactDTO);
        return addressBookRepository.save(contact);
    }

    public ContactInfo updateContact(long contactId, ContactDTO contactDTO) {
        ContactInfo contact = this.getContactById(contactId);
        contact.updateContact(contactDTO);
        return addressBookRepository.save(contact);
    }

    public void deleteContact(long contactId) {
        ContactInfo contact = this.getContactById(contactId);
        addressBookRepository.delete(contact);
    }

    public String deleteAllAddressBookData() {
        addressBookRepository.deleteAll();
        return "Successfully deleted all the Contacts from AddressBook";
    }

    @Override
    public List<ContactInfo> sortByPincode() {
        return addressBookRepository.sortByPincode();
    }

    @Override
    public List<ContactInfo> sortByCity() {
        return addressBookRepository.sortByCity();
    }

    @Override
    public List<ContactInfo> sortByName() {
        return addressBookRepository.sortByName();
    }

    @Override
    public List<ContactInfo> getContactByPincode(String zip) {
        return addressBookRepository.findContactListByZip(zip);
    }

    @Override
    public List<ContactInfo> getContactByLastName(String lastName) {
        return addressBookRepository.findContactListByLastName(lastName);
    }

    @Override
    public List<ContactInfo> getContactByFirstName(String firstName) {
        return addressBookRepository.findContactListByFirstName(firstName);
    }

    @Override
    public List<ContactInfo> getContactByCity(String city) {
        return addressBookRepository.findContactListByCity(city);
    }

    public Optional<ContactInfo> getData(String token) {
        Integer id = tokenUtil.decodeToken(token);
        Optional<ContactInfo> contactCheck = addressBookRepository.findById(Long.valueOf(id));
        if (contactCheck.isPresent()) {
            Optional<ContactInfo> contactData = addressBookRepository.findById(Long.valueOf(id));
            return contactData;
        }
        return null;
    }

    public List<ContactInfo> getAllContacts(String token) {
        Integer id = tokenUtil.decodeToken(token);
        Optional<ContactInfo> contactCheck = addressBookRepository.findById(Long.valueOf(id));
        if (contactCheck.isPresent()) {
            List<ContactInfo> contactList = addressBookRepository.findAll();
            return contactList;
        }
        return null;
    }
}

