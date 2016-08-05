package com.codepath.apps.Tweetster.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 * 
 */
@Table(name = "items")
public class DB extends Model {
	// Define table fields
	@Column(name = "name")
	private String name;

	public DB() {
		super();
	}

	// Parse model from JSON
	public DB(JSONObject object){
		super();

		try {
			this.name = object.getString("title");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// Getters
	public String getName() {
		return name;
	}

	// Record Finders
	public static DB byId(long id) {
		return new Select().from(DB.class).where("id = ?", id).executeSingle();
	}

	public static List<DB> recentItems() {
		return new Select().from(DB.class).orderBy("id DESC").limit("300").execute();
	}
}
