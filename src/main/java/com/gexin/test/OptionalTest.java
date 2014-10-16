package com.gexin.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OptionalTest {

	@Test(expected = NullPointerException.class)
	public void test001_of() {
		Optional<String> opt1 = Optional.of("test");
		Assert.assertEquals("test", opt1.get());

		Optional.of(null);
	}

	@Test(expected = IllegalStateException.class)
	public void test002_fromNullable() {
		Optional<String> opt1 = Optional.fromNullable("test");
		Assert.assertEquals("test", opt1.get());

		Optional<String> opt2 = Optional.fromNullable(null);
		Assert.assertFalse(opt2.isPresent());

		opt2.get();
	}

	@Test
	public void test003_or() {
		Optional<String> opt1 = Optional.fromNullable("test");
		Assert.assertEquals("test", opt1.or("default"));

		Optional<String> opt2 = Optional.fromNullable(null);
		Assert.assertEquals("default", opt2.or("default"));
	}

	@Test
	public void test004_orNull() {
		Optional<String> opt1 = Optional.fromNullable("test");
		Assert.assertEquals("test", opt1.orNull());

		Optional<String> opt2 = Optional.fromNullable(null);
		Assert.assertEquals(null, opt2.orNull());
	}

	@Test
	public void test005_isPresent() {
		Optional<String> opt1 = Optional.fromNullable("test");
		Assert.assertTrue(opt1.isPresent());

		Optional<String> opt2 = Optional.fromNullable(null);
		Assert.assertFalse(opt2.isPresent());
	}

	@Test
	public void test006_asSet() {
		Optional<String> opt1 = Optional.fromNullable("test");
		Assert.assertThat(opt1.asSet(), CoreMatchers.hasItem("test"));

		Optional<String> opt2 = Optional.fromNullable(null);
		Assert.assertTrue(opt2.asSet().isEmpty());
	}

	@Test
	public void test007_presentInstances() {
		List<Optional<?>> list = new ArrayList<Optional<?>>();
		list.add(Optional.fromNullable(null));
		list.add(Optional.fromNullable("aaa"));
		list.add(Optional.fromNullable("bbb"));

		Iterable<Object> iter = Optional.presentInstances(list);
		Iterator<Object> iteraotr = iter.iterator();
		while (iteraotr.hasNext()) {
			Assert.assertThat(iteraotr.next(), CoreMatchers.anyOf(CoreMatchers.is((Object) "aaa"), CoreMatchers.is((Object) "bbb")));
		}
	}
}
