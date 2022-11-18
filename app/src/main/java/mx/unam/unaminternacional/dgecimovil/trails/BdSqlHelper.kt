package mx.unam.unaminternacional.dgecimovil.trails

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.unam.unaminternacional.dgecimovil.models.DbConfig

class BdSqlHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "dgecimovil.db"
        private const val TABLE_CONFIG = "config"
        private const val CID = "id_config"
        private const val CKEY = "key"
        private const val CVALUE = "value"
        private const val CSTATUS = "status"
    }
    override fun onCreate(database: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE $TABLE_CONFIG ( $CID INTEGER PRIMARY KEY AUTOINCREMENT, $CKEY TEXT NOT NULL UNIQUE, $CVALUE TEXT, $CSTATUS INTEGER DEFAULT 1 NOT NULL )"
        database?.execSQL(sqlCreate)
    }
    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS ${TABLE_CONFIG}"
        database?.execSQL(sqlUpdate)
        onCreate(database)
    }
    fun savConfig(data : DbConfig):Int{
        var resultado = -1
        val buscando = getConfig(data.key,true)
        if(buscando.id_config != 0){
            resultado = updateConfig(data)
        }else{
            resultado = insertConfig(data).toInt()
        }
        return resultado
    }
    fun insertConfig(data : DbConfig): Long{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(CKEY, data.key)
            put(CVALUE, data.value)
            put(CSTATUS, 1)
        }
        val resultado = db.insert(TABLE_CONFIG, null, contenido)
        db.close()
        return resultado
    }
    fun updateConfig(data : DbConfig): Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(CVALUE, data.value)
            put(CSTATUS, 1)
        }
        val resultado = db.update(TABLE_CONFIG,contenido,"$CKEY = ?",arrayOf(data.key))
        db.close()
        return resultado
    }
    fun delConfig(key:String):Int{
        val db = writableDatabase
        val contenido = ContentValues().apply {
            put(CSTATUS, 0)
        }
        val resultado = db.update(TABLE_CONFIG,contenido,"$CKEY = ?",arrayOf(key))
        db.close()
        return resultado
    }
    fun getConfig(key:String,showAll:Boolean=false):DbConfig{
        var resultado = DbConfig(key=key)
        var query = "SELECT * FROM $TABLE_CONFIG WHERE $CKEY = ?"
        if(!showAll){ query += " and $CSTATUS = 1" }
        val db = readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(query, arrayOf(key))
        } catch (e : Exception){
            e.printStackTrace()
            return resultado
        }
        var rid_config:Int
        var rkey:String
        var rvalue:String
        var rstatus:Int
        with(cursor){
            while (moveToNext()){
                //REVISAR
                rid_config = getInt(getColumnIndexOrThrow(CID))
                rkey = getString(getColumnIndexOrThrow(CKEY))
                rvalue = getString(getColumnIndexOrThrow(CVALUE))
                rstatus = getInt(getColumnIndexOrThrow(CSTATUS))
                resultado = DbConfig(rid_config,rkey,rvalue,rstatus)
            }
        }
        cursor.close()
        return resultado
    }
    fun getAllConfig(): ArrayList<DbConfig> {
        var resultado = arrayListOf<DbConfig>()
        val query = "SELECT * FROM $TABLE_CONFIG WHERE $CSTATUS = 1"
        val db = readableDatabase
        val cursor : Cursor?
        try {
            cursor = db.rawQuery(query,null)
        } catch (e : Exception){
            e.printStackTrace()
            return resultado
        }
        var rid_config:Int
        var rkey:String
        var rvalue:String
        var rstatus:Int
        with(cursor){
            while (moveToNext()){
                //REVISAR
                rid_config = getInt(getColumnIndexOrThrow(CID))
                rkey = getString(getColumnIndexOrThrow(CKEY))
                rvalue = getString(getColumnIndexOrThrow(CVALUE))
                rstatus = getInt(getColumnIndexOrThrow(CSTATUS))
                val con = DbConfig(rid_config,rkey,rvalue,rstatus)
                resultado.add(con)
            }
        }
        cursor.close()
        return resultado
    }
}
