package kml.io;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kml.estructuras.Calle;
import kml.estructuras.Circulo;
import kml.estructuras.Nodo;

import java.util.Random;

public class Escritura {
	
	private static final Logger log = LoggerFactory.getLogger(Escritura.class);
	
	public void grafoKML(List<Calle> calles) {
		File folder = new File("KML_Output");
		FileWriter fichero = null;

		PrintWriter pw = null;
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			// use FileWriter constructor that specifies open for appending
			fichero = new FileWriter("KML_Output/" + "Grafo" + ".kml"); /// Escribe el KML con el nombre de la comuna
			pw = new PrintWriter(fichero);
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
					+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "\t" + "<Document id=\"root_doc\">\n"
					+ "\t\t" + "<Schema name=\"PuntosMM\" id=\"PuntosMM\">\n" + "\t\t\t"
					+ "<SimpleField name=\"id\" type=\"int\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Tiempo\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Probabilidad\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"PobExp800\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"PobExp1000\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Riesgo800\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Riesgo1000\" type=\"double\"></SimpleField>\n" + "\t\t" + "</Schema>");
			// escribirStyleKMLCallesColorRandom(pw,MMPuntos.size());
			pw.println("<Style id=\"sn_ylw-pushpin\">\r\n" + "		<BalloonStyle>\r\n" + "		</BalloonStyle>\r\n"
					+ "		<LineStyle>\r\n" + "			<color>ff00ffff</color>\r\n"
					+ "			<width>3</width>\r\n" + "		</LineStyle>\r\n" + "	</Style>\r\n"
					+ "	<Style id=\"sh_ylw-pushpin\">\r\n" + "		<IconStyle>\r\n"
					+ "			<scale>1.2</scale>\r\n" + "		</IconStyle>\r\n" + "		<BalloonStyle>\r\n"
					+ "		</BalloonStyle>\r\n" + "		<LineStyle>\r\n" + "			<color>ff00ffff</color>\r\n"
					+ "			<width>3</width>\r\n" + "		</LineStyle>\r\n" + "	</Style>\r\n"
					+ "	<StyleMap id=\"msn_ylw-pushpin\">\r\n" + "		<Pair>\r\n" + "			<key>normal</key>\r\n"
					+ "			<styleUrl>#sn_ylw-pushpin</styleUrl>\r\n" + "		</Pair>\r\n" + "		<Pair>\r\n"
					+ "			<key>highlight</key>\r\n" + "			<styleUrl>#sh_ylw-pushpin</styleUrl>\r\n"
					+ "		</Pair>\r\n" + "	</StyleMap>");

			pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>calles</name>");
			for (Calle aux : calles) {
				pw.print("\t\t\t" + "<Placemark>\n" + "\t\t\t\t" + "<name>" + aux.getIdArco() + "</name>\n" + "\t\t\t\t"
						+ "<styleUrl>#msn_ylw-pushpin</styleUrl>" + "\t\t\t\t" + "<visibility>0</visibility>\n"
						+ "\t\t\t\t" + "<ExtendedData><SchemaData schemaUrl=\"PuntosMM\">\n" + "\t\t\t\t"
						+ "<SimpleField name=\"id\">" + aux.getIdArco() + "</SimpleField>\n" + "\t\t\t\t"
						+ "<SimpleField name=\"Tiempo\">" + aux.getTiempo() + "</SimpleField>\n" + "\t\t\t\t"
						+ "<SimpleField name=\"Probabilidad\">" + aux.getProbabilidad() + "</SimpleField>\n"
						+ "\t\t\t\t" + "<SimpleField name=\"PobExp800\">" + aux.getPobExpuesta800() + "</SimpleField>\n"
						+ "\t\t\t\t" + "<SimpleField name=\"PobExp1000\">" + aux.getPobExpuesta1000()
						+ "</SimpleField>\n" + "\t\t\t\t" + "<SimpleField name=\"Riesgo800\">" + aux.getRiesgo800()
						+ "</SimpleField>\n" + "\t\t\t\t" + "<SimpleField name=\"Riesgo1000\">" + aux.getRiesgo1000()
						+ "</SimpleField>\n" + "\t\t\t\t" + "</SchemaData></ExtendedData>");
				pw.print("\t\t\t\t" + "<LineString>\n" + "\t\t\t\t" + "<extrude>1</extrude>\n" + "\t\t\t\t"
						+ "<tessellate>1</tessellate>\n" + "\t\t\t\t\t" + "<coordinates>\n");
				pw.print("\t\t\t\t\t" + aux.getaLon() + "," + aux.getaLat() + "\n");
				pw.print("\t\t\t\t\t" + aux.getbLon() + "," + aux.getbLat() + "\n");
				pw.print("\t\t\t\t\t" + "</coordinates>\n");
				pw.print("\t\t\t\t" + "</LineString>\n" + "\t\t\t" + "</Placemark>\n");
				pw.flush();
			}

