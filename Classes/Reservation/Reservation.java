package Classes.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import Classes.Table.TableManager;

/**
 * The Reservation Class.
 * Constructor and setter for the variables.
 * 
 * @author Zhang Erli
 * @version 1.0
 */

public class Reservation {

	/**
     * The sessions enum of the reservation, AM or PM.
     */
	public enum ReservationSession {
		AM, PM
	};

    /**
     * Serial number of the reservation.
     */
	private int Id;

	/**
     * The reservation date. 
     */
	private LocalDate resvDate;

	/**
     * The reservation date. 
     */
	private LocalTime resvTime;

	/**
     * Session of reservation, AM or PM
     */
	private ReservationSession resvSession;

	/**
     * The customer's telephone number.
     */
	private String customerContact;

	/**
     * The name of the customer who made the reservation
     */
	private String customerName;

	/**
     * The number of people
     */
	private int numPax;

	/**
     * The table number linked to Reservation
     */
	private int tableID;
	
    /**
     * Constructor for reservation
     *
     * @param id            Reservation ID  
     * @param rd            Reservation Date
     * @param rt            Reservation Time
     * @param session       Reservation Session
     * @param custContact   Customer Telephone Number
     * @param custName      Customer Name
     * @param pax           Number of customers
     * @param tableId       Table number assigned
     */

	public Reservation(int id, LocalDate rd, LocalTime rt, char session, String custContact, String custName, int pax,
			int tableId) {
		this.Id = id;
		this.resvDate = rd;
		this.resvTime = rt;
		this.resvSession = session == 'A' ? ReservationSession.AM : ReservationSession.PM;
		this.customerContact = custContact;
		this.customerName = custName;
		this.numPax = pax;
		this.tableID = tableId;
		TableManager.getTableByID(tableId).setReserved();
	}

    /**
     * Accessor for Reservation ID
     *
     * @return Reservation ID in integer
     */

	public int getResvId() {
		return Id;
	}

    /**
     * Accessor for Reservation Date
     *
     * @return Rservation Date in LocalDate
     */

	public LocalDate getResvDate() {
		return resvDate;
	}

    /**
     * Accessor for Reservation Time
     *
     * @return Reservation Time in Local Time
     */
	public LocalTime getResvTime() {
		return resvTime;
	}

    /**
     * Accessor for Reservation Session
     *
     * @return Enum type of ReservationSession
     */
	public ReservationSession getResvSession() {
		return resvSession;
	}

    /**
     * Accessor for number of pax
     *
     * @return Number of pax in integer
     */
	public int getNumPax() {
		return numPax;
	}

    /**
     * Accessor for Customer Name
     *
     * @return Customer Name in String
     */
	public String getCustomerName() {
		return customerName;
	}

    /**
     * Accessor for Customer Telephone Number
     *
     * @return Customer Telephone Number in String
     */
	public String getCustomerContact() {
		return customerContact;
	}

    /**
     * Accessor for Table Number
     *
     * @return Table number in integer
     */
	public int getTableID() {
		return tableID;
	}
}
