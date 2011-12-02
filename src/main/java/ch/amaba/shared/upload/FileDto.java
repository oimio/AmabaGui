package ch.amaba.shared.upload;

import java.io.Serializable;
import java.util.Date;

public final class FileDto implements Serializable {

	private String filename;
	private Date dateUploaded;

	public FileDto() {
	}

	public Date getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(final Date dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(final String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return filename + " - " + dateUploaded;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FileDto other = (FileDto) obj;
		if ((filename == null) ? (other.filename != null) : !filename.equals(other.filename)) {
			return false;
		}
		if ((dateUploaded != other.dateUploaded) && ((dateUploaded == null) || !dateUploaded.equals(other.dateUploaded))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 67 * hash + (filename != null ? filename.hashCode() : 0);
		hash = 67 * hash + (dateUploaded != null ? dateUploaded.hashCode() : 0);
		return hash;
	}
}
