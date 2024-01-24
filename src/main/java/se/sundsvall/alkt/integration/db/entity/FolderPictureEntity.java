package se.sundsvall.alkt.integration.db.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MappBilder", indexes = {
		@Index(name = "UC_MappBilder_FilNamn", columnList = "Filnamn", unique = true)
})
public class FolderPictureEntity {
	@Id
	@Column(name = "FileId", nullable = false)
	private UUID id;

	/*@Size(max = 255)
	@NotNull
	@Column(name = "Filnamn", nullable = false)
	private String filnamn;

	@Column(name = "Skapad")
	private Instant skapad;

	@Column(name = "Andrad")
	private Instant andrad;

	@Column(name = "Data")
	private byte[] data;

	@Column(name = "Locked")
	private Boolean locked;

	@Size(max = 8)
	@Column(name = "USRID", length = 8)
	private String usrid;

	@Size(max = 8)
	@Column(name = "Locked_By", length = 8)
	private String lockedBy;

	@Column(name = "Locked_Date")
	private Instant lockedDate;

	@Column(name = "FileSync")
	private Boolean fileSync;

	@Size(max = 200)
	@Column(name = "Notering", length = 200)
	private String notering;*/

}