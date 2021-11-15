package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.model.ContactInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IAddressBookServices {
    List<ContactInfo> getContact();

    ContactInfo getContactById(long contactId);

    ContactInfo createContact(ContactDTO contactDTO);

    ContactInfo updateContact(long contactId, ContactDTO contactDTO);

    void deleteContact(long contactId);

    String deleteAllAddressBookData();

    List<ContactInfo> sortByPincode();

    List<ContactInfo> sortByCity();

    List<ContactInfo> sortByName();

    List<ContactInfo> getContactByPincode(String zip);

    List<ContactInfo> getContactByLastName(String lastName);

    List<ContactInfo> getContactByFirstName(String firstName);

    List<ContactInfo> getContactByCity(String city);

    Optional<ContactInfo> getData(String token);

    List<ContactInfo> getAllContacts(String token);

}

