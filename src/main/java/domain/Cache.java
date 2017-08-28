package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Cache extends DomainEntity {

	private String sessionId;
	private List<Integer> resultIds;
	private Integer cacheType;

	@NotNull
	public Integer getCacheType() {
		return cacheType;
	}

	@NotBlank
	@Column(unique = true)
	public String getSessionId() {
		return sessionId;
	}

	@ElementCollection(targetClass = Integer.class)
	public List<Integer> getResultIds() {
		return resultIds;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setResultIds(List<Integer> resultIds) {
		this.resultIds = resultIds;
	}

	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}
}
