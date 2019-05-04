package com.uchicom.snmp;

public class Main {

	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			SnmpProcess process = new SnmpProcess();
			process.execute();
		});
		thread.setDaemon(true);
		thread.start();
		Thread thread2 = new Thread(() -> {
			SnmpProcess process = new SnmpProcess();
			process.send();
		});
		thread2.setDaemon(true);
		thread2.start();

	}

}
