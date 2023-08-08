package application;

public class Movie extends Media implements Comparable, Displayable {

	private String rate;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String code, String title, int number_of_copies_available, String rate) {
		super(code, title, number_of_copies_available);
		this.rate = rate;
		// TODO Auto-generated constructor stub
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Movie [code=" + code + ", title=" + title + ", number_of_copies_available=" + number_of_copies_available
				+ ", rate=" + rate + "]";
	}

	@Override
	public void DisplayAllDetails() {
		// TODO Auto-generated method stub
		System.out.println(super.toString() + toString());
	}

	@Override
	public int compare(Object media) {
		// TODO Auto-generated method stub
		Movie movie = (Movie) media;
		return this.getTitle().compareTo(movie.getTitle());
	}

}
