package net.onebean.core.extend.codebuilder;


/**
 * 根据类名生成相关代码的通用代码接口。
 *
 */
public abstract class UniversalCodeBuilder {
	protected static final String NEW_LINE_BREAK = "\r\n";
	
	public abstract <T> String buildByClass(Class<T> clazz);
	
}
