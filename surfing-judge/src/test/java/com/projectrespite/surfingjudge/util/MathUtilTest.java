package com.projectrespite.surfingjudge.util;

import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MathUtilTest {

	@Test
	public void average() {
		val testArray = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		val result = MathUtil.average(testArray);
		assertThat(result, is(3.0));
	}

	@Test
	public void averageDuplicateMax() {
		val testArray = Arrays.asList(1.0, 2.0, 3.0, 4.0, 4.0);
		val result = MathUtil.average(testArray);
		assertThat(result, is(2.8));
	}

	@Test
	public void averageDuplicateMin() {
		val testArray = Arrays.asList(2.0, 2.0, 3.0, 4.0, 5.0);
		val result = MathUtil.average(testArray);
		assertThat(result, is(3.2));
	}

	@Test
	public void averageListSize2() {
		val testArray = Arrays.asList(2.0, 5.0);
		val result = MathUtil.average(testArray);
		assertThat(result, is(3.5));
	}

	@Test
	public void sumBestAndSecondBest() {
		val testArray = Arrays.asList(5.0, 2.0, 3.0, 4.0);
		val result = MathUtil.sumBestAndSecondBest(testArray);
		assertThat(result, is(9.0));
	}

	@Test
	public void sumBestAndSecondBestListSize0() {
		val testArray = Collections.EMPTY_LIST;
		val result = MathUtil.sumBestAndSecondBest(testArray);
		assertThat(result, is(0.0));
	}

	@Test
	public void sumBestAndSecondBestListSize1() {
		val testArray = Collections.singletonList(5.0);
		val result = MathUtil.sumBestAndSecondBest(testArray);
		assertThat(result, is(5.0));
	}
}
