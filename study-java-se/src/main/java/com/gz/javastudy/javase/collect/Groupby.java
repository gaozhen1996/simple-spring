package com.gz.javastudy.javase.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gaozhen
 * @title: Groupby
 * @projectName study-java
 * @description: TODO
 * @date 2021/3/30下午6:51
 */
public class Groupby {
	
    public static void main(String[] args) {
    	List<Security> securities = loadData();
    	/**
    	 * 根据type分组
    	 */
    	Map<Integer,List<Security>> type_securities = 
    			securities.stream().collect(Collectors.groupingBy(Security::getType));
    	for (Integer type : type_securities.keySet()) {
    		System.out.println("type:"+type);
			for (Security security : type_securities.get(type)) {
				System.out.println(security);
			}
		}
    	/**
    	 * 根据证券代码排序
    	 */
    	Map<Integer, Long> count = securities.
    			stream().collect(Collectors.groupingBy(Security::getType,Collectors.counting()));
    	System.out.println(count);
    }
	
	
	static class Security{
		String code;//证券代码
		String name;//证券名称
		int type;   //证券类别
		
		public Security(String code,String name,int type) {
			this.code = code;
			this.name = name;
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public int getType() {
			return type;
		}

		@Override
		public String toString() {
			return "Security [code=" + code + ", name=" + name + ", type=" + type + "]";
		}
	}

	/**
	 * 模拟数据
	 * @return
	 */
	public static List<Security> loadData(){
    	List<Security> securities = new ArrayList<Groupby.Security>();
    	securities.add(new Security("600000","浦发银行", 1));
    	securities.add(new Security("600519","贵州茅台", 1));
    	securities.add(new Security("019642","20国债12", 2));
    	securities.add(new Security("020411","21贴债14", 4));
    	securities.add(new Security("000001","华夏成长混合", 4));
    	securities.add(new Security("000009","易方达天天理财货币A", 2));
    	return securities;
	}
	
}
