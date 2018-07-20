package com.leonlabs.search.view;

/**
 * 
 * @author Admin
 *
 */
public class SearchQuery {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((freeText == null) ? 0 : freeText.hashCode());
		result = prime * result + ((maxResult == null) ? 0 : maxResult.hashCode());
		result = prime * result + ((startIndex == null) ? 0 : startIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchQuery other = (SearchQuery) obj;
		if (freeText == null) {
			if (other.freeText != null)
				return false;
		} else if (!freeText.equals(other.freeText))
			return false;
		if (maxResult == null) {
			if (other.maxResult != null)
				return false;
		} else if (!maxResult.equals(other.maxResult))
			return false;
		if (startIndex == null) {
			if (other.startIndex != null)
				return false;
		} else if (!startIndex.equals(other.startIndex))
			return false;
		return true;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(Integer maxResult) {
		this.maxResult = maxResult;
	}

	private String freeText;
	
	private Integer startIndex;
	
	private Integer maxResult;
	
}
