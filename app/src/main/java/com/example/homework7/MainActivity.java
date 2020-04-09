package com.example.homework7;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    EditText etOrientation;
    EditText etMagnetic;
    EditText etTemerature;
    EditText etLight;
    EditText etPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etOrientation=(EditText)findViewById(R.id.etOrientation);
        etMagnetic=(EditText)findViewById(R.id.etMagnetic);
        etTemerature=(EditText)findViewById(R.id.etTemerature);
        etLight=(EditText)findViewById(R.id.etLight);
        etPressure=(EditText)findViewById(R.id.etPressure);
        //获取传感器管理服务
        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的磁场传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的温度传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的光传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_GAME);
        //为系统的压力传感器注册监听器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
                SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop() {
        //程序退出取消注册传感器的监听器
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onPause() {
        //程序暂停时同样取消注册监听器
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values=sensorEvent.values;
        //获取触发event的传感器类型
        int sensorType=sensorEvent.sensor.getType();
        //StingBulider是一个字符串处理类
        StringBuilder sb=null;
        //用switch判断是哪个传感器发生改变
        switch (sensorType){
            case Sensor.TYPE_ORIENTATION:
                sb=new StringBuilder();
                sb.append("绕Z轴转过的角度：");
                sb.append(values[0]);
                sb.append("\n绕X轴转过的角度：");
                sb.append(values[1]);
                sb.append("\n绕Y轴转过的角度：");
                sb.append(values[2]);
                etOrientation.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb=new StringBuilder();
                sb.append("X轴方向上的磁场强度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的磁场强度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的磁场强度：");
                sb.append(values[2]);
                etMagnetic.setText(sb.toString());
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb=new StringBuilder();
                sb.append("当前温度为：");
                sb.append(values[0]);
                etTemerature.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb=new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                etLight.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb=new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                etPressure.setText(sb.toString());
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
