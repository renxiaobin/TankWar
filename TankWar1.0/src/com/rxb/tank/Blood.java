package com.rxb.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blood {
	int x,y,w,h;
	TankClient tc;
	
	int step = 0;
	private boolean live = true;
	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	//The trail of Blood move
	private int[][] pos= {
			{350,300},
			{360,300},
			{375,290},
			{400,260},
			{370,280},
			{365,290},
			{340,280}
	};

	public Blood() {
		this.x = pos[0][0];
		this.y = pos[0][1];
		w=h=15;
	}
	
	public void draw(Graphics g){
		if(!live) return;
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		
		move();
	}

	private void move() {
		step++;
		if(step==pos.length){
			step=0;
		}
		x = pos[step][0];
		y=pos[step][1];
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
}
