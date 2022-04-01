package com.project.main;

import com.project.dodung.DictionaryManagement;
import com.project.dodung.DoDungMain;
import com.project.duydang.DuyDangMain;
import com.project.quangminh.QuagMinhMain;
import com.project.support.SupportMain;

public abstract class Main {

    abstract public void run();

    public static void main(String[] args){

        QuagMinhMain quagMinhMain = new QuagMinhMain();

        quagMinhMain.run();

    }

}
