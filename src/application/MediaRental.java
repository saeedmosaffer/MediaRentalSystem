package application;

import java.util.ArrayList;

public class MediaRental implements MediaRentalInt {

	private int MaxPlan;
	protected ArrayList<Customer> customer;
	protected ArrayList<Media> media;

	public MediaRental() {
		super();
		this.MaxPlan = 2;
		this.customer = new ArrayList<Customer>();
		this.media = new ArrayList<Media>();
	}
	

	public MediaRental(ArrayList<Customer> customer, ArrayList<Media> media) {
		super();
		this.customer = customer;
		this.media = media;
	}


	public int getMaxPlan() {
		return MaxPlan;
	}

	public void setMaxPlan(int maxPlan) {
		MaxPlan = maxPlan;
	}

	public ArrayList<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(ArrayList<Customer> customer) {
		this.customer = customer;
	}

	public ArrayList<Media> getMedia() {
		return media;
	}

	public void setMedia(ArrayList<Media> media) {
		this.media = media;
	}

	@Override
	public void addCustomer(String ID, String Name, String Address, String Plan, String MobileNumber) {
		// TODO Auto-generated method stub
		Customer C = new Customer(ID, Name, Address, Plan, MobileNumber);
		customer.add(C);
	}

	@Override
	public void addMovie(String code, String title, int number_of_copies_available, String rate) {
		// TODO Auto-generated method stub
		Movie M = new Movie(code, title, number_of_copies_available, rate);
		media.add(M);
	}

	@Override
	public void addGame(String code, String title, int number_of_copies_available, double weight) {
		// TODO Auto-generated method stub
		Game G = new Game(code, title, number_of_copies_available, weight);
		media.add(G);
	}

	@Override
	public void addAlbum(String code, String title, int number_of_copies_available, String artist, String songs) {
		// TODO Auto-generated method stub
		Album A = new Album(code, title, number_of_copies_available, artist, songs);
		media.add(A);
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		// TODO Auto-generated method stub
		MaxPlan = value;
	}

	@Override
	public String getAllCustomersInfo() {
		// TODO Auto-generated method stub
		String customerInfo = "";
		for (int i = 0; i < customer.size(); i++) {
			customerInfo += customer.get(i).toString() + "\n";
		}

		return customerInfo;
	}

	@Override
	public String getAllMediaInfo() {
		// TODO Auto-generated method stub
//		for (int i = 0; i < media.size(); i++) {
//			if (media.get(i) instanceof Movie) {
//				((Movie) media.get(i)).DisplayAllDetails();
//			}
//
//			if (media.get(i) instanceof Album) {
//				((Album) media.get(i)).DisplayAllDetails();
//			}
//
//			if (media.get(i) instanceof Game) {
//				((Game) media.get(i)).DisplayAllDetails();
//			}
//		}
		
		String mediaInfo = "";
		for (int i = 0; i < media.size(); i++) {
			mediaInfo += media.get(i).toString() + "\n";
		}

		return mediaInfo;
	}

