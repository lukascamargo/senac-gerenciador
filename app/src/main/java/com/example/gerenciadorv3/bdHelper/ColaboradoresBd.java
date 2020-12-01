package com.example.gerenciadorv3.bdHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gerenciadorv3.model.Colaboradores;

import java.util.ArrayList;

public class ColaboradoresBd extends SQLiteOpenHelper {

    private static final String DATABASE = "bancoColaboradores";
    private static final int VERSION = 1;

    public ColaboradoresBd (Context context){
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String colaborador = "CREATE TABLE colaboradores(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeColaborador TEXT NOT NULL, emailColaborador TEXT NOT NULL, telefoneColaborador INTEGER, idadeColaborador INTEGER NOT NULL, cargoColaborador STRING NOT NULL)";
        db.execSQL(colaborador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String colaborador = "DROP TABLE IF EXISTS colaboradores;";
        db.execSQL(colaborador);
    }

    public void salvarColaborador(Colaboradores colaborador){
        ContentValues values = new ContentValues();

        values.put("nomeColaborador", colaborador.getNomeColaborador());
        values.put("emailColaborador", colaborador.getEmailColaborador() );
        values.put("telefoneColaborador", colaborador.getTelefoneColaborador() );
        values.put("idadeColaborador", colaborador.getIdadeColaborador());
        values.put("cargoColaborador", colaborador.getCargoColaborador());

        getWritableDatabase().insert("colaboradores", null, values);

    }

    public void alterarColaborador (Colaboradores colaborador){
        ContentValues values = new ContentValues();

        values.put("nomeColaborador", colaborador.getNomeColaborador());
        values.put("emailColaborador", colaborador.getEmailColaborador() );
        values.put("telefoneColaborador", colaborador.getTelefoneColaborador() );
        values.put("idadeColaborador", colaborador.getIdadeColaborador());
        values.put("cargoColaborador", colaborador.getCargoColaborador());

        String [] args = {colaborador.getId().toString()};
        getWritableDatabase().update("colaboradores", values,"id=?",args);
    }

    public void deletarColaborador (Colaboradores colaborador){
        String [] args = {colaborador.getId().toString()};
        getWritableDatabase().delete("colaboradores","id=?",args);
    }

    public ArrayList<Colaboradores> getLista() {
        String [] columns = {"id","nomeColaborador","emailColaborador","telefoneColaborador","idadeColaborador","cargoColaborador"};
        Cursor cursor = getWritableDatabase().query("colaboradores",columns,null,null,null,null,null,null);
        ArrayList<Colaboradores> colaboradores = new ArrayList<Colaboradores>();

        while (cursor.moveToNext()){
            Colaboradores colaborador = new Colaboradores();
            colaborador.setId(cursor.getLong(0));
            colaborador.setNomeColaborador(cursor.getString(1));
            colaborador.setEmailColaborador(cursor.getString(2));
            colaborador.setTelefoneColaborador(cursor.getInt(3));
            colaborador.setIdadeColaborador(cursor.getInt(4));
            colaborador.setCargoColaborador(cursor.getString(5));

            colaboradores.add(colaborador);

        }
        return colaboradores;
    }


}
