package com.epam.kiev.skipass.factories;

import com.epam.kiev.skipass.factories.cards.SeasonSkiPass;
import com.epam.kiev.skipass.factories.cards.SkiPass;

public abstract class SkiPassAbstractFactory {
	
	public static final String WEEKEND_TYPE = "weekend";
	public static final String WORK_DAYS_TYPE = "work days";
	
	public static SkiPassFactory getSkiPassFactory(final String type){
		switch (type) {
		case WEEKEND_TYPE:
			return new WeekendSkiPassFactory();
		case WORK_DAYS_TYPE:
			return new WorkDaysSkiPassFactory();		
		default:
			return null;
		}		
	}
	
	public static SkiPass letOutSessonSkiPass(){
		return new SeasonSkiPass();
	}
}
