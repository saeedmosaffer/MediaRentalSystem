package application;

import java.util.ArrayList;

public interface MediaRentalInt {

	public void addCustomer(String ID, String Name, String Address, String Plan, String MobileNumber);

	public void addMovie(String code, String title, int copiesAvailable, String rating);

	public void addGame(String code, String title, int copiesAvailable, double weight);

	public void addAlbum(String code, String title, int copiesAvailable, String artist, String songs);

	public void setLimitedPlanLimit(int value);

	public String getAllCustomersInfo();

	public String getAllMediaInfo();

	public boolean addToCart(String customerID, String mediaCode);

	public boolean removeFromCart(String customerID, String mediaCode);

	public String processRequests();

	public boolean returnMedia(String customerID, String mediaCode);

	public ArrayList<String> searchMedia(String code, String title, String rate, String artist, String songs, double weight);

}
