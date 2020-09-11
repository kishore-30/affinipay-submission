package com.affinipay.assignment.representation;

public class UserRequestRepresentation {

	protected String time;

	protected int deltaminutes;

	public int getDeltaminutes() {
		return deltaminutes;
	}

	public void setDeltaminutes(int deltaminutes) throws Exception {
		if (deltaminutes < Integer.MIN_VALUE || deltaminutes > Integer.MAX_VALUE) {
			throw new Exception(" deltaminutes not in range of " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
		}
		this.deltaminutes = deltaminutes;
	}

	public String getTime() {
		return time;
	}

	public boolean setTime(String time) throws Exception {
		
		time = time.trim();
		String[] splittedTimeAndMidDay = time.split(" ");

		if (splittedTimeAndMidDay.length != 2) {
			throw new Exception(" invalid time format " + time);
		}

		String timeSplit = splittedTimeAndMidDay[0];
		String[] splittedHourAndMinute = timeSplit.split(":");

		if (splittedHourAndMinute.length != 2) {
			throw new Exception(" invalid time format " + time);
		}

		int hour = Integer.valueOf(splittedHourAndMinute[0]);

		if (hour < 0 || hour > 12) {
			throw new Exception(" invalid time format " + time);
		}

		if (splittedHourAndMinute[1].charAt(0) == '0') {
			splittedHourAndMinute[1] = splittedHourAndMinute[1].substring(1);
		}

		int minutes = Integer.valueOf(splittedHourAndMinute[1]);

		if (minutes < 0 || minutes > 59) {
			throw new Exception(" invalid time format " + time);
		}

		String midday = splittedTimeAndMidDay[1];

		if (!midday.equals("AM") && !midday.equals("PM")) {
			throw new Exception(" invalid time format " + time);
		}
		
		this.time = time;
		
		return true;
	}

}
