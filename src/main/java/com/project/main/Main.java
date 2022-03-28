package com.project.main;

import com.project.dodung.DoDungMain;
import com.project.duydang.DuyDangMain;
import com.project.quangminh.QuagMinhMain;
import com.project.support.SupportMain;

public abstract class Main {

    abstract public void run();

    public static void main(String[] args){
        //System.out.println("alo");
        DoDungMain doDungMain = new DoDungMain();
        DuyDangMain duyDangMain = new DuyDangMain();
        QuagMinhMain quagMinhMain = new QuagMinhMain();
        SupportMain supportMain = new SupportMain();
        doDungMain.run();
        duyDangMain.run();
        quagMinhMain.run();
        supportMain.run();
    }

}
