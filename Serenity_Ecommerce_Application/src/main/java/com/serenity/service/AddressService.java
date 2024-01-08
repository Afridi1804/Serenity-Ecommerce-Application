package com.serenity.service;

import com.serenity.exception.AddressException;
import com.serenity.exception.CustomersException;
import com.serenity.model.Address;

public interface AddressService {

	public Address addAddress(Address address) throws AddressException;

	public Address getAddressByCustomerId(int cid) throws AddressException, CustomersException;

	public Address updateAddressByCustomerId(int cid, Address address) throws AddressException, CustomersException;

	public Address deleteAddressByCustomerId(int cid) throws AddressException, CustomersException;

}
