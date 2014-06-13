package communication;

import java.io.IOException;
import java.io.OutputStream;

public class SerialWriter implements Runnable {
	OutputStream out;
	private boolean isRunning;
    
    public SerialWriter (OutputStream out)
    {
        this.out = out;
        this.isRunning = true;
    }

	public void run ()
    {
		while (isRunning) {
			try {
				Message msg = CommunicationManager.getInstance().getOutputQueue().take();
				this.out.write(CommunicationManager.startDelimiter);
				this.out.write(msg.getBytes(), 0, msg.getBytes().length);
				this.out.write(CommunicationManager.stopDelimiter);
	        }
	        catch ( IOException e )
	        {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
				e.printStackTrace();
			}
		}       
    }
	
	public void stopThread() {
    	this.isRunning = false;
    }
}
