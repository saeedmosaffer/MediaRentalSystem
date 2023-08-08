package application;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

//		File ResultFile = new File("Result.txt");
//		try (PrintWriter output = new PrintWriter(ResultFile);) {
//			if (ResultFile.createNewFile()) {
//
//				output.println(MR);
//
//				System.out.println("File created: " + ResultFile.getName());
				testAddingCustomers();
				testAddingMedia();
				testingAddingToCart();
				testingRemovingFromCart();
				testProcessingRequestsOne();
				testProcessingRequestsTwo();
				testReturnMedia();
				testSearchMedia();

//				output.close();
//			} else {
//				System.out.println("File already exists.");
//			}
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//			System.out.println("Result.txt (The system cannot find the file specified)");
//		}
	}

	static MediaRental MR = new MediaRental();

	public static void testAddingCustomers() {
		MR.addCustomer("9856421", "Saeed Mosaffer", "Ramallah", "UNLIMITED", "0569605357");
		MR.addCustomer("9856825", "Ahmad Mohammad", "Nablus", "LIMITED", "0598536512");
		System.out.println(MR.getAllCustomersInfo());

	}

	public static void testAddingMedia() {
		MR.addMovie("S73JDFC82R", "World.War.Z", 5, "AC");
		MR.addAlbum("SADF873KHD", "Kazab", 3, "BigSam", "Rap");
		MR.addGame("35HGBKJ348", "Monopoly", 2, 225.5);
		MR.getAllMediaInfo();

	}

	public static void testingAddingToCart() {
		System.out.println(MR.addToCart("9856421", "SADF873KHD"));
		System.out.println(MR.addToCart("9856825", "S73JDFC82R"));
		System.out.println(MR.addToCart("9856421", "JADBHF"));
	}

	public static void testingRemovingFromCart() {
		System.out.println(MR.removeFromCart("9856825", "35HGBKJ348"));
		System.out.println(MR.removeFromCart("99999", "CCCCC"));
	}

	public static void testProcessingRequestsOne() {
		System.out.println(MR.processRequests());
	}

	public static void testProcessingRequestsTwo() {
		System.out.println(MR.processRequests());
	}

	public static void testReturnMedia() {
		System.out.println(MR.returnMedia("9856421", "SADF873KHD"));
		System.out.println("&");
		System.out.println(MR.returnMedia("00000", "AAAAA"));
	}

	public static void testSearchMedia() {
		System.out.println(MR.searchMedia("SADF873KHD", "Kazab", null, "BigSam", "Rap", 0));
		System.out.println(MR.searchMedia("S73JDFC82R", "World.War.Z", "AC", null, null, 0));
		System.out.println(MR.searchMedia("35HGBKJ348", "Monopoly", null, null, null, 225.5));
	}

}
