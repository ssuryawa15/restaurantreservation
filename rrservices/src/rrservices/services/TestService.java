package rrservices.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rrservices.dao.ConfirmationsDao;
import rrservices.dao.ReservationRequestDao;
import rrservices.dao.SeatingDao;
import rrservices.dao.ProfileDao;

import rrservices.dao.TimeSlotDao;
import rrservices.dao.UserDao;
import rrservices.dataconnections.DBUtil;
import rrservices.datamodel.Confirmations;
import rrservices.datamodel.Profile;
import rrservices.datamodel.ReservationRequest;
import rrservices.datamodel.Seating;
import rrservices.datamodel.TimeSlot;
import rrservices.datamodel.User;
import rrservices.util.MyUtils;


@Path("/Test")
public class TestService{

	@GET
	@Path("/reservationrequest")
	
	@Produces(MediaType.APPLICATION_JSON)
	public Object getReservationRequest(@QueryParam("id")int primarykey) throws SQLException {
		ReservationRequestDao rrd = new ReservationRequestDao();
		
        return rrd.getReservationRequest(primarykey);
}
	@GET
	@Path("/reservationrequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getListOfReservationRequest() throws SQLException {
		ReservationRequestDao rrd = new ReservationRequestDao();
		List l = rrd.getAllReservationRequests();
        return l;
}

	@GET
	@Path("/seating")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getListOfSeatings() throws SQLException {
		SeatingDao sd = new SeatingDao();
		List l = sd.getAll();
        return l;
}
	
	@POST
	@Path("/vacant_seating")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object getListOfVacantSeatings(ReservationRequest rr) throws SQLException {
		SeatingDao sd = new SeatingDao();
		List l = sd.getVacant(rr.getTimeslot(), rr.getDate(),rr.getPartysize());
        return l;
}
	
	@POST
	@Path("/addReservationRequest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object addReservationRequest(ReservationRequest rr) throws SQLException {
		System.out.println("json is " + rr);
		ReservationRequestDao rrdao = new ReservationRequestDao();
		rr = rrdao.addReservationRequest(rr);
		
        return rr;
}
	
	
	@POST
	@Path("/addConfirmation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object addConfirmation(Confirmations confirmation) throws SQLException {
		ConfirmationsDao cdao = new ConfirmationsDao();
		cdao.addConfirmations(confirmation);
		ReservationRequestDao rrdao = new ReservationRequestDao();
		ReservationRequest rr = confirmation.getReservation();
		rr.setStatus("c");
		
        return rrdao.updateReservationRequest(rr);
}
	
	@POST
	@Path("/deleteConfirmation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object delConfirmation(Confirmations confirmation) throws SQLException {
		ConfirmationsDao cdao = new ConfirmationsDao();
		cdao.delConfirmations(confirmation);
		ReservationRequestDao rrdao = new ReservationRequestDao();
		ReservationRequest rr = confirmation.getReservation();
		rr.setStatus("w");
		
        return rrdao.updateReservationRequest(rr);
}
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object dologin(User user) throws SQLException {
		UserDao udao = new UserDao();
		
        return udao.getUser(user);
}
	
	
	
	@POST
	@Path("/editReservationRequest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object editReservation(ReservationRequest rr) throws SQLException {
		ReservationRequestDao rrdao = new ReservationRequestDao();
		
		ConfirmationsDao cdao = new ConfirmationsDao();
		Confirmations conf = new Confirmations();
		conf.setReservation(rr);
		ReservationRequest oldrr = rrdao.getReservationRequest(rr.getPrimarykey());
		rr.setStatus(oldrr.getStatus());
		String oldDate = oldrr.getDate();
		String newDate = oldrr.getDate();
		int oldTimeslot = oldrr.getTimeslot();
		int newTimeslot = rr.getTimeslot();
		int oldPartySize = oldrr.getPartysize();
		int newPartySize = rr.getPartysize();
		if(oldDate.equals(newDate) && newPartySize<=oldPartySize && oldTimeslot==newTimeslot)
		{
			//do nothing
			
		}
		else
		{
			cdao.delConfirmations(conf);
			rr.setStatus("w");
		}
		rrdao.updateReservationRequest(rr);
		
		
        return rrdao.updateReservationRequest(rr);
}
	
	@GET
	@Path("/getConfirmation")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getConfirmation() throws SQLException {
		
		ConfirmationsDao cdao = new ConfirmationsDao();
		List<Confirmations> confirmation = cdao.getAll();
		
        return confirmation;
}
	
	@GET
	@Path("/getTimeSlots")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getTimeSlots() throws SQLException {
		
		TimeSlotDao tdao = new TimeSlotDao();
		List<TimeSlot> timeslots = tdao.getAll();
		
        return timeslots;
}
	
	@GET
	@Path("/getProfile")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getProfile() throws SQLException {
		
		ProfileDao pdao = new ProfileDao();
		
        return pdao.getProfile();
}

	@POST
	@Path("/addprofile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Object addProfile(Profile profile) throws SQLException {
		ProfileDao pdao = new ProfileDao();
		Profile p = pdao.getProfile();
		if(p==null)
		{
			pdao.addProfile(profile);
		}
		else
		{
			pdao.updateProfile(profile);
		}
        return null;
}
	
	
}


