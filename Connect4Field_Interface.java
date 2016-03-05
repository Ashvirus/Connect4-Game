/*
 * Connect4Field_Interface.java 

 * 
 * Version: 1.0 11/23/2015
 * 
 * @author: Ashwini Singh
 * @author: Prajesh Jhumkhawala
 *
 *
 * This is the interface which contains the method which will be implemented by the Server
 */


import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connect4Field_Interface extends Remote {
    public void run1(int h, int index)  throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public void Display() throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public void draw()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public boolean GameOver()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public void finish()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int Winner()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public Object[][] disp() throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public void Cfinish(int n)throws MalformedURLException, RemoteException,AlreadyBoundException ;
	 public int start() throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public void send(int i)throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int turn()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int CP1()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int CP2()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int CP3()throws MalformedURLException, RemoteException,AlreadyBoundException ;
	public int CP4()throws MalformedURLException, RemoteException,AlreadyBoundException ;

	
}
