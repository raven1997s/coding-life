package com.raven.common.utils;

import com.vip.vjtools.vjkit.time.DateFormatUtil;
import com.zhouyutong.zapplication.exception.ServiceException;
import com.zhouyutong.zapplication.utils.DateUtils;
import com.zhouyutong.zapplication.utils.NumberDate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author: by qulibin
 * @date: 2020/3/9  5:38 PM
 * @version: 1.0
 */
@Slf4j
public final class DateUtil {

	public static int getAgeFromIdCardNumber(String idCardNumber) {
		int leh = idCardNumber.length();
		if (leh == 18) {
			String dates = idCardNumber.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new Date());
			int u = Integer.parseInt(year) - Integer.parseInt(dates);
			return u;
		} else {
			String dates = idCardNumber.substring(6, 8);
			return Integer.parseInt(dates);
		}
	}

	/**
	 * 对出库时间格式统一转换为系统标准时间yyyy-MM-dd HH:mm:ss
	 *
	 * @param timeValue
	 * @param timeDesc
	 * @return
	 */
	public static String transTime(String timeValue, String timeDesc) {
		if (StringUtils.isBlank(timeValue)) {
			return "";
		}
		if (TextValidatorUtil.isDateTime(timeValue)) {
			return timeValue;
		}

		String timeValueToUse = timeValue;
		if (StringUtils.contains(timeValueToUse, "/")) {
			timeValueToUse = timeValueToUse.replace("/", "-");
		}
		NumberDate timeValueToUseNumberDate;
		try {
			if (StringUtils.contains(timeValueToUse, ":")) {
				timeValueToUseNumberDate = NumberDate
						.newNumberDate(timeValueToUse, NumberDate.FORMAT_YYYY_MM_DD_HH_MM_SS);
				NumberDate.newNumberDate(timeValueToUse, NumberDate.FORMAT_YYYY_MM_DD_HH_MM_SS);
			} else {
				timeValueToUseNumberDate = NumberDate.newNumberDate(timeValueToUse, NumberDate.FORMAT_YYYY_MM_DD);
			}
		} catch (RuntimeException e) {
			String errorMsg = String.format("无法将[%s]的值[%s]转换为标准的日期时间格式", timeDesc, timeValue);
			throw new ServiceException(errorMsg);
		}

		return timeValueToUseNumberDate.toFormatString(NumberDate.FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	public static String getCurrentTime() {
		return DateUtils.format(new Date(), DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
	}

	public static String format(long dateTime) {
		if (dateTime <= 0) {
			return "";
		}
		try {
			return DateUtils.format(DateUtils.parse(dateTime), DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			log.error("format error" + dateTime, e);
			return "";
		}
	}

	public static long parse(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return 0;
		}
		try {
			final String transTime = DateUtil.transTime(dateTime, dateTime);
			return DateUtils.format2Long(DateFormatUtil.parseDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, transTime));
		} catch (Exception e) {
			log.error("format error" + dateTime, e);
			return 0;
		}
	}

	//201900000000格式化到2019-00-00 00:00:00
	public static String numberDateToString(long time) {
		if (time <= 0L) {
			return "";
		}
		try {
			return NumberDate.newNumberDate(time).toFormatString(NumberDate.FORMAT_YYYY_MM_DD_HH_MM_SS);
		} catch (RuntimeException e) {
			return "";
		}
	}

	public static long durationSeconds(Date startDate, Date endDate) {
		DateTime startTimeD = new DateTime(startDate);
		DateTime endTimeD = new DateTime(endDate);
		Duration duration;
		if (startTimeD.isBefore(endTimeD)) {
			duration = new Duration(startTimeD, endTimeD);
		} else {
			duration = new Duration(endTimeD, startTimeD);
		}

		return duration.getStandardSeconds();
	}
}
