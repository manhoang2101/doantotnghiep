package com.poly.utils;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean checkNumberPhone(String number) {
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(number);
		if (matcher.matches()) {
			if (number.length() == 10 || number.length() == 11) {
				if (number.length() == 10) {
					if (number.substring(0, 2).equals("09")) {
						return true;
					}
				} else if (number.substring(0, 2).equals("01")) {
					return true;
				}
			}
		}

		return false;
	}

	public static void merge(Object obj, Object update) {
		if (!obj.getClass().isAssignableFrom(update.getClass())) {
			return;
		}

		Method[] methods = obj.getClass().getMethods();

		for (Method fromMethod : methods) {
			if (fromMethod.getDeclaringClass().equals(obj.getClass()) && fromMethod.getName().startsWith("get")) {

				String fromName = fromMethod.getName();
				String toName = fromName.replace("get", "set");

				try {
					Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
					Object value = fromMethod.invoke(update, (Object[]) null);
					if (value != null) {
						toMetod.invoke(obj, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
