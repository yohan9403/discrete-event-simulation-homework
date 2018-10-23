public class Client {
	int id;
	int ticket_count;
	int denied;
	double current_time;
	double next_event_time;
	Ticket[] tickets_holding;
	Ticket ticket;
	
	public Client(int idx, double t) {
		ticket_count = 0;
		denied = 0;
		id = idx;
		current_time = t;
		tickets_holding = new Ticket[10];
		for (int i = 0; i < 10; i++) {
			tickets_holding[i] = null;
		}
		
		double y;
		int serv;
		System.out.println("Client ID: " + id);

		// 11520 = 8 days
		while (current_time < 480) {
			System.out.println("Current time is: " + current_time);

			serv = Simulator.r.nextInt(10);
			y = Simulator.r.nextDouble();	// uniform random between [0, 1)			
			next_event_time = (Math.log((1-y))/-0.25); 	// calculate x using exponential function given to us

			System.out.println("Next Event in: " + next_event_time + "\tfor service: " + serv);
			
			Ticket ticket = new Ticket(current_time + next_event_time);
			
			// client has no tickets yet
			if (tickets_holding[serv] == null) {
				tickets_holding[serv] = ticket;
				ticket_count++;
				Simulator.total_tickets_granted++;
				System.out.println("Ticket granted for service: " + serv);
				System.out.println("Your ticket expires at: " + ticket.expiration + " min");
			}
			// if tickets has already been granted for a client, check whether it has expire and client needs another.
			else {
				// if new ticket issued and old ticket has been expired
				if (ticket.time_issued > tickets_holding[serv].expiration) {
					System.out.println("Ticket for service: " + serv + " has expired.");
					tickets_holding[serv] = ticket;
					ticket_count++;
					Simulator.total_tickets_granted++;
					System.out.println("New ticket granted for service: " + serv);
					System.out.println("Your ticket expires at: " + ticket.expiration + " min");
				
				} else {
					// calculate how many more minutes ticket is still valid for the service.
					System.out.println("Ticket is not granted, your ticket for service: " + serv + " is valid for: " + (tickets_holding[serv].expiration - ticket.time_issued) + " more min.");
					denied++;
				}	
	
			}
			// go to next event
			current_time += next_event_time;

		}
	Simulator.total_denied_requests += denied;
	System.out.println("Tickets granted: " + ticket_count + "\tDenied Requests: " + denied + "\n");
	}		
}
