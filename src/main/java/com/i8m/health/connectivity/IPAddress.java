package com.i8m.health.connectivity;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class IPAddress {
	public static void main1(String[] args) throws UnknownHostException, SocketException {
		System.out.println("Host:\t" + InetAddress.getLocalHost() + "\n");
		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

		while (en.hasMoreElements()) {
			NetworkInterface networkinterface = en.nextElement();
			List<InterfaceAddress> interfaces = networkinterface.getInterfaceAddresses();

			if (interfaces.size() > 0) {
				System.out.println(networkinterface.getName());
				for (InterfaceAddress i : interfaces) {
					System.out.println("\t" + i.getAddress());
				}
			}
			// Enumeration<InetAddress> addresses;
			// addresses = networkinterface.getInetAddresses();
			// while (addresses.hasMoreElements()) {
			// System.out.println("\t" +
			// addresses.nextElement().getHostAddress() + "");
			// }
		}
	}
}