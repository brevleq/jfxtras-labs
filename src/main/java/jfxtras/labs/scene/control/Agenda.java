/**
 * Copyright (c) 2011, JFXtras
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jfxtras.labs.scene.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

/**
 * Agenda control
 *  
 * @author Tom Eugelink
 */
public class Agenda extends Control
{
	// ==================================================================================================================
	// CONSTRUCTOR
	
	/**
	 * 
	 */
	public Agenda()
	{
		construct();
	}
	
	/*
	 * 
	 */
	private void construct()
	{
		// setup the CSS
		// the -fx-skin attribute in the CSS sets which Skin class is used
		this.getStyleClass().add(this.getClass().getSimpleName());
	}

	/**
	 * Return the path to the CSS file so things are setup right
	 */
	@Override protected String getUserAgentStylesheet()
	{
		return this.getClass().getResource("/jfxtras/labs/internal/scene/control/" + this.getClass().getSimpleName() + ".css").toString();
	}
	
	// ==================================================================================================================
	// PROPERTIES
	
	/** Appointments: */
	public ObservableList<Appointment> appointments() { return appointments; }
	final private ObservableList<Appointment> appointments =  javafx.collections.FXCollections.observableArrayList();
	final static public String APPOINTMENTS_PROPERTY_ID = "appointments";

	/** Locale: the locale is used to determine first-day-of-week, weekday labels, etc */
	public ObjectProperty<Locale> localeProperty() { return iLocaleObjectProperty; }
	final private ObjectProperty<Locale> iLocaleObjectProperty = new SimpleObjectProperty<Locale>(this, "locale", Locale.getDefault());
	public Locale getLocale() { return iLocaleObjectProperty.getValue(); }
	public void setLocale(Locale value) { iLocaleObjectProperty.setValue(value); }
	public Agenda withLocale(Locale value) { setLocale(value); return this; } 

	/** 
	 * DisplayedCalendar: this calendar denotes the timeframe being displayed. 
	 * If the agenda is in week skin, it will display the week containing this date. (Things like FirstDayOfWeek are taken into account.)
	 * In month skin, the month containing this date.
	 */
	public ObjectProperty<Calendar> displayedCalendar() { return displayedCalendarObjectProperty; }
	volatile private ObjectProperty<Calendar> displayedCalendarObjectProperty = new SimpleObjectProperty<Calendar>(this, "displayedCalendar", Calendar.getInstance());
	public Calendar getDisplayedCalendar() { return displayedCalendarObjectProperty.getValue(); }
	public void setDisplayedCalendar(Calendar value) { displayedCalendarObjectProperty.setValue(value); }
	public Agenda withDisplayedCalendar(Calendar value) { setDisplayedCalendar(value); return this; }
	
	
	// ==================================================================================================================
	// Appointment
	
	/**
	 * The interface that all appointments must adhere to; you can provide your own implementation.
	 */
	static public interface Appointment
	{
		public Calendar getStartTime();
		public Calendar getEndTime();
		public Boolean isWholeDay();
		public String getSummary();
		public String getDescription();
		public String getLocation();
		public String getGroup(); // this will result in a CSS class being assigned
	}
	
	/**
	 * A class to help you get going; all the required methods of the interface are implemented as JavaFX properties 
	 */
	static public class AppointmentImpl 
	implements Appointment
	{
		/** StartTime: */
		public ObjectProperty<Calendar> startTimeProperty() { return startTimeObjectProperty; }
		final private ObjectProperty<Calendar> startTimeObjectProperty = new SimpleObjectProperty<Calendar>(this, "startTime");
		public Calendar getStartTime() { return startTimeObjectProperty.getValue(); }
		public void setStartTime(Calendar value) { startTimeObjectProperty.setValue(value); }
		public AppointmentImpl withStartTime(Calendar value) { setStartTime(value); return this; }
		
		/** EndTime: */
		public ObjectProperty<Calendar> endTimeProperty() { return endTimeObjectProperty; }
		final private ObjectProperty<Calendar> endTimeObjectProperty = new SimpleObjectProperty<Calendar>(this, "endTime");
		public Calendar getEndTime() { return endTimeObjectProperty.getValue(); }
		public void setEndTime(Calendar value) { endTimeObjectProperty.setValue(value); }
		public AppointmentImpl withEndTime(Calendar value) { setEndTime(value); return this; } 
		
		/** WholeDay: */
		public ObjectProperty<Boolean> wholeDayProperty() { return wholeDayObjectProperty; }
		final private ObjectProperty<Boolean> wholeDayObjectProperty = new SimpleObjectProperty<Boolean>(this, "wholeDay", false);
		public Boolean isWholeDay() { return wholeDayObjectProperty.getValue(); }
		public void setWholeDay(Boolean value) { wholeDayObjectProperty.setValue(value); }
		public AppointmentImpl withWholeDay(Boolean value) { setWholeDay(value); return this; } 
		
		/** Summary: */
		public ObjectProperty<String> summaryProperty() { return summaryObjectProperty; }
		final private ObjectProperty<String> summaryObjectProperty = new SimpleObjectProperty<String>(this, "summary");
		public String getSummary() { return summaryObjectProperty.getValue(); }
		public void setSummary(String value) { summaryObjectProperty.setValue(value); }
		public AppointmentImpl withSummary(String value) { setSummary(value); return this; } 
		
		/** Description: */
		public ObjectProperty<String> descriptionProperty() { return descriptionObjectProperty; }
		final private ObjectProperty<String> descriptionObjectProperty = new SimpleObjectProperty<String>(this, "description");
		public String getDescription() { return descriptionObjectProperty.getValue(); }
		public void setDescription(String value) { descriptionObjectProperty.setValue(value); }
		public AppointmentImpl withDescription(String value) { setDescription(value); return this; } 
		
		/** Location: */
		public ObjectProperty<String> locationProperty() { return locationObjectProperty; }
		final private ObjectProperty<String> locationObjectProperty = new SimpleObjectProperty<String>(this, "location");
		public String getLocation() { return locationObjectProperty.getValue(); }
		public void setLocation(String value) { locationObjectProperty.setValue(value); }
		public AppointmentImpl withLocation(String value) { setLocation(value); return this; } 
		
		/** Group: */
		public ObjectProperty<String> groupProperty() { return groupObjectProperty; }
		final private ObjectProperty<String> groupObjectProperty = new SimpleObjectProperty<String>(this, "group");
		public String getGroup() { return groupObjectProperty.getValue(); }
		public void setGroup(String value) { groupObjectProperty.setValue(value); }
		public AppointmentImpl withGroup(String value) { setGroup(value); return this; } 
	}
	
	
	// ==================================================================================================================
	// SUPPORT

	/*
	 * 
	 */
	static public String quickFormatCalendar(Calendar value)
	{
		if (value == null) return "null";
		SimpleDateFormat lSimpleDateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG);
		lSimpleDateFormat.applyPattern("yyyy-MM-dd");
		return value == null ? "null" : lSimpleDateFormat.format(value.getTime());
	}
	static public String quickFormatCalendar(List<Calendar> value)
	{
		if (value == null) return "null";
		String s = value.size() + "x [";
		for (Calendar lCalendar : value)
		{
			if (s.endsWith("[") == false) s += ",";
			s += quickFormatCalendar(lCalendar);
		}
		s += "]";
		return s;
	}
}
