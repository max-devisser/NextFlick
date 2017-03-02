package src;

public class Filter {
	private String filterType;
	private String mapType;
	private String query;

	public Filter(String filterType, String query) {
		if (filterType.equals("getParental Rating")) {
			filterType = "getParentalRating";
		}
		if (filterType.equals("getYear")) {
			filterType = "getDate";
		}
		if (filterType.equals("getLength")) {
			filterType = "getRuntime";
		}
		if (filterType.equals("getRating")) {
			filterType = "getCriticalRating";
		}
		this.filterType = filterType;
		this.mapType = this.filterType + "Map";
		this.query = query;
	}

	public String getFilterType() {
		return filterType;
	}

	public String getMapType() {
		return mapType;
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
		return (f.getFilterType().equals(this.filterType) && f.getQuery().equals(this.query));
	}
}