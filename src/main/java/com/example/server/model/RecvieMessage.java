package com.example.server.model;


import java.io.Serializable;

public class RecvieMessage  implements Serializable {

	/**
	 * 文件类型
	 */
	private static final long serialVersionUID = 6200390330718630934L;

	private short msgType;//文件类型

	private String data;//文件的具体信息

	public short getMsgType() {
		return msgType;
	}

	public void setMsgType(short msgType) {
		this.msgType = msgType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
}
