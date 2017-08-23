package com.rxb.tank;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TankDeadMsg implements Msg {
	int msgType = Msg.TANK_DEAD_MSG;
	TankClient tc;
	int id;
	public TankDeadMsg(int id){
		this.id = id;
	}
	
	public TankDeadMsg(TankClient tc) {
		this.tc= tc;
	}

	@Override
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf = baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length,new InetSocketAddress(IP, udpPort));
			ds.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parse(DataInputStream dis) {
		try {
			int id = dis.readInt();
			if(tc.myTank.id == id){
				return;
			}
			
			for(int i=0;i<tc.tanks.size();i++){
				Tank t =tc.tanks.get(i);
				if(t.id==id){
					t.setLive(false);
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
