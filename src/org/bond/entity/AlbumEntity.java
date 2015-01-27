package org.bond.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "g_album")
public class AlbumEntity {
	private long id;

	@Id
	@javax.persistence.Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private long userID;

	@Basic
	@javax.persistence.Column(name = "userid")
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	private String albumName;

	@Basic
	@javax.persistence.Column(name = "a_name", nullable = false, insertable = true, updatable = true, length = 512)
	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	private String albumDesc;

	@Basic
	@javax.persistence.Column(name = "a_desc", nullable = false, insertable = true, updatable = true, length = 1024)
	public String getAlbumDesc() {
		return albumDesc;
	}

	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}
}
