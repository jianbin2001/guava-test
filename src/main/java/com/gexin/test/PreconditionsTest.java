package com.gexin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.base.Preconditions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreconditionsTest {

	@Test(expected = IllegalArgumentException.class)
	public void test001_checkArgument() {
		String name = "";
		Preconditions.checkArgument(name != null && name.length() > 0, "the name is blank");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test002_checkElementIndex() {
		List<String> list = new ArrayList<String>();
		Preconditions.checkElementIndex(1, list.size(), "the index is out of bound");
	}

	@Test(expected = NullPointerException.class)
	public void test003_checkNotNull() {
		String name = null;
		Preconditions.checkNotNull(name, "the name can't be null");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void test004_checkPositionIndex() {
		List<String> list = new ArrayList<String>();
		Preconditions.checkPositionIndex(1, list.size(), "the index is out of bound");
	}

	@Test(expected = IllegalStateException.class)
	public void test004_checkState() {
		int age = 9;
		Preconditions.checkState(age >= 10, "age must greate than 10", age);
	}
}