			pw.print("\t\t" + "</Folder>\n");

			pw.print("\t" + "</Document>\n" + "</kml>");
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void escribirRadio(PrintWriter pw, List<Circulo> puntosRiesgo) {
		pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>Radio</name>");
		pw.println("<visibility>0</visibility>");
		puntosRiesgo.forEach(it -> {
			pw.flush();
			pw.println("<Folder>\r\n" + "				<name>Radio</name>\r\n" + "\t\t\t\t"
					+ "<styleUrl>#failed</styleUrl>" + "				<open>1</open>\r\n"
					+ "			<visibility>0</visibility>\r\n" + "				<Placemark>\r\n"
					+ "			<visibility>0</visibility>\r\n" + "					<name>" + it.getCentro().getId()
					+ "</name>\r\n" + "					<styleUrl>#failed1</styleUrl>\r\n"
					+ "					<Polygon>\r\n" + "						<tessellate>1</tessellate>\r\n"
					+ "						<outerBoundaryIs>\r\n" + "							<LinearRing>\r\n"
					+ "								<coordinates>\r\n");
			it.getPuntos().forEach(kt -> {
				pw.println(kt.getY() + "," + kt.getX() + ",0");

			});
			pw.println(it.getPuntos().get(0).getY() + "," + it.getPuntos().get(0).getX() + ",0");
			pw.println("								</coordinates>\r\n"
					+ "							</LinearRing>\r\n" + "						</outerBoundaryIs>\r\n"
					+ "					</Polygon>\r\n" + "				</Placemark>\r\n" + "			</Folder>");

		});
		pw.println("\t\t" + "</Folder>\n");
	}

