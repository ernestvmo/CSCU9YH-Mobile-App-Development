package uk.ac.stir.cs.yhproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

const val database_name = "unitsDB"

class UnitsDatabase (context: Context) : SQLiteOpenHelper(context, database_name, null, 1)
{
    /** The name of the table where all the conversion rates are stored. */
    private val tableName = "UnitsConversion"
    /** The column name for the ID associated with all the rates. */
    private val column_ID = "id"
    /** The column name for the unit family that the rate belongs to. */
    private val unit_family_column = "family"
    /** The column name for the initial unit that we convert. */
    private val initial_unit_column = "initial_unit"
    /** The column name for the unit that the input will be converted to. */
    private val convert_to_column = "converted_unit"
    /** The column name for the ratio to multiply the input by. */
    private val ratio_column = "ratio"

    /**
     * This method will be called on the Instantiation of the Database.
     * It create a query to create a table using column names and their types.
     */
    override fun onCreate(db: SQLiteDatabase) {
        val dropQuery = "DROP TABLE IF EXISTS $tableName;"

        val createTable = "CREATE TABLE $tableName " +
                "($column_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$unit_family_column VARCHAR(256), " +
                "$initial_unit_column VARCHAR(256), " +
                "$convert_to_column VARCHAR(256), " +
                "$ratio_column REAL);"

//        db.execSQL(dropQuery)
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { /* NEVER USED */ }

    /**
     * This method calls all the other methods to populate the database based on their family.
     */
    fun populateDatabase()
    {
        val db = this.writableDatabase
        val values = ContentValues()

        populateWeight(values, db)
        populateLength(values, db)
        populateSpeed(values, db)
        populateVolume(values, db)
    }

    /**
     * This method populates all the Weight-related conversion rates.
     */
    private fun populateWeight(values: ContentValues, db: SQLiteDatabase) {
        // Weight
        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "gram")
        values.put(convert_to_column, "pound")
        values.put(ratio_column, 0.00220462)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "gram")
        values.put(convert_to_column, "stone")
        values.put(ratio_column, 0.000157473)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "gram")
        values.put(convert_to_column, "ounce")
        values.put(ratio_column, 0.035273952)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "gram")
        values.put(convert_to_column, "kilogram")
        values.put(ratio_column, 0.001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "pound")
        values.put(convert_to_column, "gram")
        values.put(ratio_column, 453.592)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "pound")
        values.put(convert_to_column, "kilogram")
        values.put(ratio_column, 0.453592)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "pound")
        values.put(convert_to_column, "stone")
        values.put(ratio_column, 0.0714286)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "pound")
        values.put(convert_to_column, "ounce")
        values.put(ratio_column, 16.0)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "stone")
        values.put(convert_to_column, "gram")
        values.put(ratio_column, 6350.29)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "stone")
        values.put(convert_to_column, "ounce")
        values.put(ratio_column, 224.0)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "stone")
        values.put(convert_to_column, "kilogram")
        values.put(ratio_column, 6.35029)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "stone")
        values.put(convert_to_column, "pound")
        values.put(ratio_column, 14)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "ounce")
        values.put(convert_to_column, "gram")
        values.put(ratio_column, 28.3495)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "ounce")
        values.put(convert_to_column, "pound")
        values.put(ratio_column, 0.0625)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "ounce")
        values.put(convert_to_column, "stone")
        values.put(ratio_column, 0.00446429)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "ounce")
        values.put(convert_to_column, "kilogram")
        values.put(ratio_column, 0.0283495)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "kilogram")
        values.put(convert_to_column, "gram")
        values.put(ratio_column, 1000)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "kilogram")
        values.put(convert_to_column, "pound")
        values.put(ratio_column, 2.20462)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "kilogram")
        values.put(convert_to_column, "ounce")
        values.put(ratio_column, 35.274)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "weight")
        values.put(initial_unit_column, "kilogram")
        values.put(convert_to_column, "stone")
        values.put(ratio_column, 0.157473)
        db.insert(tableName, null, values)
    }

    /**
     * This method populates all the Length-related conversion rates.
     */
    private fun populateLength(values: ContentValues, db: SQLiteDatabase) {
        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "meter")
        values.put(convert_to_column, "kilometer")
        values.put(ratio_column, 0.001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "meter")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 100)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "meter")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 39.3701)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "meter")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 3.28084)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "meter")
        values.put(convert_to_column, "mile")
        values.put(ratio_column, 0.000621371)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 1000)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 100000)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "millimeter")
        values.put(ratio_column, 1000000)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 39370.1)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 3280.84)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "kilometer")
        values.put(convert_to_column, "mile")
        values.put(ratio_column, 0.621371)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 0.01)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "kilometer")
        values.put(ratio_column, 0.00001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "millimeter")
        values.put(ratio_column, 10)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 0.393701)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 0.0328084)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "centimeter")
        values.put(convert_to_column, "mile")
        values.put(ratio_column, 0.00000062137)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 0.001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 0.1)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 0.000001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 0.0393701)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 0.00328084)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "millimeter")
        values.put(convert_to_column, "mile")
        values.put(ratio_column, 0.00000062137)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 0.0254)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 2.54)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "millimeter")
        values.put(ratio_column, 25.4)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "kilometer")
        values.put(ratio_column, 0.0000254)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 0.0833333)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "inch")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 0.0000157828)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 0.3048)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "kilometer")
        values.put(ratio_column, 0.0003048)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 30.48)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "millimeter")
        values.put(ratio_column, 304.8)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 12)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "foot")
        values.put(convert_to_column, "mile")
        values.put(ratio_column, 0.000189394)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "meter")
        values.put(ratio_column, 1609.34)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "kilometer")
        values.put(ratio_column, 1.60934)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "centimeter")
        values.put(ratio_column, 160934)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "millimeter")
        values.put(ratio_column, 1609344)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "inch")
        values.put(ratio_column, 63360)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "length")
        values.put(initial_unit_column, "mile")
        values.put(convert_to_column, "foot")
        values.put(ratio_column, 5280)
        db.insert(tableName, null, values)
    }

    /**
     * This method populates all the Speed-related conversion rates.
     */
    private fun populateSpeed(values: ContentValues, db: SQLiteDatabase) {
        values.put(unit_family_column, "speed")
        values.put(initial_unit_column, "mph")
        values.put(convert_to_column, "kph")
        values.put(ratio_column, 1.60934)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "speed")
        values.put(initial_unit_column, "kph")
        values.put(convert_to_column, "mph")
        values.put(ratio_column, 0.621371)
        db.insert(tableName, null, values)
    }

    /**
     * This method populates all the Volume-related conversion rates.
     */
    private fun populateVolume(values: ContentValues, db: SQLiteDatabase) {
        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 1000)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.219969)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 1.75975)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 3.51951)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 56.3121)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "liter")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 168.936)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 0.001)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.000219969)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 0.00175975)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 0.00351951)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 0.0563121)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "milliliter")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 0.168936)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 4.54609)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 4546.09)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 8)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 16)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 256)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "gallon")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 768)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 0.568261)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 568.261)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.125)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 2)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 32)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "pint")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 96)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 0.284131)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 284.131)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.0625)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 0.5)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 16)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "cup")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 48)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 0.00591939)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 5.91939)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.00130208)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 0.0104167)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 0.0208333)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "teaspoon")
        values.put(convert_to_column, "tablespoon")
        values.put(ratio_column, 0.333333)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "liter")
        values.put(ratio_column, 0.0177582)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "milliliter")
        values.put(ratio_column, 17.7582)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "gallon")
        values.put(ratio_column, 0.00390625)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "pint")
        values.put(ratio_column, 0.03125)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "cup")
        values.put(ratio_column, 0.0625)
        db.insert(tableName, null, values)

        values.put(unit_family_column, "volume")
        values.put(initial_unit_column, "tablespoon")
        values.put(convert_to_column, "teaspoon")
        values.put(ratio_column, 3)
        db.insert(tableName, null, values)
    }

    /**
     * This method returns the ratio that is found in the database using the query and the passed parameters.
     * The query will find the row(s) where the initial unit and convert to units are the same as the parameters.
     *
     * @param db The database to query.
     * @param initialUnit The value that the initial unit column must be.
     * @param convertToUnit The value that the convert to unit column must be.
     *
     * @return The retrieved ratio.
     */
    fun getRatio(db: SQLiteDatabase, initialUnit: String, convertToUnit: String): Double {
        var ratio_val: Double = 0.0
        // Select Query to retreive the ratio.
        var query = "SELECT $ratio_column " +
                    "FROM $tableName " +
                    "WHERE $initial_unit_column  == '$initialUnit' " +
                        "AND $convert_to_column == '$convertToUnit';"
        // Execute the query
        val result = db.rawQuery(query, null)

        if (result.moveToFirst())
        {
            // Assign the first value return from the query
            ratio_val = result.getDouble(result.getColumnIndex(ratio_column))
        }

        return ratio_val
    }
}