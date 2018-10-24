package com.psmelser.durian;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class EitherJavaTest {

	@Test
	public void testLeftAndMap_mustMapCorrectValue() {
		final String left = Either.Companion.<String, String>left("left")
				.map(l -> l.concat(":OK"), r -> r.concat(":BAD"));

		assertThat(left).isEqualTo("left:OK");
	}

	@Test
	public void test() {
		Either.Companion.<String, String>left("left")
				.apply(l -> {
					assertThat(l).isEqualTo("left");
					return null;
				}, r -> {
					fail();
					return null;
				});
	}

	@Test
	public void test3() {
		Either.Companion.<String, String>left("left")
				.ifLeft(l -> {
					assertThat(l).isEqualTo("left");
					return null;
				});
	}

	@Test
	public void test10() {
		Either.Companion.<String, String>left("left")
				.ifRight(l -> {
					fail();
					return null;
				});
	}

	@Test
	public void test4() {
		final Either<String, String> either = Either.Companion.left("left");

		assertThat(either.isLeft()).isTrue();
	}

	@Test
	public void test5() {
		final String actual = Either.Companion.left("left").mapLeft(l -> l.concat(":OK"));

		assertThat(actual).isEqualTo("left:OK");
	}

	@Test
	public void test6() {
		final String left = Either.Companion.<String, String>right("right")
				.map(l -> l.concat(":BAD"), r -> r.concat(":OK"));

		assertThat(left).isEqualTo("right:OK");
	}

	@Test
	public void test2() {
		Either.Companion.<String, String>right("right")
				.apply(l -> {
					fail();
					return null;
				}, r -> {
					assertThat(r).isEqualTo("right");
					return null;
				});
	}

	@Test
	public void test7() {
		Either.Companion.<String, String>right("right")
				.ifRight(l -> {
					assertThat(l).isEqualTo("right");
					return null;
				});
	}

	@Test
	public void test11() {
		Either.Companion.<String, String>right("right")
				.ifLeft(l -> {
					fail();
					return null;
				});
	}

	@Test
	public void test8() {
		final Either<String, String> either = Either.Companion.right("right");

		assertThat(either.isRight()).isTrue();
	}

	@Test
	public void test9() {
		final String actual = Either.Companion.right("right").mapRight(r -> r.concat(":OK"));

		assertThat(actual).isEqualTo("right:OK");
	}
}