	public void nodosRiesgoKML(List<Circulo> puntosRiesgo,String radio) {
		File folder = new File("KML_Output");
		FileWriter fichero = null;
		try {
			if (!folder.exists()) {
				folder.mkdir();
			}
			fichero = new FileWriter("KML_Output/" + "NodosRiesgo_"+radio + ".kml"); /// Escribe el KML con el nombre de la
																				/// comuna
			final PrintWriter pw = new PrintWriter(fichero);
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
					+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "\t" + "<Document id=\"root_doc\">\n"
					+ "\t\t" + "<Schema name=\"PuntosMM\" id=\"PuntosMM\">\n" + "\t\t\t"
					+ "<SimpleField name=\"id\" type=\"int\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"demanda\" type=\"int\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"x\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"y\" type=\"double\"></SimpleField>\n" + "\t\t" + "</Schema>");
			escribirEstiloCirculo(pw);
			escribirRadio(pw,puntosRiesgo);
			pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>Nodos</name>");
			pw.println("<visibility>0</visibility>");
			puntosRiesgo.forEach(it -> {
				pw.flush();
				pw.println("<Placemark>\r\n" + "		<name>" + it.getCentro().getId() + "</name>\r\n" + "\t\t\t\t"
						+ "<ExtendedData><SchemaData schemaUrl=\"PuntosMM\">\n" + "\t\t\t\t\t"
						+ "<SimpleData name=\"id\">" + it.getCentro().getId() + "</SimpleData>\n" + "\t\t\t\t\t"
						+ "<SimpleData name=\"demanda\">" + it.getCentro().getDemanda() + "</SimpleData>\n"
						+ "\t\t\t\t\t" + "<SimpleData name=\"x\">" + it.getCentro().getX() + "</SimpleData>\n"
						+ "\t\t\t\t\t" + "<SimpleData name=\"y\">" + it.getCentro().getY() + "</SimpleData>\n"
						+ "\t\t\t\t" + "</SchemaData></ExtendedData>" + "<visibility>0</visibility>"
						+ "		<Point>\r\n" + "			<gx:drawOrder>1</gx:drawOrder>\r\n"
						+ "			<coordinates>" + it.getCentro().getY() + "," + it.getCentro().getX() + ",0"
						+ "</coordinates>\r\n" + "		</Point>\r\n" + "	</Placemark>");
			});
			pw.println("\t\t" + "</Folder>\n");
			pw.println("</Document>\r\n" + "</kml>");

			pw.close();
			fichero.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nodosRiesgoCSV(List<Nodo> puntosRiesgo) {
		File folder = new File("KML_Output");
		FileWriter fichero = null;
		try {
			if (!folder.exists()) {
				folder.mkdir();
			}
			fichero = new FileWriter("KML_Output/" + "NodosRiesgo" + ".csv"); /// Escribe el KML con el nombre de la
																				/// comuna
			final PrintWriter pw = new PrintWriter(fichero);
			String delimidator = ",";
			pw.println("Num" + delimidator + "Demanda" + delimidator + "Longitude" + delimidator + "Latitude"
					+ delimidator + "Radius" + delimidator + "Style" + delimidator + "Description");
			puntosRiesgo.forEach(it -> {
				pw.println(it.getId() + delimidator + it.getDemanda() + delimidator + it.getY() + delimidator
						+ it.getX() + "1000" + "101" + "algo");
			});
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nodosKML(Map<Integer, Nodo> puntos) {
		File folder = new File("KML_Output");
		FileWriter fichero = null;

		PrintWriter pw = null;
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			fichero = new FileWriter("KML_Output/" + "Nodos" + ".kml"); /// Escribe el KML con el nombre de la comuna
			pw = new PrintWriter(fichero);
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
					+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "\t" + "<Document id=\"root_doc\">\n"
					+ "\t\t" + "<Schema name=\"PuntosMM\" id=\"PuntosMM\">\n" + "\t\t\t"
					+ "<SimpleField name=\"id\" type=\"int\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"x\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"y\" type=\"double\"></SimpleField>\n" + "\t\t" + "</Schema>");
			pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>Nodos</name>");
			for (Entry<Integer, Nodo> aux : puntos.entrySet()) {
				pw.println("<Placemark>\r\n" + "		<name>" + aux.getValue().getId() + "</name>\r\n"
						+ "<visibility>0</visibility>" + "		<Point>\r\n"
						+ "			<gx:drawOrder>1</gx:drawOrder>\r\n" + "			<coordinates>"
						+ aux.getValue().getY() + "," + aux.getValue().getX() + ",0" + "</coordinates>\r\n"
						+ "		</Point>\r\n" + "	</Placemark>");
				pw.flush();
			}
			pw.print("\t\t" + "</Folder>\n");

			pw.print("\t" + "</Document>\n" + "</kml>");
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void escribirNodosCostos(Map<Integer,List<String>> costos,Map<Integer,List<Nodo>> ruta,Map<Integer,List<Calle>> arcos,String outname, boolean rr) {
		File folder = new File("Costos_Output");
		FileWriter fichero = null;
		PrintWriter pw = null;
		if (!folder.exists()) {
			folder.mkdir();
		}
		String rname = "CostosRepresentativos_"+outname;
		if (rr) {
			rname = "Costos_"+outname;
		}
		try {
			fichero = new FileWriter("Costos_Output/" + rname + ".csv");
			pw = new PrintWriter(fichero);
			for (Entry<Integer, List<String>> it : costos.entrySet()) {
				for(int k = 0; k < it.getValue().size() ; ++k){
					if(k == it.getValue().size() - 1){
						pw.println(String.format("costo_%d;ruta;arco",k));
					}else{
						pw.print(String.format("num;costo_%d;",k));
					}
				}
				break;
			}
			pw.flush();
			for (Entry<Integer, List<String>> it : costos.entrySet()) {
				pw.print(it.getKey()+";");
				for(int k = 0; k < it.getValue().size() ; ++k){
					pw.print(it.getValue().get(k)+";");
				}
				pw.print(ruta.get(it.getKey()).size()+";");
				pw.println(arcos.get(it.getKey()).size());
				pw.flush();
			}
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rutaKML(Map<Integer, List<Nodo>> ruta, Map<String, Calle> mapa, boolean rr,String outname,Map<Integer,List<Calle>> arcos) {
		File folder = new File("KML_Output");
		FileWriter fichero = null;

		PrintWriter pw = null;
		if (!folder.exists()) {
			folder.mkdir();
		}
		String rname = "RutasRepresentativas_"+outname;
		if (rr) {
			rname = "Rutas_"+outname;
		}
		try {
			fichero = new FileWriter("KML_Output/" + rname + ".kml"); /// Escribe el KML con el nombre de la comuna
			pw = new PrintWriter(fichero);
			pw.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
					+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" + "\t" + "<Document id=\"root_doc\">\n"
					+ "\t\t" + "<Schema name=\"PuntosMM\" id=\"PuntosMM\">\n" + "\t\t\t"
					+ "<SimpleField name=\"id\" type=\"int\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Tiempo\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Probabilidad\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"PobExp800\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"PobExp1000\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Riesgo800\" type=\"double\"></SimpleField>\n" + "\t\t\t"
					+ "<SimpleField name=\"Riesgo1000\" type=\"double\"></SimpleField>\n" + "\t\t" + "</Schema>");
			escribirStyleKMLCalles(pw, ruta.size());
			pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>Nodos</name>");
			for (Entry<Integer, List<Nodo>> it : ruta.entrySet()) {
				pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>" + it.getKey() + "</name>");
				for (Nodo kt : it.getValue()) {
					pw.println("<Placemark>\r\n" + "		<name>" + kt.getId() + "</name>\r\n"
							+ "<visibility>0</visibility>" + "		<Point>\r\n"
							+ "			<gx:drawOrder>1</gx:drawOrder>\r\n" + "			<coordinates>" + kt.getY() + ","
							+ kt.getX() + ",0" + "</coordinates>\r\n" + "		</Point>\r\n" + "	</Placemark>");
					pw.flush();
				}
				pw.print("\t\t" + "</Folder>\n");
			}
			pw.print("\t\t" + "</Folder>\n");
			pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>Rutas</name>");
			for (Entry<Integer, List<Nodo>> it : ruta.entrySet()) {
				String nombre = "";
				pw.println("\t\t" + "<Folder>\n" + "\t\t\t" + "<name>" + it.getKey() + nombre + "</name>");
				List<Calle> auxCalle = new ArrayList<>();
				for (int i = 1; i < it.getValue().size(); ++i) {
					String consultar = String
							.valueOf(it.getValue().get(i - 1).getId() + "-" + it.getValue().get(i).getId());
					Calle aux = mapa.get(consultar);
					pw.println("<Placemark>\r\n" + "		<name>" + it.getValue().get(i).getId() + "</name>\r\n"
							+ "<styleUrl>#failed" + it.getKey() + "</styleUrl>" + "\t\t\t\t"
							+ "<visibility>0</visibility>\n" + "\t\t\t\t"
							+ "<ExtendedData><SchemaData schemaUrl=\"PuntosMM\">\n" + "\t\t\t\t\t"
							+ "<SimpleData name=\"id\">" + aux.getIdArco() + "</SimpleData>\n" + "\t\t\t\t\t"
							+ "<SimpleData name=\"Tiempo\">" + aux.getTiempo() + "</SimpleData>\n" + "\t\t\t\t\t"
							+ "<SimpleData name=\"Probabilidad\">" + aux.getProbabilidad() + "</SimpleData>\n"
							+ "\t\t\t\t\t" + "<SimpleData name=\"PobExp800\">" + aux.getPobExpuesta800()
							+ "</SimpleData>\n" + "\t\t\t\t\t" + "<SimpleData name=\"PobExp1000\">"
							+ aux.getPobExpuesta1000() + "</SimpleData>\n" + "\t\t\t\t\t"
							+ "<SimpleData name=\"Riesgo800\">" + aux.getRiesgo800() + "</SimpleData>\n" + "\t\t\t\t\t"
							+ "<SimpleData name=\"Riesgo1000\">" + aux.getRiesgo1000() + "</SimpleData>\n" + "\t\t\t\t"
							+ "</SchemaData></ExtendedData>");

					pw.print("\t\t\t\t" + "<LineString>\n" + "\t\t\t\t" + "<extrude>1</extrude>\n" + "\t\t\t\t"
							+ "<tessellate>1</tessellate>\n" + "\t\t\t\t\t" + "<coordinates>\n");
					pw.print("\t\t\t\t\t" + aux.getaLon() + "," + aux.getaLat() + "\n");
					pw.print("\t\t\t\t\t" + aux.getbLon() + "," + aux.getbLat() + "\n");
					pw.print("\t\t\t\t\t" + "</coordinates>\n");
					pw.print("\t\t\t\t" + "</LineString>\n" + "\t\t\t" + "</Placemark>\n");
					pw.flush();
					auxCalle.add(aux);
				}
				pw.print("\t\t" + "</Folder>\n");
				arcos.put(it.getKey(),auxCalle);
			}
			pw.print("\t\t" + "</Folder>\n");
			pw.print("\t" + "</Document>\n" + "</kml>");
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escribirStyleKMLCallesColorRandom(PrintWriter pw, int numerito) {

		Random random = new Random();
		// System.out.println( "hex: " + Integer.toHexString( c.getRGB() & 0x00ffffff )
		// );
		for (int i = 0; i < numerito; i++) {
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			Color c = new Color(r, g, b);
			String aux = Integer.toHexString(c.getRGB() & 0x00ffffff);
			char[] fromString = aux.toCharArray();
			fromString[0] = 'C';
			fromString[1] = 'F';
			aux = String.valueOf(fromString);
			pw.println("<StyleMap id=\"failed" + i + "\">" + "<Pair>" + "	<key>normal</key>" + "	<styleUrl>#failed1"
					+ i + "</styleUrl>" + " </Pair>" + " <Pair>" + "	<key>highlight</key>" + "	<styleUrl>#failed0"
					+ i + "</styleUrl>" + " </Pair>" + "</StyleMap>" + "<Style id=\"failed0" + i + "\">" + "<LineStyle>"
					+ "	<color>" + aux + "aa</color>" + "	<width>6</width>" + "</LineStyle>" + "</Style>"
					+ "<Style id=\"failed1" + i + "\">" + "<LineStyle>" + " 	<color>" + aux + "aa</color>"
					+ "	<width>6</width>" + "</LineStyle>" + "</Style>");
		}
	}

	private void escribirStyleKMLCalles(PrintWriter pw, int numerito) {

		pw.println("<StyleMap id=\"failed1\">\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>normal</key>\r\n"
				+ "			<styleUrl>#failed11</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>highlight</key>\r\n"
				+ "			<styleUrl>#failed01</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "	</StyleMap>\r\n"
				+ "	<StyleMap id=\"failed2\">\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>normal</key>\r\n"
				+ "			<styleUrl>#failed12</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>highlight</key>\r\n"
				+ "			<styleUrl>#failed02</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "	</StyleMap>\r\n"
				+ "	<Style id=\"failed12\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<color>ffff0000</color>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>\r\n"
				+ "	<Style id=\"failed01\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>\r\n"
				+ "	<Style id=\"failed10\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<color>ff00ffaa</color>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>\r\n"
				+ "	<Style id=\"failed00\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<color>ff00ffaa</color>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>\r\n"
				+ "	<StyleMap id=\"failed0\">\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>normal</key>\r\n"
				+ "			<styleUrl>#failed10</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "		<Pair>\r\n"
				+ "			<key>highlight</key>\r\n"
				+ "			<styleUrl>#failed00</styleUrl>\r\n"
				+ "		</Pair>\r\n"
				+ "	</StyleMap>\r\n"
				+ "	<Style id=\"failed11\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>\r\n"
				+ "	<Style id=\"failed02\">\r\n"
				+ "		<LineStyle>\r\n"
				+ "			<color>ffff0000</color>\r\n"
				+ "			<width>6</width>\r\n"
				+ "		</LineStyle>\r\n"
				+ "	</Style>");
	}

	private void escribirEstiloCirculo(PrintWriter pw) {
		pw.println("<StyleMap id=\"failed\">\r\n" + "		<Pair>\r\n" + "			<key>normal</key>\r\n"
				+ "			<styleUrl>#failed0</styleUrl>\r\n" + "		</Pair>\r\n" + "		<Pair>\r\n"
				+ "			<key>highlight</key>\r\n" + "			<styleUrl>#failed1</styleUrl>\r\n"
				+ "		</Pair>\r\n" + "	</StyleMap>\r\n" + "	<Style id=\"failed0\">\r\n" + "		<LineStyle>\r\n"
				+ "			<color>ff000000</color>\r\n" + "		</LineStyle>\r\n" + "		<PolyStyle>\r\n"
				+ "			<color>667fff00</color>\r\n" + "		</PolyStyle>\r\n" + "	</Style>\r\n"
				+ "	<Style id=\"failed1\">\r\n" + "		<LineStyle>\r\n" + "			<color>ff000000</color>\r\n"
				+ "		</LineStyle>\r\n" + "		<PolyStyle>\r\n" + "			<color>667fff00</color>\r\n"
				+ "		</PolyStyle>\r\n" + "	</Style>");
	}
}
