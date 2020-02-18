package com.poly.utils;

import java.util.ArrayList;
import java.util.List;

import com.poly.entities.OrderDetail;
import com.poly.entities.Product;

public class Cart {

	private static final int LIMIT_ITEMS = 10;

	private List<OrderDetail> items;

	public Cart() {
		items = new ArrayList<OrderDetail>();
	}

	public List<OrderDetail> getOrderDetails() {
		return items;
	}

	public void add(Product product) {
		OrderDetail item = new OrderDetail();
		item.setProduct(product);
		item.setQty(1);
		// check item exist
		for (OrderDetail e : items) {
			if (e.getProduct().getId() == product.getId()) {
				if (e.getQty() + 1 <= LIMIT_ITEMS) {
					e.setQty(e.getQty() + 1);
				} else {
					e.setQty(LIMIT_ITEMS);
				}
				return;
			}
		}
		items.add(item);
	}

	public void update(Product product, int qty) {
		OrderDetail item = new OrderDetail();
		item.setProduct(product);
		item.setQty(qty);
		if (qty == 0) {
			remove(product.getId());
		} else {
			// check item exist
			for (OrderDetail e : items) {
				if (e.getProduct().getId() == product.getId()) {
					if (qty <= LIMIT_ITEMS) {
						e.setQty(qty);
					} else {
						e.setQty(LIMIT_ITEMS);
					}
					return;
				}
			}
		}
		items.add(item);
	}

	public void remove(int id) {
		for (OrderDetail e : items) {
			if (e.getProduct().getId() == id) {
				items.remove(e);
				return;
			}
		}
	}

	public int getCount() {
		return items.size();
	}

	public int getTotal() {
		int total = 0;
		for (OrderDetail e : items) {
			total += (e.getProduct().getPrice() * e.getQty());
		}
		return total;
	}
}
