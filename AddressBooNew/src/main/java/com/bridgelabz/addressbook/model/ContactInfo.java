package com.bridgelabz.addressbook.model;

import com.bridgelabz.addressbook.dto.ContactDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "address_book")
public class ContactInfo {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "Contact_Id")
        private long contactId;
        @Column(name = "First_Name")
        private String firstName;
        private String lastName;
        private String address;
        private String city;
        private String state;
        private String zip;
        private String phone;
        @Temporal(TemporalType.TIMESTAMP)
        private Date registerDate;

        @Temporal(TemporalType.TIMESTAMP)
        private Date updateDate;

        public ContactInfo(ContactDTO contactDTO) {

            this.updateContact(contactDTO);
        }

        public ContactInfo() {

        }

        public void updateContact(ContactDTO contactDTO) {
            this.firstName = contactDTO.firstName;
            this.lastName = contactDTO.lastName;
            this.address = contactDTO.address;
            this.city = contactDTO.city;
            this.state = contactDTO.state;
            this.zip = contactDTO.zip;
            this.phone = contactDTO.phone;
            this.updateDate = new Date(System.currentTimeMillis());
        }

        public void createContact(ContactDTO contactDTO) {
            this.firstName = contactDTO.firstName;
            this.lastName = contactDTO.lastName;
            this.address = contactDTO.address;
            this.city = contactDTO.city;
            this.state = contactDTO.state;
            this.zip = contactDTO.zip;
            this.phone = contactDTO.phone;
            this.registerDate = new Date(System.currentTimeMillis());

        }
}
