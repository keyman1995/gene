package net.onebean.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassUtils {
	public static String getLowerFirstLetterSimpleClassName(String className) {
		if (StringUtils.isEmpty(className))
			return "";
		String[] parts = className.split("\\.");
		String result = parts[parts.length - 1];
		return result.substring(0, 1).toLowerCase()
				+ (result.length() > 1 ? result.substring(1) : "");
	}

	/**
	 * 1.获取属性名称的简单名，如果属性名称是entity.property，那么将返回property 2.如何输入的名称有 [ 标识
	 * 说明是查询字段，原样返回即可 ，不做任何处理
	 * 
	 * @param fullName
	 *            属性名称
	 * @return 处理后的属性名称
	 */
	public static String getSimplePropertyName(String fullName) {

		if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf("[") != -1) {
			// 如果字段含有 [ 说明是查询字段，直接返回
			return fullName;
		}

		if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf(".") != -1) {// 检查要获取的属性名是否含有.
			// 检查.是否是最后一个字符
			if (fullName.lastIndexOf(".") != fullName.length()) {
				fullName = fullName.substring(fullName.lastIndexOf(".") + 1);
			}
		}

		return fullName;
	}

	/**
	 * 获取 get 方法对应的属性名
	 * 
	 * @param readMethod
	 * @return
	 */
	public static String getPropertyName(Method readMethod) {
		String methodName = readMethod.getName();
		int getPosition = methodName.indexOf("get");
		if (getPosition == -1) {
			throw new RuntimeException(methodName + " 不是 以get开关的方法");
		}
		return getLowerFirstLetterSimpleClassName(methodName
				.substring(getPosition + 3));
	}

	/**
	 * 根据字符串转化成ID 1.如果属性名称是entity.property，那么将返回property 2.如何输入的名称有 [ 标识 说明是查询字段
	 * 如 ：conditions['date_date_gt'].value 用 split("'") 截断 返回第二个
	 * 
	 * @param fullName
	 *            属性名称
	 * @return 处理后的属性名称
	 */
	public static String changeToId(String fullName) {
		if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf("[") != -1) {
			// 如果字段含有 [ 说明是查询字段，直接返回
			String[] ids = fullName.split("'");
			if (ids.length == 3)
				return ids[1];
			return fullName;
		}

		if (!StringUtils.isTrimEmpty(fullName) && fullName.indexOf(".") != -1) {// 检查要获取的属性名是否含有.
			// 检查.是否是最后一个字符
			if (fullName.lastIndexOf(".") != fullName.length()) {
				fullName = fullName.substring(fullName.lastIndexOf(".") + 1);
			}
		}

		return fullName;
	}

	/**
	 * 判断类似否指定名字的注解
	 * 
	 * @param clazz
	 * @param annotationClassName
	 * @return
	 */
	public static boolean hasAnnotation(Class<?> clazz,
			String annotationClassName) {

		Annotation[] annots = clazz.getAnnotations();
		for (Annotation anno : annots) {

			Class<?> annoclazz = anno.annotationType();
			String annName = annoclazz.getSimpleName();
			if (annName.equalsIgnoreCase(annotationClassName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取指定注解的注解值
	 * 
	 * @param clazz
	 * @param annotationClassName
	 * @param annoField
	 * @return
	 */
	public static Object getAnnotationValue(Class<?> clazz,
			String annotationClassName, String annoField) {
		Annotation[] annots = clazz.getAnnotations();
		for (Annotation anno : annots) {
			Class<?> annoclazz = anno.annotationType();
			String annName = annoclazz.getSimpleName();
			if (annName.equalsIgnoreCase(annotationClassName)) {

				try {
					Method m = anno.getClass().getDeclaredMethod(annoField,
							null);
					try {
						if (m != null) {
							return m.invoke(anno, null);
						}
						break;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return null;
	}

	/**
	 * 获取指定注解的注解值
	 * 
	 * @param clazz
	 * @param annotationClassName
	 * @param annoField
	 * @return
	 */
	public static Object getMethodAnnotationValue(Method method,
			String annotationClassName, String annoField) {

		Annotation[] annots = method.getAnnotations();
		for (Annotation anno : annots) {
			Class<?> annoclazz = anno.annotationType();
			String annName = annoclazz.getSimpleName();
			if (annName.equalsIgnoreCase(annotationClassName)) {

				try {
					Method m = anno.getClass().getDeclaredMethod(annoField, null);
					try {
						if (m != null) {
							return m.invoke(anno, null);
						}
						break;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}

				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return null;
	}

	public static Object getFieldAnnotationValue(Field method,
			String annotationClassName, String annoField) {

		Annotation[] annots = method.getAnnotations();
		for (Annotation anno : annots) {
			Class<?> annoclazz = anno.annotationType();
			String annName = annoclazz.getSimpleName();
			if (annName.equalsIgnoreCase(annotationClassName)) {

				try {
					Method m = anno.getClass().getDeclaredMethod(annoField,
							null);
					try {
						if (m != null) {
							return m.invoke(anno, null);
						}
						break;
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return null;
	}

	/**
	 * 将数据对象转换成map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> toMap(Object obj) {
		// 创建新map
		Map<String, Object> rs = new HashMap<String, Object>();
		return appendMap(rs, obj);
	}

	/**
	 * 将数据对象合并的map
	 * 
	 * @param rs
	 *            结果map
	 * @param obj
	 *            数据对象
	 * @return
	 */
	public static Map<String, Object> appendMap(Map<String, Object> rs,
			Object obj) {
		return appendMap(rs, obj, null);
	}

	/**
	 * 将数据对象合并的map
	 * 
	 * @param rs
	 *            结果map
	 * @param obj
	 *            数据对象
	 * @param defaultValue
	 *            如果对象值为Null，默认填充的值
	 * @return
	 */
	public static Map<String, Object> appendMap(Map<String, Object> rs,
			Object obj, Object defaultValue) {
		// 获取所有属性
		Field[] array = obj.getClass().getDeclaredFields();
		// 遍历取值
		for (Field f : array) {
			// 首先设置为属性可读
			f.setAccessible(true);
			try {
				Object value = f.get(obj);
				// 取值
				rs.put(f.getName(), null == value ? defaultValue : value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

}
