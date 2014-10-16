package com.gexin.test;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.collect.Lists;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListsTest {

	@Test
	public void test001_newArrayList() {
		List<String> list1 = Lists.newArrayList();
		Assert.assertThat(list1, Matchers.instanceOf(ArrayList.class));
		Assert.assertThat(list1, Matchers.empty());

		List<String> list2 = Lists.newArrayList("a", "b");
		Assert.assertThat(list2, Matchers.hasSize(2));
		Assert.assertThat(list2, Matchers.hasItems("a", "b"));
	}

}
