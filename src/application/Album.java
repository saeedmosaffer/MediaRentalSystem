package application;

public class Album extends Media implements Comparable, Displayable {

	private String artist;
	private String songs;

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(String code, String title, int number_of_copies_available, String artist, String songs) {
		super(code, title, number_of_copies_available);
		this.artist = artist;
		this.songs = songs;
		// TODO Auto-generated constructor stub
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return "Album [code=" + code + ", title=" + title + ", number_of_copies_available=" + number_of_copies_available
				+ ", artist=" + artist + ", songs=" + songs + "]";
	}

	@Override
	public void DisplayAllDetails() {
		// TODO Auto-generated method stub
		System.out.println(super.toString() + toString());

	}

	@Override
	public int compare(Object media) {
		// TODO Auto-generated method stub
		Album album = (Album) media;
		return this.getTitle().compareTo(album.getTitle());
	}

}