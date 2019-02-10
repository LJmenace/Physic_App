package org.pursuit.psychic_app_hw_jimenez_luis.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.pursuit.psychic_app_hw_jimenez_luis.model.Choice;

public class ChoiceDatabase  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CHOICE DATABASE";
    private static final String TABLE_NAME = "Choice";
    private static final int SCHEMA_VERSION = 1;
    private static ChoiceDatabase choiceDatabase;

    private ChoiceDatabase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    public static synchronized ChoiceDatabase getInstance(Context context) {
        if (choiceDatabase == null) {
            choiceDatabase = new ChoiceDatabase(context.getApplicationContext());
        }
        return choiceDatabase;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "correct INTEGER, theme TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addChoice(Choice choice) {
        int correct = choice.isCorrect() ? 1 : 0;
        getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME + "(correct, theme) VALUES('" +
                correct + "', '" +
                choice.getTheme() + "');");
    }
    public int getChoiceRatio() {
        double winRatio = 0;
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);
        if (cursor != null) {
            int total = cursor.getCount();
            cursor = getReadableDatabase().rawQuery(
                    "SELECT * FROM " + TABLE_NAME + " WHERE correct = '" + 1 + "';", null);
            double totalGuessed = cursor.getCount();
            winRatio = (totalGuessed / total) * 100;
            cursor.close();
        }
        return (int) winRatio;
    }

}
