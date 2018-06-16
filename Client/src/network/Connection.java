package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Connection extends MyThread{

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;


	public Connection(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		start();
	}

	public void setInput(DataInputStream input) {
		this.input = input;
	}

	public Socket getSocket() {
		return socket;
	}

	public DataInputStream getInput() {
		return input;
	}

	public DataOutputStream getOutput() {
		return output;
	}

	public void send(String data) throws IOException{
		output.writeUTF(data);
	}

	public void sendInt(int data) throws IOException{
		output.writeInt(data);
	}

	public String readResponse() throws IOException{
		return input.readUTF();
	}

	public int readResponseInt() throws IOException {
		return input.readInt();
	}

	public void close() throws IOException{
		output.close();
		input.close();
		socket.close();
	}

	public void saveFile() throws IOException {
		File fi = new File("src/persistence");
		if(!fi.exists()) {
			fi.mkdir();
		}
		try{
			setInput(new DataInputStream(getSocket().getInputStream()));
			String nameFile = getInput().readUTF();
			int tam = getInput().readInt();
			File f = new File("src/persistence/" + nameFile);
			FileOutputStream fos = new FileOutputStream(f);
			@SuppressWarnings("resource")
			BufferedOutputStream out = new BufferedOutputStream(fos);
			BufferedInputStream in = new BufferedInputStream(getSocket().getInputStream());
			byte[] buffer = new byte[tam];
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = (byte) in.read();
			}
			out.write(buffer);
			out.flush();
		} catch (IOException e1) {
			System.out.println("Recibir "+ e1.toString());
		}
	}
	
	public void sendFile(File file){
		try {
			int fileSize = (int) file.length();
			DataOutputStream output = new DataOutputStream(getOutput());
			output.writeUTF(file.getName());
			output.writeInt(fileSize);
			FileInputStream filInp = new FileInputStream(file);
			@SuppressWarnings("resource")
			BufferedInputStream bis = new BufferedInputStream(filInp);
			BufferedOutputStream bos = new BufferedOutputStream(getOutput());
			byte[] buffer = new byte[fileSize];
			bis.read(buffer);
			for (int i = 0; i < buffer.length; i++) {
				bos.write(buffer[i]);
			}
			bos.flush();
		} catch (IOException e) {
			System.out.println("Error al crear canal de salida en el servidor.");
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}