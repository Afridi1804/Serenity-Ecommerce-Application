package com.serenity.service;

import java.util.List;

import com.serenity.exception.OrdersException;
import com.serenity.model.Orders;

public interface OrdersService {

	public Orders createOrder(Orders order) throws OrdersException;

	public Orders getOrderById(int oid) throws OrdersException;

	public List<Orders> getAllOrders() throws OrdersException;

	public Orders updateOrders(int oid, Orders orders) throws OrdersException;

	public Orders deleteOrders(int oid) throws OrdersException;

}
