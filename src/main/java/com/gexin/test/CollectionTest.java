package com.gexin.test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollectionTest {

	@Test
	public void test001_createCollectionObject() {
		// 简化集合类创建
		List<List<Map<String, String>>> list1 = Lists.newArrayList();
		assertThat(list1, instanceOf(ArrayList.class));
		assertThat(list1, empty());

		// 简化创建集合类及赋初值
		List<String> list2 = Lists.newArrayList("a", "b");
		assertThat(list2, hasSize(2));
		assertThat(list2, hasItems("a", "b"));

		// 创建不可变集合并赋初值
		Map<String, String> map = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE");
		assertEquals(2, map.size());
		assertThat(map, allOf(hasEntry("ON", "TRUE"), hasEntry("OFF", "FALSE")));
	}

	@Test
	public void test002_MultiMap() {
		// 可以存在相同KEY的MAP，get(key)返回集合，其它Multimap子类功能类似
		Multimap<String, String> map = ArrayListMultimap.create();
		map.put("AA", "11");
		map.put("AA", "22");
		map.put("AA", "22");
		map.put("BB", "11");

		assertEquals(4, map.size());
		Collection<String> collect = map.get("AA");
		assertThat(collect, hasSize(3));
		assertThat(collect, hasItems("11", "22", "22"));
	}

	@Test
	public void test003_MultiSet() {
		// 可以存在重复值的Set，可系统存在相同值个数
		Multiset<String> set = HashMultiset.create();
		set.add("AA");
		set.add("BB");
		set.add("AA");

		assertThat(set, hasSize(3));
		assertEquals(2, set.count("AA"));
	}

	@Test
	public void test004_Table() {
		// rowkey, column, value；rowkey和column唯一确定一条记录
		Table<Integer, String, Long> table = HashBasedTable.create();
		table.put(1, "A", 100L);
		table.put(2, "B", 200L);
		table.put(1, "AA", 110L);
		table.put(3, "B", 300L);
		table.put(1, "A", 101L);

		assertEquals(2, table.row(1).size());
		assertThat(table.row(1), allOf(hasEntry("A", 101L), hasEntry("AA", 110L)));
		assertEquals(2, table.column("B").size());
		assertThat(table.column("B"), allOf(hasEntry(2, 200L), hasEntry(3, 300L)));
	}

	@Test
	public void test005_BiMap() {
		// 是一个一一映射，可以通过key得到value，也可以通过value得到key；
		BiMap<Integer, String> map = HashBiMap.create();
		map.put(1, "A");
		map.put(1, "B");
		// key重复会复覆盖，value重复抛异常，需要用forcePut替代
		// map.put(2, "B");
		map.put(3, "C");

		assertEquals(2, map.size());
		assertThat(map, allOf(hasEntry(1, "B"), hasEntry(3, "C")));

		map.forcePut(2, "B");
		assertEquals(2, map.size());
		assertThat(map, allOf(hasEntry(2, "B"), hasEntry(3, "C")));

		// key和value互换
		BiMap<String, Integer> inverseMap = map.inverse();
		assertEquals(2, inverseMap.size());
		assertThat(inverseMap, allOf(hasEntry("B", 2), hasEntry("C", 3)));
	}
}
