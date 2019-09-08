package com.codingotaku.apis.animecrawler.helpers;

public final class Preconditions {
	public static void checkNotNull(Object ob, String message) {
		if (ob == null) {
			throw new NullPointerException(message);
		}
	}

	public static void checkArgument(boolean expression, String message) {
		if (!expression) {
			throw new NullPointerException(message);
		}
	}
}
