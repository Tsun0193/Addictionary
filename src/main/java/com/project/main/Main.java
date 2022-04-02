package com.project.main;

import com.project.dodung.DictionaryManagement;
import com.project.dodung.DoDungMain;
import com.project.UI.UI_Main;

public abstract class Main {

    abstract public void run();

    public static void main(String[] args){

        UI_Main ui_main = new UI_Main();

        ui_main.run();

    }

}
