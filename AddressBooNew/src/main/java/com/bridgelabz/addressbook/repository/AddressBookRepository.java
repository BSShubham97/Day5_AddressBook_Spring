package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<ContactInfo, Long> {

    @Query(value = "select * from address_book where city= :city", nativeQuery = true)
    List<ContactInfo> findContactListByCity(String city);

    @Query(value = "select * from address_book where first_Name= :firstName", nativeQuery = true)
    List<ContactInfo> findContactListByFirstName(String firstName);

    @Query(value = "select * from address_book where last_Name= :lastName", nativeQuery = true)
    List<ContactInfo> findContactListByLastName(String lastName);

    @Query(value = "select * from address_book where zip= :zip", nativeQuery = true)
    List<ContactInfo> findContactListByZip(String zip);

    @Query(value = "select * from address_book order by first_name;", nativeQuery = true)
    List<ContactInfo> sortByName();

    @Query(value = "select * from address_book order by city;", nativeQuery = true)
    List<ContactInfo> sortByCity();

    @Query(value = "select * from address_book order by zip;", nativeQuery = true)
    List<ContactInfo> sortByPincode();


}

