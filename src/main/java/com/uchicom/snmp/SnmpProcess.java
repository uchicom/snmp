package com.uchicom.snmp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 161 M→A 162 M←A
 * 
 * @author hex
 *
 */
public class SnmpProcess {

	/**
	 * 受信処理
	 */
	public void execute() {
		DatagramSocket socket = null;
		byte[] buf = new byte[128];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);

		try {
			socket = new DatagramSocket(162);
			System.out.println("start port=" + socket.getLocalPort() + ")");
			while (true) {
				socket.receive(packet);
				String message = new String(buf, 0, packet.getLength());
				System.out.println(packet.getSocketAddress() + " received: " + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}

	/**
	 * 送信処理
	 */
	public void send() {
		DatagramSocket socket = null;
		InetSocketAddress remoteAddress = new InetSocketAddress("localhost", 161);

		try {
			BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));
			socket = new DatagramSocket();
			String message;
			while ((message = keyIn.readLine()).length() > 0) {
				byte[] buf = message.getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, remoteAddress);
				socket.send(packet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
}
