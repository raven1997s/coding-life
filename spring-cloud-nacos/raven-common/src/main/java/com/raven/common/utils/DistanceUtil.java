package com.raven.common.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * <p>Description: 距离工具</p>
 * @author: by qulibin
 * @date: 2020/3/22  9:51 PM
 * @version: 1.0
 */
public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378137;//赤道半径

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * @author: qulibin
	 * @description:
	 * @date: 9:52 PM 2020/3/22
	 * @return: 单位米
	 */
	public static double getDistance(double lng, double lat, double lng1, double lat1) {
		double radLat1 = rad(lat);
		double radLat2 = rad(lat1);
		double a = radLat1 - radLat2;
		double b = rad(lng) - rad(lng1);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		return s;
	}

    public static double getDistanceSphere(double latFrom, double lngFrom, double latTo, double lngTo) {
        GlobalCoordinates from = new GlobalCoordinates(latFrom, lngFrom);
        GlobalCoordinates to = new GlobalCoordinates(latTo, lngTo);
        return getDistanceByDiffCoordinateSystem(from, to, Ellipsoid.Sphere);
    }

    public static double getDistanceWGS84(double latFrom, double lngFrom, double latTo, double lngTo) {
        GlobalCoordinates from = new GlobalCoordinates(latFrom, lngFrom);
        GlobalCoordinates to = new GlobalCoordinates(latTo, lngTo);
        return getDistanceByDiffCoordinateSystem(from, to, Ellipsoid.WGS84);
    }

    public static double getDistanceByDiffCoordinateSystem(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }
}
