package com.poly.utils;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Convert {

	private static final Pattern NORMALIZE_NON_ASCII = Pattern.compile("[^\\p{ASCII}]+");
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	// In thÆ°á»�ng

	public static String toSlug(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		String slug2 = NORMALIZE_NON_ASCII.matcher(normalized).replaceAll("d");

		return slug2.toLowerCase(Locale.ENGLISH);
	}
}
