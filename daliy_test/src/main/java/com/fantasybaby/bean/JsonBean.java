package com.fantasybaby.bean;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * test jsob object
 * @author FantasyBaby
 *
 */
public class JsonBean {
	private String name;
	private Integer inteNum;
	private List<String> list;
	private boolean isTrue;
	public String getName() {
		return name;
	}
	
	public Integer getInteNum() {
		return inteNum;
	}

	public void setInteNum(Integer inteNum) {
		this.inteNum = inteNum;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public static void main(String[] args) throws JSONException {
		JsonBean json = new JsonBean();
		json.setName("名称");
		List<String> list = new ArrayList<String>();
		list.add("1231");
		list.add("123221");
		json.setTrue(true);
		JSONArray ja = new JSONArray(list);
//		System.out.println(ja.toString());
		json.setList(list);
		JSONObject jo = new JSONObject(json);
		jo.put("list1", list);
		String string = jo.toString();
		System.out.println(jo.toString());
		JSONObject jsonobject2 = new JSONObject(string);
		System.out.println(jsonobject2.get("name"));
		JSONArray jsonArray = jsonobject2.getJSONArray("list1");
		System.out.println(jsonArray.get(1));
		try {
			System.out.println(jsonobject2.getString("g"));
		} catch (Exception e) {
			System.out.println(e.getMessage().contains("not found"));
		}
		JSONArray jsonArray2 = jsonobject2.getJSONArray("list");
		System.out.println(jsonArray2.get(0));
		System.out.println(jsonobject2.getBoolean("true"));
	}
}
