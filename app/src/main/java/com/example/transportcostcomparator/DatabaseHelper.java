package com.example.transportcostcomparator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE = "calculations";
    public DatabaseHelper(Context context) { super(context, "transport_costs.db", null, 1); }
    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE calculations (_id INTEGER PRIMARY KEY AUTOINCREMENT, mode TEXT, type TEXT, distance REAL, cost_per_km REAL, days INTEGER, daily_cost REAL, monthly_cost REAL, monthly_distance REAL)");
    }
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { db.execSQL("DROP TABLE IF EXISTS " + TABLE); onCreate(db); }
    public long saveTransport(Transport t) {
        ContentValues v = new ContentValues();
        v.put("mode", t.getModeOfTransport()); v.put("type", t.getTransportType()); v.put("distance", t.getDistancePerDay()); v.put("cost_per_km", t.getCostPerKilometre()); v.put("days", t.getTravelDays()); v.put("daily_cost", t.getDailyCost()); v.put("monthly_cost", t.getMonthlyCost()); v.put("monthly_distance", t.getMonthlyDistance());
        return getWritableDatabase().insert(TABLE, null, v);
    }
    public Transport getTransport(long id) {
        try (Cursor c = getReadableDatabase().query(TABLE, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null)) {
            if (!c.moveToFirst()) return null;
            Transport t = new Transport();
            t.setId(c.getLong(c.getColumnIndexOrThrow("_id"))); t.setModeOfTransport(c.getString(c.getColumnIndexOrThrow("mode"))); t.setTransportType(c.getString(c.getColumnIndexOrThrow("type")));
            t.setDistancePerDay(c.getDouble(c.getColumnIndexOrThrow("distance"))); t.setCostPerKilometre(c.getDouble(c.getColumnIndexOrThrow("cost_per_km"))); t.setTravelDays(c.getInt(c.getColumnIndexOrThrow("days")));
            t.setDailyCost(c.getDouble(c.getColumnIndexOrThrow("daily_cost"))); t.setMonthlyCost(c.getDouble(c.getColumnIndexOrThrow("monthly_cost"))); t.setMonthlyDistance(c.getDouble(c.getColumnIndexOrThrow("monthly_distance")));
            return t;
        }
    }
}
