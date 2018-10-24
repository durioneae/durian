package com.psmelser.durian.file;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ResourceJavaTest {
	@Test
	public void test() {
		assertThat(Resource.Companion.parseFile("test.txt").getText()).isEqualTo("This is a test");
	}

	@Test
	public void testFlat() {
		var file = Resource.Companion.parseFile("multi-line-text.txt");

		assertThat(file.getFlattenedText()).isEqualTo("This is a testwith more than one line");
	}

	@Test
	public void testNoWhite() {
		var file = Resource.Companion.parseFile("multi-line-text.txt");

		assertThat(file.getNoWhitespaceText()).isEqualTo("Thisisatestwithmorethanoneline");
	}

	@Test
	public void testMultiLine() {
		var file = Resource.Companion.parseFile("multi-line-text.txt");

		assertThat(file.getText()).isEqualTo("This is a test\nwith more than one line");
	}
}
