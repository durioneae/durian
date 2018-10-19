package com.psmelser.durian;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ResultJavaTest {

	@Test
	public void testMap() {
		final Result<Object, RuntimeException> value = Result.Companion.value("hello");

		final String valueMapped = value.map(v -> v + " ahh", Throwable::getMessage);
		final Result<String, RuntimeException> mapValue = value.mapValue(v -> v + " ahh");

		assertThat(valueMapped).isEqualTo("hello ahh");
		assertThat(mapValue.getValue()).isEqualTo("hello ahh");
	}
}
