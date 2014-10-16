package com.gexin.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Splitter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SplitterTest {
	private static final String STR = ",a ,, , b, c ,d,";

	@Test
	public void test001_on() {
		Iterable<String> iterable = Splitter.on(",").split(STR);
		Iterator<String> iterator = iterable.iterator();
		String[] expect = new String[] { "", "a ", "", " ", " b", " c ", "d", "" };
		int index = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expect[index++], iterator.next());
		}
	}

	@Test
	public void test002_trimResults() {
		Iterable<String> iterable = Splitter.on(",").trimResults().split(STR);
		Iterator<String> iterator = iterable.iterator();
		String[] expect = new String[] { "", "a", "", "", "b", "c", "d", "" };
		int index = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expect[index++], iterator.next());
		}
	}

	@Test
	public void test003_omitEmptyStrings() {
		Iterable<String> iterable = Splitter.on(",").omitEmptyStrings().split(STR);
		Iterator<String> iterator = iterable.iterator();
		String[] expect = new String[] { "a ", " ", " b", " c ", "d" };
		int index = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expect[index++], iterator.next());
		}
	}

	@Test
	public void test004_limit() {
		Iterable<String> iterable = Splitter.on(",").limit(3).split(STR);
		Iterator<String> iterator = iterable.iterator();
		String[] expect = new String[] { "", "a ", ", , b, c ,d," };
		int index = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expect[index++], iterator.next());
		}
	}

	@Test
	public void test005_fixedLength() {
		Iterable<String> iterable = Splitter.fixedLength(5).split(STR);
		Iterator<String> iterator = iterable.iterator();
		String[] expect = new String[] { ",a ,,", " , b,", " c ,d", "," };
		int index = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(expect[index++], iterator.next());
		}
	}

	@Test
	public void test006_withKeyValueSeparator() {
		Map<String, String> map = Splitter.on("&").trimResults().omitEmptyStrings().withKeyValueSeparator("=").split(" a=1&b=2 &c =3 &d= 4");
		Assert.assertTrue(map.size() == 4);
		Assert.assertThat(map, Matchers.hasEntry("a", "1"));
		Assert.assertThat(map, Matchers.hasEntry("b", "2"));
		Assert.assertThat(map, Matchers.hasEntry("c ", "3"));
		Assert.assertThat(map, Matchers.hasEntry("d", " 4"));
	}

	@Test
	public void test007_splitToList() {
		List<String> list = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(STR);
		Assert.assertTrue(list.size() == 4);
		Assert.assertThat(list, Matchers.hasItems("a", "b", "c", "d"));
	}
}
