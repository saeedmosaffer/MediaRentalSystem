package application;

public abstract class Media {

	protected String code;
	protected String title;
	protected int number_of_copies_available;

	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Media(String code, String title, int number_of_copies_available) {
		super();
		this.code = code;
		this.title = title;
		this.number_of_copies_available = number_of_copies_available;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber_of_copies_available() {
		return number_of_copies_available;
	}

	public void setNumber_of_copies_available(int number_of_copies_available) {
		this.number_of_copies_available = number_of_copies_available;
	}

	@Override
	public String toString() {
		return "Media [code=" + code + ", title=" + title + ", number_of_copies_available=" + number_of_copies_available
				+ "]";
	}

	public void calculateNumberOfCopies(boolean copyAdd) {

		if (copyAdd) {
			this.number_of_copies_available++;
		} else {
			this.number_of_copies_available--;
		}
	}

}
