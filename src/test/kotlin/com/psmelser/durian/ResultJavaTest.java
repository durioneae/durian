package com.psmelser.durian;

import org.junit.Test;

public class ResultJavaTest {

	@Test
	public void test() {
		final Result<Object, RuntimeException> error = Result.Companion.error(new RuntimeException());

		error.apply(v -> {}, e -> {});
		error.map(v -> v, e -> e);
	}
}
