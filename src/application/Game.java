package application;

public class Game extends Media implements Comparable, Displayable {

	private double weight;

	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Game(String code, String title, int number_of_copies_available, double weight) {
		super(code, title, number_of_copies_available);
		this.weight = weight;
		// TODO Auto-generated constructor stub
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Game [code=" + code + ", title=" + title + ", number_of_copies_available=" + number_of_copies_available
				+ ", weight=" + weight + "]";
	}

	@Override
	public void DisplayAllDetails() {
		// TODO Auto-generated method stub
		System.out.println(super.toString() + toString());

	}

	@Override
	public int compare(Object media) {
		// TODO Auto-generated method stub
		Game game = (Game) media;

		return this.getTitle().compareTo(game.getTitle());
	}

}
