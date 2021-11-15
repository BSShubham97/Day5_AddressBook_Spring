package com.bridgelabz.addressbook.controller;


import antlr.Token;
import com.bridgelabz.addressbook.dto.ContactDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.exceptions.AddressBookException;
import com.bridgelabz.addressbook.model.ContactInfo;
import com.bridgelabz.addressbook.services.IAddressBookServices;
import com.bridgelabz.addressbook.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/addressbook")
    @Slf4j
    public class AddressBookController {

        @Autowired
        TokenUtil tokenUtil;

        @Autowired
        private IAddressBookServices addressbookservice;

        @RequestMapping(value = { "", "/", "/get" })
        public ResponseEntity<ResponseDTO> getContactData() {

            List<ContactInfo> contactList = addressbookservice.getContact();
            ResponseDTO response = new ResponseDTO("Get call success", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/get/{contactId}")
        public ResponseEntity<ResponseDTO> getContactData(@PathVariable("contactId") int contactId) {
            ContactInfo contact = addressbookservice.getContactById(contactId);
            ResponseDTO response = new ResponseDTO("Get call success for id", contact);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

        }

        @PostMapping("/create")
        public ResponseEntity<ResponseDTO> addContactData(@Valid @RequestBody ContactDTO contactDTO) {
            ContactInfo contact = addressbookservice.createContact(contactDTO);
            log.debug("Address Book DTO: " + contactDTO.toString());
            ResponseDTO response = new ResponseDTO("Contact Data Added Successfully",
                    tokenUtil.createToken((int) contact.getContactId()));
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

        }

        @PutMapping("/update/{contactId}")
        public ResponseEntity<ResponseDTO> updateContactData(@PathVariable("contactId") int contactId,
                                                             @Valid @RequestBody ContactDTO contactDTO) {
            ContactInfo contact = addressbookservice.updateContact(contactId, contactDTO);
            log.debug("AddressBook Contact After Update " + contact.toString());
            ResponseDTO response = new ResponseDTO("Updated Contact Data ", tokenUtil.createToken((int) contact.getContactId()));
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

        }

        @DeleteMapping("/delete/{contactId}")
        public ResponseEntity<ResponseDTO> deleteContactData(@PathVariable("contactId") int contactId) {
            addressbookservice.deleteContact(contactId);
            ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + contactId);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

        }

        @DeleteMapping("/deleteall")
        public ResponseEntity<ResponseDTO> deleteAllAddressBookData() {
            String message = addressbookservice.deleteAllAddressBookData();
            ResponseDTO respDTO = new ResponseDTO("Deleteall:", message);
            return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
        }

        @GetMapping("/city/{city}")
        public ResponseEntity<ResponseDTO> getContactByCity(@PathVariable String city) {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.getContactByCity(city);
            ResponseDTO response = new ResponseDTO("Get Call Contact List By city is Successful", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/firstname/{firstName}")
        public ResponseEntity<ResponseDTO> getContactByFirstName(@PathVariable String firstName) {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.getContactByFirstName(firstName);
            ResponseDTO response = new ResponseDTO("Get Call Contact List By first name is Successful", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/lastname/{lastName}")
        public ResponseEntity<ResponseDTO> getContactByLastName(@PathVariable String lastName) {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.getContactByLastName(lastName);
            ResponseDTO response = new ResponseDTO("Get Call Contact List By last name is Successful", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/zip/{zip}")
        public ResponseEntity<ResponseDTO> getContactByZip(@PathVariable String zip) {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.getContactByPincode(zip);
            ResponseDTO response = new ResponseDTO("Get Call Contact List By Pincode is Successful", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/sortbyname")
        public ResponseEntity<ResponseDTO> sortByName() {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.sortByName();
            ResponseDTO response = new ResponseDTO("Get Call  is Successful Sort By Name: ", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/sortbycity")
        public ResponseEntity<ResponseDTO> sortByCity() {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.sortByCity();
            ResponseDTO response = new ResponseDTO("Get Call  is Successful Sort By City: ", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/sortbypincode")
        public ResponseEntity<ResponseDTO> sortByPincode() {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.sortByPincode();
            ResponseDTO response = new ResponseDTO("Get Call  is Successful Sort By PinCode: ", contactList);
            return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
        }

        @GetMapping("/readallcontactbytoken")
        public ResponseEntity<ResponseDTO> readdata(@RequestHeader(name = "token") String token)
                throws AddressBookException {
            List<ContactInfo> contactList = null;
            contactList = addressbookservice.getAllContacts(token);
            if (contactList.size() > 0) {
                ResponseDTO responseDTO = new ResponseDTO("all user Fetched successfully", contactList);
                return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
            } else {
                throw new AddressBookException("No Data Found");
            }

        }

        @GetMapping("/readdatabytoken")
        public ResponseEntity<ResponseDTO> readupdatedata(@RequestHeader(name = "token") String token)
                throws AddressBookException {
            Optional<ContactInfo> readData = null;
            readData = addressbookservice.getData(token);

            ResponseDTO responseDTO = new ResponseDTO("Updated data", readData);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);

        }
    }

