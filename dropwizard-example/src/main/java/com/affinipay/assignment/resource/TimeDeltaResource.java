package com.affinipay.assignment.resource;

import java.util.UUID;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.affinipay.assignment.representation.UserRequestRepresentation;
import com.affinipay.assignment.representation.UserResponseExceptionRepresentation;
import com.affinipay.assignment.representation.UserResponseRepresentation;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/assignment/v1")
@Produces(MediaType.APPLICATION_JSON)
public class TimeDeltaResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeDeltaResource.class);

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTime(String request) {

		String transactionId = UUID.randomUUID().toString();
		ObjectMapper mapper = new ObjectMapper();
		String newTime = null;

		try {

			UserRequestRepresentation userRequest = mapper.readValue(request, UserRequestRepresentation.class);
			LOGGER.info(transactionId + " request received with time: " + userRequest.getTime() + " and delta "
					+ userRequest.getDeltaminutes());

			newTime = addDelta(transactionId, userRequest.getTime(), userRequest.getDeltaminutes());

		} catch (Exception e) {
			UserResponseExceptionRepresentation responseA = new UserResponseExceptionRepresentation(transactionId,
					e.getMessage());
			ResponseBuilder builder = Response.status(400).entity(responseA);

			return builder.build();

		}
		UserResponseRepresentation responseA = new UserResponseRepresentation(transactionId, newTime);
		ResponseBuilder builder = Response.ok(responseA);

		LOGGER.info(transactionId + " response sent with new time: " + newTime);
		return builder.build();
	}

	public String addDelta(String transactionId, String time, int deltaminutes) throws Exception {

		String[] hourAndMinute = getHourAndMinute(time);
		int inputHour = Integer.valueOf(hourAndMinute[0]);
		int inputMinutes = Integer.valueOf(hourAndMinute[1]);
		int totalMinutes = 0;

		String[] test = time.trim().split(" ");
		String test1 = test[1];
		if (test1.equals("PM")) {
			totalMinutes = (inputHour * 60 + inputMinutes + deltaminutes + 720) % 1440;
		} else {
			totalMinutes = (inputHour * 60 + inputMinutes + deltaminutes) % 1440;
		}

		if (totalMinutes < 0) {
			throw new Exception(" invalid deltaminutes " + deltaminutes);
		}

		String newMidDay = totalMinutes > 719 && totalMinutes < 1439 ? "PM" : "AM";

		int newMinutes = totalMinutes % 60;
		String newMinutesInString = String.valueOf(newMinutes);

		if (newMinutesInString.length() == 1) {
			newMinutesInString = "0" + newMinutesInString;
		}

		int newHour = totalMinutes / 60;

		if (newHour == 0) {
			newHour = 12;
		}
		newHour = newHour > 12 ? newHour % 12 : newHour;

		LOGGER.info(transactionId + " new time: " + newHour + ":" + newMinutesInString + " " + newMidDay);

		String timeInString = String.valueOf(newHour) + ":" + newMinutesInString + " " + newMidDay;

		return timeInString;
	}

	private String[] getHourAndMinute(String time) {

		String[] hourAndMinute = new String[2];
		time = time.trim();
		String[] splittedTimeAndMidDay = time.split(" ");
		String timeSplit = splittedTimeAndMidDay[0];
		String[] splittedHourAndMinute = timeSplit.split(":");
		hourAndMinute[0] = splittedHourAndMinute[0];
		hourAndMinute[1] = splittedHourAndMinute[1];

		return hourAndMinute;

	}

}
