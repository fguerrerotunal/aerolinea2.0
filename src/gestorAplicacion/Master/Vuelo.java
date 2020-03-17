package gestorAplicacion.Master;
import java.util.*;

import gestorAplicacion.AtencionAlCliente.Reserva;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Vuelo{

	static int cantidadVuelos = 0;
	private Reserva[] puestos = new Reserva[20];
	private int numeroVuelo;
	private int precioTiquete;
	private Calendar fecha=Calendar.getInstance();
	private Aeropuerto destino;
	private Aeropuerto salida;
	private String estado = "Venta";
	private int puertaAbordaje;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm");
	
	Vuelo(Calendar fecha, Aeropuerto destino, Aeropuerto salida, int puertaAbordaje){
		cantidadVuelos += 1;
		this.numeroVuelo = cantidadVuelos;
		precioTiquete = ((int)(Math.random()*(500000-125000+1)+125000));
		this.fecha = fecha;
		this.estado="Venta";
		this.salida = salida;
		this.destino= destino;
		this.puertaAbordaje = puertaAbordaje;
	}	
	
	public Reserva[] getPuestos() {
		return puestos;
	}



	public void setPuestos(Reserva[] puestos) {
		this.puestos = puestos;
	}



	public int getNumeroVuelo() {
		return numeroVuelo;
	}



	public void setNumeroVuelo(int numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}



	public int getPrecioTiquete() {
		return precioTiquete;
	}



	public void setPrecioTiquete(int precioTiquete) {
		this.precioTiquete = precioTiquete;
	}



	public Calendar getFecha() {
		return fecha;
	}



	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}



	public Aeropuerto getDestino() {
		return destino;
	}



	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}



	public Aeropuerto getSalida() {
		return salida;
	}



	public void setSalida(Aeropuerto salida) {
		this.salida = salida;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public int getPuertaAbordaje() {
		return puertaAbordaje;
	}



	public void setPuertaAbordaje(int puertaAbordaje) {
		this.puertaAbordaje = puertaAbordaje;
	}



	public String toString(String tipo) {
		String info = "";
		String recorrido = this.salida.getCiudad() +"-"+ this.destino.getCiudad();
		
		switch (tipo) {
		case "consulta":
			info = info + Integer.toString(numeroVuelo) +"\t \t"+ Integer.toString(precioTiquete) +"\t "+ dateFormat.format(fecha.getTime()) +"\t"+ recorrido;
			break;
		case "pasabordo":
			info = "VUELO: " + numeroVuelo +"\n"+
					"EN SALA: " + dateFormat.format(fecha.getTime()) + "\n"+
					"PUERTA DE ABORAJE: " + puertaAbordaje + "\n" +
					"ORIGEN: " + salida.getCiudad() + "\n"+
					"DESTINO: " + destino.getCiudad() + "\n";
			break;
									
		case "estado":
			info = info + Integer.toString(numeroVuelo) +"\t \t"+ estado +"    \t  \t"+ puertaAbordaje +"\t \t "+ recorrido;
			break;
		
		case "sillas":
			String a;
			for(int i = 0; i < 20; i++) {
				if(puestos[i] == null) {
					a = "[PUESTO: " + (Integer.valueOf(i)+Integer.valueOf(1)) + " LIBRE ]" ;
				}
				else {
					a = "[PUESTO: " + i+1 + " OCUPADO ]" ;
				}
				info = info + a;
				if(i == 4 || i == 9 || i == 14 || i == 19) {
					info = info + "\n";
				}
				if(i == 9) {
					info = info + "=============================================" + "\n";
				}
			}
			break;
			
		}
	
		return info;
	}

	public void finalizer() {
		cantidadVuelos += 1;
		this.numeroVuelo =cantidadVuelos+20;
		precioTiquete = ((int)(Math.random()*(500000-125000+1)+125000));
		this.fecha = Calendar.getInstance();
		fecha.add(Calendar.MINUTE,(int)Math.random()*(4-2+1)+2);
		this.estado="Venta";
		for(int i = 0;i<20;i++){ 
			if(this.puestos[i]!=null){
				this.puestos[i].getPasajero().A�adirHistorial(this.toString("consulta"));
				Admin.empleados.get(0).ModMillas(this.puestos[i].getPasajero(), this.precioTiquete/2);
				this.puestos[i].getPasajero().cartera.remove(this.puestos[i]);
				this.puestos[i]=null;
			}
		}
	}
}