package de.MissingNameException;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
	// ##########################
	// Created by missingnameexception!
	// ##########################
	static Socket s;
	static String nl = "<>";
	
	public static void main(String[] args) throws IOException {
		String ip;
		Scanner in = new Scanner(System.in);
		System.out.println("LAST IP [84.170.220.42]");
		System.out.print("IP >> ");
		ip = in.nextLine();
		s = new Socket(ip, 8105);
//		s = new Socket("localhost", 8105);
		@SuppressWarnings("resource")
//		x.start
		
		
		String cmd;
		String[] serverMsg;
		while(true) {
			try {
				serverMsg = getServerMsg();
				if(serverMsg.length > 1) {
					for (int i = 0; i < serverMsg.length; i++) {
						System.out.println(serverMsg[i]);
					}
				} else {
					System.out.println(serverMsg[0]);
				}
				System.out.print(" >> ");
				cmd = in.nextLine();
				printS(cmd);
				if(cmd.equalsIgnoreCase("shutdown") || cmd.equalsIgnoreCase("sd") || cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("stop")) {
					System.out.println("Disconnected!");
					System.exit(0);
				}
			} catch (SocketException e) {
				System.out.println("You lost the connection to the server, start the system again!");
				System.exit(0);
			}
		}
	}
	
	private static String[] getServerMsg() throws IOException {
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferedReader bf = new BufferedReader(in);
		 
		String str = bf.readLine();
		
		if(str.contains(nl)) {
//			System.out.println("TRUE");
			String[] split = str.split(nl);
			return split;
		} else {
//			System.out.println("FALSE");
			String[] temp = {str};
			return temp;
		}		
	}
	
	public static void printS(String msg) throws IOException {
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		pr.println(msg);
		pr.flush();
	}
}
