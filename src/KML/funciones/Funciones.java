package kml.funciones;

import java.util.ArrayList;
import geotransform.coords.Gdc_Coord_3d;
import geotransform.coords.Utm_Coord_3d;
import geotransform.ellipsoids.WE_Ellipsoid;
import geotransform.transforms.Gdc_To_Utm_Converter;
import geotransform.transforms.Utm_To_Gdc_Converter;

public class Funciones {
	public double[] conv_gdc( double x, double y ){
    	double[] lat_lon = new double[2];
        //Utm_To_Gdc_Converter.Convert(utm,gdc);
        Gdc_Coord_3d gdc = new Gdc_Coord_3d();
        Utm_Coord_3d utm = new Utm_Coord_3d( x,           //x utm
											 y,           //y utm
											 0,           //z utm
											 (byte)19,  //zona
											 false );     //hemisferio norte
        Utm_To_Gdc_Converter.Init( new WE_Ellipsoid( ) );
        Utm_To_Gdc_Converter.Convert( utm, gdc );
        lat_lon[0] = gdc.latitude;
        lat_lon[1] = gdc.longitude;
        return lat_lon;
    }

    //Convertir coordenadas a UTM
    public ArrayList<Double> conv_utm( Double latitud, Double longitud ){
        Gdc_Coord_3d puntoLatLon = new Gdc_Coord_3d( latitud, longitud, 100.0 );
        Utm_Coord_3d puntoUtm    = new Utm_Coord_3d( );
        Gdc_To_Utm_Converter.Init( new WE_Ellipsoid( ) );
        Gdc_To_Utm_Converter.Convert( puntoLatLon, puntoUtm );
        ArrayList<Double> utmXY = new ArrayList<>( );
        utmXY.add( puntoUtm.x );
        utmXY.add( puntoUtm.y );
        return utmXY;
    }
}
