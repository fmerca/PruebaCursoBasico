package uy.com.antel.capacitacion.pruebacursobasico;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

public class Upload extends Service {
    public Upload() {
    }

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    Bundle bundle;
    int mId = 0;

    private final class ServiceHandler extends Handler  {
        public ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {

            long endTime = System.currentTimeMillis() + 10*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }

            //TODO notificacion + bundle uri y carpeta
            int s = R.string.strCargaCompleta;
            String ss  = getResources().getString(s);
            Notification.Builder mBuilder =
                    new Notification.Builder(Upload.this)
                            .setSmallIcon(R.mipmap.folder)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.folder))
                            .setContentTitle(ss);


//            Intent resultIntent = new Intent(Upload.this, CompartirArchivo.class);
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(Upload.this);
//            stackBuilder.addParentStack(MainActivity.class);
//            stackBuilder.addNextIntent(resultIntent);
//            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//            //mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.InboxStyle inboxStyle= new Notification.InboxStyle();

            int u = R.string.strUri;
            String uu  = getResources().getString(u);
            int f = R.string.strFolder;
            String ff  = getResources().getString(f);

            String[] events  = new String[2];
            events[0] = uu+bundle.getString("uri");
            events[1] = ff+bundle.getString("carpeta");

            for(int i = 0; i< events.length; i++){
                inboxStyle.addLine(events[i]);
            }
            mBuilder.setStyle(inboxStyle);
            mNotificationManager.notify(mId, mBuilder.build());
            stopSelf(msg.arg1);
        }
    }
    @Override
    public void onCreate() {

        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, R.string.strCargaIniciada, Toast.LENGTH_SHORT).show();
        bundle = intent.getExtras();
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {

    }
}
