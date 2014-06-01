package main;

import implementation.MainImpl;
import implementation.gui.GUIImpl;
import MainSys.GUI;
import MainSys.MainSys;




public class MainMain { 
	public static void main(String args[]){
		MainSys.Component main = (new MainImpl()).newComponent();
	}
}
