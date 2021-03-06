import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client3 extends Thread{
	private Client3() {
	}

	static int win = 0;
	static Connect4Field_View cv = new Connect4Field_View();
	static Connect4Field_Interface stub;
	static String host ;
	Registry registry;
	static boolean first=true;
	static boolean first1=true;
	public static void main(String[] args)
			throws AlreadyBoundException, MalformedURLException, RemoteException, NotBoundException, InterruptedException {
		host = (args.length < 1) ? null : args[0];
		Client3 t=new Client3();
		t.start();
		t.join();
		
	}
	public void run()
	{/*
		 * When the server thread is initialized, it will call the run method.
		 * 
		 */

		try
		{
			run2();
		}
		catch(Exception e)
		{
			
		}
	}
	public void run2()	throws AlreadyBoundException, MalformedURLException, RemoteException, NotBoundException 
	{/*
		 * THe run method will make a call to the run2()
		 * 
		 * 
		 */

		try {
			registry = LocateRegistry.getRegistry();//Getting the registry
			stub = (Connect4Field_Interface) registry.lookup("Hello11");//Connecting to the server
			stub.send(3);//Sending value to the stub

			while (true) {
		
		//		 registry = LocateRegistry.getRegistry(host);

				stub = (Connect4Field_Interface) registry.lookup("Hello11");
				int out = 0;
				int p = -1;
				int l=stub.start();
				if(l>=4){
				int x=stub.turn();//Checking whose turn it is
				if (x==0 || x==3){
		
				System.out.println("Enter Column Number: ");
				while (!(out == 1)) {
					p = cv.getColumn();//Taking column from the user  and checking if it is valid or not
					if ((p < 0 || p > 24)) {
						System.out.println("Invalid Column> Enter Again: ");
						out = 0;
					} else {

						out = 1;
					}

				}
				stub.run1(p, 3);//Calling the run method of the server and then displaying the updated box
				//System.out.println("here");
				System.out.println("After Run");
				stub.Display();
				Object box[][] = (Object[][]) stub.disp();
				System.out.println("\n");
				for (int k = 0; k < 3; k++) {
					for (int col = 0; col < 8; col++) {
						System.out.print(col + " ");
					}
				}
				System.out.print("24");
				System.out.println("\n");

				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 25; col++) {
						System.out.print(box[row][col] + " ");
					}
					System.out.println("");
				}

				boolean end = stub.GameOver();
				win = stub.Winner();
				//System.out.println("Temp Winner is :" + win);

				if (end == true) {
					break;

				}
			}
				else //If other players don't provide any input, then the player is closed
				{
//					Thread.sleep(5000);
//					System.out.println("Inside");
					int c1=stub.CP1();
					int c2=stub.CP2();
					int c4=stub.CP4();
					if (c1==1 && c2==2 && c4==4)
					{
						System.out.println("Other players have lost the connection");
					break;	
					}
						
				}
		
			}
				else //If other players are not connected, then the player is closed
				{
					if (first==true)
					Thread.sleep(50000);
					else
					{
						System.out.println("Other players have lost the connection");
						
						stub.Cfinish(3);
						
						System.exit(0);
						
					}
					first=false;
					
				}
				}} catch (Exception e) {

		}

			win = stub.Winner();
			System.out.println("Winner is :" + win);
		try {
			stub.Cfinish(3);
		} catch (Exception e) {
		}
		System.exit(0);

	}
}