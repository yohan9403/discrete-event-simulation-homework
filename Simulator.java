import java.util.Random;

public class Simulator {
	public static int total_tickets_granted;
	public static int total_denied_requests;
	public static int client_number;
	public static Random r;

	public static void main(String args[]) {
		total_tickets_granted = 0;
		total_denied_requests = 0;
		client_number = 100;
		r = new Random();
		for (int i = 0; i < client_number; i++) {
			new Client(i, 0);
		}

		System.out.println("Total Tickets Granted: " + total_tickets_granted + "\tTotal Requests Denied: " + total_denied_requests);
		System.out.println("Average tickets granted per client: " + (total_tickets_granted/client_number) +
					"\nAverage tickets request denied per client: " + (total_denied_requests/client_number));
	}
}
