/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */
package jxtn.core.unix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * {@codse struct timespec}
 *
 * @author aqd
 */
public final class Timespec implements Comparable<Timespec> {

    public final long tv_sec;
    public final long tv_nsec;

    Timespec(ByteBuffer buffer) {
        buffer.order(ByteOrder.nativeOrder());
        this.tv_sec = buffer.getLong();
        this.tv_nsec = buffer.getLong();
    }

    public DateInfo toDateInfo() {
        return new DateInfo(this.tv_sec);
    }

    public TimeInfo toTimeInfo() {
        return new TimeInfo(this.tv_sec, this.tv_nsec);
    }

    @Override
    public int compareTo(Timespec other) {
        long sec_diff = this.tv_sec - other.tv_sec;
        if (sec_diff > 0) {
            return 1;
        } else if (sec_diff < 0) {
            return -1;
        }
        long nsec_diff = this.tv_nsec - other.tv_nsec;
        if (nsec_diff > 0) {
            return 1;
        } else if (nsec_diff < 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return this.equals((Timespec) obj);
    }

    public boolean equals(Timespec other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        return this.tv_sec == other.tv_sec
                && this.tv_nsec == other.tv_nsec;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.tv_sec);
        hash = 17 * hash + (int) (this.tv_nsec);
        return hash;
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.ofEpochSecond(this.tv_sec, (int) this.tv_nsec, ZoneOffset.UTC);
    }

    public OffsetDateTime toOffsetDateTime() {
        return OffsetDateTime.of(this.toLocalDateTime(), ZoneOffset.UTC);
    }

    @Override
    public String toString() {
        return this.toDateInfo() + "T" + this.toTimeInfo() + "Z";
    }

    public static final class DateInfo {

        public final int year;
        public final int month;
        public final int day;

        public DateInfo(long seconds) {
            int epochDays = (int) (seconds / 86400L);
            epochDays += 719468;
            int era = (epochDays >= 0 ? epochDays : epochDays - 146096) / 146097;
            int dayOfEra = epochDays - era * 146097;
            int yearOfEra = (dayOfEra - dayOfEra / 1460 + dayOfEra / 36524 - dayOfEra / 146096) / 365;
            int y = yearOfEra + era * 400;
            int dayOfYear = dayOfEra - (365 * yearOfEra + yearOfEra / 4 - yearOfEra / 100);
            int mp = (5 * dayOfYear + 2) / 153;
            int d = dayOfYear - (153 * mp + 2) / 5 + 1;
            int m = mp + (mp < 10 ? 3 : -9);
            this.year = y + (m <= 2 ? 1 : 0);
            this.month = m;
            this.day = d;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("2015-10-29".length());
            sb.append(StringFormat.padLeft(String.valueOf(this.year), 4, '0'));
            sb.append('-');
            sb.append(StringFormat.padLeft(String.valueOf(this.month), 2, '0'));
            sb.append('-');
            sb.append(StringFormat.padLeft(String.valueOf(this.day), 2, '0'));
            return sb.toString();
        }
    }

    public static final class TimeInfo {

        public final int hour;
        public final int minute;
        public final int second;
        public final int millisecond;

        public TimeInfo(long seconds, long nanoseconds) {
            this.millisecond = (int) (nanoseconds / 1000000L);
            int remainingSeconds = (int) (seconds % 86400L);
            this.hour = remainingSeconds / 3600;
            remainingSeconds -= this.hour * 3600;
            this.minute = remainingSeconds / 60;
            remainingSeconds -= this.minute * 60;
            this.second = remainingSeconds;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("19:21:52.891".length());
            sb.append(StringFormat.padLeft(String.valueOf(this.hour), 2, '0'));
            sb.append(':');
            sb.append(StringFormat.padLeft(String.valueOf(this.minute), 2, '0'));
            sb.append(':');
            sb.append(StringFormat.padLeft(String.valueOf(this.second), 2, '0'));
            sb.append('.');
            sb.append(StringFormat.padLeft(String.valueOf(this.millisecond), 3, '0'));
            return sb.toString();
        }
    }

}
