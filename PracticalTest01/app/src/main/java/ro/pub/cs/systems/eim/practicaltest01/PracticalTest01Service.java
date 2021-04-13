package ro.pub.cs.systems.eim.practicaltest01;
import ro.pub.cs.systems.eim.practicaltest01.Constants;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Service extends Service {

    private ProcessingThread processingThread = null;

    public int onStartCommand(Intent intent, int flags, int startId){
        int first = intent.getIntExtra(Constants.FIRST_NUMBER,-1);
        int second = intent.getIntExtra(Constants.SECOND_NUMBER, -1);

        processingThread = new ProcessingThread(this, first, second);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    public void onDestory() {
        Intent intent = new Intent(this, PracticalTest01Service.class);
        stopService(intent);
        super.onDestroy();
    }
}