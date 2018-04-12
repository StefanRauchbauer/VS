package UE2_2;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JProgressBar;

// aktive Klasse
public class Download extends Thread
{

	private final JProgressBar balken;
	private int n;
	private CyclicBarrier barrier1 = null;
	private CountDownLatch countDownLatch;

    // weitere Attribute zur Synchronisation hier definieren

	public Download(JProgressBar balken,CyclicBarrier barrier1, CountDownLatch cdl /* ggf. weitere Objekte */) {
		this.balken = balken;
        // ...
		this.barrier1 = barrier1;
		this.countDownLatch = cdl;
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
			System.out.println(this.currentThread().getName()+ " wartet");
			this.barrier1.await();
			System.out.println(this.currentThread().getName()+ " startet");
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
			Thread.sleep(new	Random().nextInt(100));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		}
		countDownLatch.countDown();
	}



}