	@Override
	public boolean addToCart(String customerID, String mediaCode) {
		// TODO Auto-generated method stub
		int flag = 0;
		int customerNo = -1;

		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getID() == customerID) {
				flag = 1;
				customerNo = i;
			}

		}

		if (flag == 0) {
			return false;
		}

		for (int i = 0; i < customer.get(customerNo).intrested_in_received.size(); i++) {
			if (customer.get(customerNo).intrested_in_received.get(i) == mediaCode) {
				return false;
			}
		}

		customer.get(customerNo).intrested_in_received.add(mediaCode);
		return true;
	}

	@Override
	public boolean removeFromCart(String customerID, String mediaCode) {
		// TODO Auto-generated method stub
		for (int i = 0; i < customer.size(); i++) {

			if (customer.get(i).getID() == customerID) {

				customer.get(i).intrested_in_received.remove(mediaCode);
				return true;
			}
		}
		return false;
	}

	@Override
	public String processRequests() {
		// TODO Auto-generated method stub
		String process = "";

		for (int i = 0; i < customer.size(); i++) {

			int size = customer.get(i).intrested_in_received.size();
			int data = 0;

			if (customer.get(i).getPlan() == "LIMITED") {

				for (int j = 0; j < size; j++) {

					for (int k = 0; k < media.size(); k++) {

						Media m = media.get(k);

						String mediaCode = m.getCode();

						System.out.println(customer.get(i).intrested_in_received.size());
						if (mediaCode.equals(customer.get(i).intrested_in_received.get(data))) {

							if (m.getNumber_of_copies_available() > 0) {

								if (customer.get(i).rented.size() < MaxPlan) {

									customer.get(i).intrested_in_received.remove(data);

									customer.get(i).rented.add(mediaCode);
									customer.get(i).rented.add(mediaCode);

									m.calculateNumberOfCopies(false);

									process += "Sending " + mediaCode + " to " + customer.get(i).getID() + "\n";

									break;

								}
							} else {
								data++;
							}

						}
					}

				}

			} else if (customer.get(i).getPlan() == "UNLIMITED") {

				for (int j = 0; j < size; j++) {

					for (int k = 0; k < media.size(); k++) {

						Media m = media.get(k);

						String mediaCode = m.getCode();

						if (mediaCode.equals(customer.get(i).intrested_in_received.get(data))) {
							if (m.getNumber_of_copies_available() > 0) {

								customer.get(i).intrested_in_received.remove(data);

								customer.get(i).rented.add(mediaCode);

								m.calculateNumberOfCopies(false);

								process += "Sending " + mediaCode + " to " + customer.get(i).getID() + "\n";

								break;

							} else {
								data++;
							}
						}

					}

				}

			}

		}

		return process;
	}

	@Override
	public boolean returnMedia(String customerID, String mediaCode) {
		// TODO Auto-generated method stub
		// check if there is a customer has media title
		int flag = 0;
		int customerNo = -1;

		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getID().equals(customerID)) {
				flag = 1;
				customerNo = i;
			}
		}

		if (flag == 0) {
			return false;
		}

		for (int i = 0; i < customer.get(customerNo).rented.size(); i++) {
			if (customer.get(customerNo).rented.get(i).equals(mediaCode)) {
				customer.get(customerNo).rented.remove(mediaCode);

				for (int j = 0; j < media.size(); j++) {
					if (media.get(j).getCode().equals(mediaCode)) {
						media.get(j).calculateNumberOfCopies(true);
						System.out.println("Return completed successfully");
					}
				}

				return true;

			}
		}

		return false;
	}

	@Override
	public ArrayList<String> searchMedia(String code, String title, String rate, String artist, String songs,
			double weight) {
		// TODO Auto-generated method stub
		ArrayList<java.lang.String> addMedia = new ArrayList<java.lang.String>();

		int Bflag = 0, Tflag = 0, Rflag = 0, Aflag = 0, Sflag = 0, Wflag = 0;

		for (Media mediaSearch : media) {

			if (code == null || mediaSearch.getCode().equals(code))
				Bflag = 1;

			if (title == null || mediaSearch.getTitle().equals(title))
				Tflag = 1;

			if (mediaSearch instanceof Movie) {

				Movie movie = (Movie) mediaSearch;

				if (rate == null || movie.getRate().equals(rate))
					Rflag = 1;

				if (artist == null)
					Aflag = 1;

				if (songs == null)
					Sflag = 1;

			} else if (mediaSearch instanceof Album) {

				Album album = (Album) mediaSearch;

				if (artist == null || album.getArtist().equals(artist))
					Aflag = 1;

				if (songs == null || album.getSongs().indexOf(songs) != -1)
					Sflag = 1;

				if (rate == null)
					Rflag = 1;

			}

			else if (mediaSearch instanceof Game) {

				Game game = (Game) mediaSearch;

				if (weight == 0 || game.getWeight() == weight)
					Wflag = 1;

			}

			if (Tflag != 0 && Rflag != 0 && Aflag != 0 && Sflag != 0) {
				addMedia.add(mediaSearch.getCode());
			}

		}

		return addMedia;

	}

	public int get_customer_count() {
		return customer.size();
	}

}