package net.onebean.core.model;

import java.io.Serializable;

import net.onebean.core.extend.OrderBy;


@OrderBy("id desc")
public class BaseIncrementIdModelMongo implements Serializable{
	private static final long serialVersionUID = 3120480270658878412L;
	private String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BaseIncrementIdModelMongo other = (BaseIncrementIdModelMongo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
