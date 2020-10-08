package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskeyByYear(){
		List<Whisky> found = whiskyRepository.findWhiskyByYear(2018);
		assertEquals(6, found.size());
	}

	@Test
	public void canFindDistilleryByRegion(){
		List<Distillery> found = distilleryRepository.findDistilleryByRegion("Highland");
		assertEquals(3, found.size());
	}

	@Test
	public void canFindWhiskyByAgeByDistillery(){
		Distillery distillery = distilleryRepository.findDistilleryByName("Highland Park");
		List<Whisky> found = whiskyRepository.findWhiskyByAgeAndDistilleryId(12,distillery.getId());
		assertEquals("Viking Honour",found.get(0).getName());
	}
	@Test
	public void canFindWhiskyByDistilleryRegion(){
		List<Whisky> found = whiskyRepository.findWhiskyByDistilleryRegion("Highland");
		assertEquals(7, found.size());
	}
	@Test
	public void canFindDistilleryByWhiskyAge(){
		List<Distillery> found = distilleryRepository.findDistilleryByWhiskiesAge(25);
		assertEquals(1, found.size());
	}
}
