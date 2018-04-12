package UE2_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Browser extends JFrame implements ActionListener{

	private int downloads;
	private JProgressBar[] balken;
	private JButton startButton;
	private CyclicBarrier barrier;
	CountDownLatch countDownLatch;
	// Deklaration Ihrer Synchronisations-Hilfsklassen hier:





	public Browser(int downloads ) {
		super("Mein Download-Browser");
		this.downloads = downloads;
		barrier = new CyclicBarrier(downloads+1);
		countDownLatch = new CountDownLatch(downloads);

		// Initialisierung Ihrer Synchronisations-Hilfsklassen hier:






		// Aufbau der GUI-Elemente:
		balken = new JProgressBar[downloads];
		JPanel zeilen = new JPanel(new GridLayout(downloads, 1));

		for(int i=0; i<downloads; i++) {
			JPanel reihe = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0, 10));
			balken[i] = new JProgressBar(0, 100);
                        balken[i].setPreferredSize(new Dimension(500, 20));
                        balken[i].setBackground(new Color(200,0,0));
			reihe.add(balken[i]);
			zeilen.add(reihe);


			 new Download(balken[i],barrier,countDownLatch).start();

			// neue Download-Threads erzeugen und starten
			// ggf. müssen Synchronisations-Objekte im Konstruktor übergeben werden!!
			// balken ist ebenfalls zu übergeben!



		}

		startButton = new JButton("Downloads starten");
		startButton.addActionListener(this);

		this.add(zeilen, BorderLayout.CENTER);
		this.add(startButton, BorderLayout.SOUTH);


		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws InterruptedException {
		new Browser(5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Blockierte Threads jetzt laufen lassen:






		startButton.setEnabled(false);
		startButton.setSelected(false);
		startButton.setText("Downloads laufen...");

		try {
			barrier.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BrokenBarrierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new Thread(new Runnable()
		{
			public void run()
			{
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startButton.setText("Ende");
			}

		}).start();

		// Auf Ende aller Download-Threads warten ... erst dann die Beschriftung ändern
		// Achtung, damit die Oberflaeche "reaktiv" bleibt dies in einem eigenen Runnable ausfuehren!










	}

}
