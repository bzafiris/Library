#!/bin/bash
DEVICE=$2
if [ -z "$2" ]
  then
    DEVICE="emulator-5554"
fi

echo "pidcat on $DEVICE"

./pidcat.py -c -s $DEVICE  package com.mgiandia.library -i "dalvikvm|MonitoringInstrumentation|LifecycleMonitor|OpenGLRenderer|TestExecutor|MultiDex|DynamiteModule|ESP_TRACE|UiControllerImpl|EGL_emulation|HardwareRenderer|art"   --clear
