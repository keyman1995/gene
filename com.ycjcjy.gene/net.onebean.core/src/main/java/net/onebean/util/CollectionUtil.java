package net.onebean.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.txw2.IllegalAnnotationException;
/**
 * 集合工具类
 */
public class CollectionUtil {
	
	/**
	 * 判断集合是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Collection<?> obj) {
		return (obj == null || obj.size() == 0) ? true : false;
	}

	/**
	 * 判断集合是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> obj) {
		return (obj == null || obj.size() == 0) ? false : true;
	}
	
	/**
	 * 返回List<Long>类型集合
	 * @param list
	 * @param key
	 * @return
	 */
	public static final List<Long> getLongList(List<Map<String, Object>> list, String key) {
		List<Long> resultList = new ArrayList<Long>();
		for(Map<String, Object> map : list) {
			resultList.add(Long.parseLong(map.get(key).toString()));
		}
		return resultList;
	}
	
	/**
	 * 将字符串集合值转换成长整型类型集合，将其转换成ArrayList类型结果集
	 * @param collection 要转换的集合
	 * @return 结果
	 */
	public static <T extends Collection<Long>> T toLongCollection(Collection<String> collection){
		return toLongCollection(collection,ArrayList.class);
	}
	/**
	 * 将字符串数组值转换成长整型类型集合，将其转换成ArrayList类型结果集
	 * @param collection 要转换的集合
	 * @return 结果
	 */
	public static <T extends Collection<Long>> T toLongCollection(String[] collection){
		return toLongCollection(collection,ArrayList.class);
	}
	/**
	 * 将字符串数组转换成Long集合
	 * @param collection 字符串数组
	 * @param arrayType 要转换的结果集合类型
	 * @return 结果集合
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Collection<Long>> T toLongCollection(String[] collection,Class<?> arrayType){
		//检查参数
		if(collection == null){
			return null;
		}
		//如果不存在
		if(arrayType == null){
			arrayType = ArrayList.class;
		}
		//检查类型是否是collection
		if(!Collection.class.isAssignableFrom(arrayType)){
			throw new IllegalAnnotationException("arrayType 必须是Collection子类");
		}
		//检查是否是接口
		if(arrayType.isInterface()){
			throw new IllegalAnnotationException("arrayType 必须是可以实例化的子类");
		}
		try {
			//实例化结果类
			Collection<Long> rs = (Collection<Long>)arrayType.newInstance();
			for(int i = 0;i < collection.length;i ++){
				rs.add(Long.parseLong(collection[i]));
			}
			return (T) rs;
		}catch (Exception e) {
			throw new IllegalAnnotationException("arrayType 必须是Collection子类",e);
		}
	}
	
	/**
	 * 将字符串集合值转换成长整型类型集合
	 * @param collection 要转换的集合
	 * @param arrayType 返回的集合类型，如果为空则默认为ArrayList
	 * @return 结果
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Collection<Long>> T toLongCollection(Collection<String> collection,Class<?> arrayType){
		//检查参数
		if(collection == null){
			return null;
		}
		//如果不存在
		if(arrayType == null){
			arrayType = ArrayList.class;
		}
		//检查类型是否是collection
		if(!Collection.class.isAssignableFrom(arrayType)){
			throw new IllegalAnnotationException("arrayType 必须是Collection子类");
		}
		//检查是否是接口
		if(arrayType.isInterface()){
			throw new IllegalAnnotationException("arrayType 必须是可以实例化的子类");
		}
		try {
			//实例化结果类
			Collection<Long> rs = (Collection<Long>)arrayType.newInstance();
			Iterator<String> it = collection.iterator();
			while(it.hasNext()){
				//转换并插入到结果集中
				rs.add(Long.parseLong(it.next()));
			}
			return (T) rs;
		}catch (Exception e) {
			throw new IllegalAnnotationException("arrayType 必须是Collection子类",e);
		}
	}
}
