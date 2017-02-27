package src;

public class Filter {
	private String type;
	private String query;

	public Filter(String type, String query) {
		if (type.equals("getParental Rating")) {
			type = "getParentalRating";
		}
		if (type.equals("getYear")) {
			type = "getDate";
		}
		if (type.equals("getLength")) {
			type = "getRuntime";
		}
		if (type.equals("getRating")) {
			type = "getCriticalRating";
		}
		this.type = type;
		this.query = query;
	}

	public String getType() {
		return type;
	}

	public String getQuery() {
		return query;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Filter)) {
			return false;
		}

		Filter f = (Filter) o;
		return (f.getType().equals(this.type) && f.getQuery().equals(this.query));
	}
}