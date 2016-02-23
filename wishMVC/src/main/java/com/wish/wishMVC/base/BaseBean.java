package com.wish.wishMVC.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.wish.wishMVC.utils.JsonUtil;
import com.wish.wishMVC.utils.XmlUtil;


/**
 * @Description: TODO
 * @author ttx
 * @since  2015年12月23日 上午9:59:43
 */
public abstract class BaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String toJson(){
		return JsonUtil.toJson(this);
	}
	
	public String toXml(){
		return XmlUtil.toXml(this);
	}
}
