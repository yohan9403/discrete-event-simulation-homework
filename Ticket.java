public class Ticket {
	double time_issued;
	double expiration;
	public Ticket(double t) {
		time_issued = t;
		expiration = t + 30;
	}
}
