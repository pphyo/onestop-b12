package com.jdc.ps.entity;

public record TotalPopulationByRegion(
		Region region, 
		int stateCount, 
		int totalPopulation) {}
