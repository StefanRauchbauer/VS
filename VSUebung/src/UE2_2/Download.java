package UE2_2;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JProgressBar;

// aktive Klasse
public class Download extends Thread
{
	
	private final JProgressBar balken;
	private int n;
	private CyclicBarrier barrier1 = null;
    // weitere Attribute zur Synchronisation hier definieren
    
	public Download(JProgressBar balken,CyclicBarrier barrier1 /* ggf. weitere Objekte */) {
		this.balken = balken;
        // ...
		this.barrier1 = barrier1;
		
    }


    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */
		
	@Override
	public void run()
	{
		try {
			this.barrier1.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(balken.getMaximum()!=n)
		{
		n++;
		balken.setValue(n);
		try {
			Thread.sleep(new	Random().nextInt(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	


}
