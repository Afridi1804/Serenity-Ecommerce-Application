package com.serenity.service;

import java.util.List;

import com.serenity.exception.PaymentsException;
import com.serenity.model.Payments;

public interface PaymentsService {

	public Payments createPayements(Payments payments) throws PaymentsException;

	public Payments getPaymentsById(int payid) throws PaymentsException;

	public List<Payments> getAllPayments() throws PaymentsException;

	public Payments updatePayments(int payid, Payments payments) throws PaymentsException;

	public Payments deletePayments(int payid) throws PaymentsException;

}
