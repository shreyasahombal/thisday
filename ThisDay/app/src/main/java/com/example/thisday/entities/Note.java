package com.example.thisday.entities;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.io.Serializable;

@Entity(tableName = "notes")

public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "month_date")
    private String monthDate;

    @ColumnInfo(name = "day")
    private String day;

    @ColumnInfo(name = "content")
    private String noteContent;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @NonNull
    @Override
    public String toString() {
        return monthDate + " : " + day;
    }
}
