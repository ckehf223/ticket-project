package view;

import java.io.IOException;
import java.util.Scanner;

import controller.ServerRegisterManager;

public class ServiceServerMain {
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerRegisterManager.connectServer();
	}// end of main
}
	

