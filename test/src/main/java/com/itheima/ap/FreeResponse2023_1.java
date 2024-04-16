package com.itheima.ap;

public class FreeResponse2023_1 {
    static class AppointmentBook {
        private boolean isMinuteFree(int period, int minute) {
            return true;
        }

        private void reserveBlock(int period, int start, int duration) {

        }

        /*
            返回占用的时间起点
            0 1 2 3 4 5 6
            -
         */
        private int findFreeBlock(int period, int duration) {
            int count = 0;
            for (int minute = 0; minute < 60; minute++) {
                if(isMinuteFree(period, minute)) {
                    count++;
                    if (count == duration) {
                        return minute - duration + 1;
                    }
                } else {
                    count = 0;
                }
            }
            return -1;
        }

        public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
            for (int period = startPeriod; period <= endPeriod; period++) {
                int startBlock = findFreeBlock(period, duration);
                if(startBlock != -1) {
                    reserveBlock(period, startBlock, duration);
                    return true;
                }
            }
            return false;
        }
    }
}
